package com.github.pwcong.jpstart.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.Toolbar.LayoutParams
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.adapter.BannerPagerAdapter
import com.github.pwcong.jpstart.databinding.ActivityMainBinding
import com.github.pwcong.jpstart.manager.ActivityManager
import com.github.pwcong.jpstart.mvp.bean.BannerItem
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.impl.MainActivityPresenterImpl
import com.github.pwcong.jpstart.mvp.view.BaseView
import com.github.pwcong.jpstart.rxbus.RxBus
import com.github.pwcong.jpstart.rxbus.event.EventContainer
import com.github.pwcong.jpstart.ui.fragment.GameFragment
import com.github.pwcong.jpstart.ui.fragment.JPStartTabFragment
import com.github.pwcong.jpstart.ui.fragment.MemoryFragment
import com.github.pwcong.jpstart.ui.fragment.TranslateFragment
import com.github.pwcong.jpstart.utils.ResourceUtils
import com.github.pwcong.radiobuttonview.RadioButtonView
import me.relex.circleindicator.CircleIndicator
import rx.Observable

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<ActivityMainBinding>(), BaseView.MainActivityView {
    private val TAG: String = javaClass.simpleName

    private lateinit var mToolbar: Toolbar

    private lateinit var mDrawerLayout: DrawerLayout

    private lateinit var mNavigationView: NavigationView

    private lateinit var mBannerViewPager: ViewPager
    private lateinit var mCircleIndicator: CircleIndicator

    private lateinit var mRadioButtonView: RadioButtonView
    private lateinit var busSubscription: Subscription
    private var bannerSubscription: Subscription? = null

    private var mExitTime: Long = 0
    private lateinit var presenter: BasePresenter.MainActivityPresenter

    private var registered: Boolean = false

    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        presenter = MainActivityPresenterImpl(this)

        if (!registered) {
            busSubscription = RxBus.getDefault().toObserverable(EventContainer::class.java)
                .subscribe { eventContainer -> presenter.onBusEventInteraction(eventContainer) }

            registered = true
        }

        initToolbar()
        initRadioButtonView()
        initDrawerLayout()
        initNavigationView()
        initBanner()

        presenter.initMainActivity()
    }

    private fun initToolbar() {
        mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        Log.i(TAG, "initToolbar: OK")
    }

    private fun initRadioButtonView() {
        mRadioButtonView =
            layoutInflater.inflate(R.layout.button_radio, mToolbar, false) as RadioButtonView
        mRadioButtonView.setOptions(radioButtonOptions)
        mRadioButtonView.setOnRadioButtonChangedListener(object :
            RadioButtonView.OnRadioButtonChangedListener {
            override fun onRadioButtonChanged(option: String?, index: Int) {
                presenter.onRadioButtonChanged(index)
            }
        })

        val params = LayoutParams(
            ResourceUtils.getDimension(this@MainActivity, R.dimen.radio_button_width).toInt(),
            ViewGroup.LayoutParams.MATCH_PARENT, GravityCompat.END
        )
        mToolbar.addView(mRadioButtonView, params)

        Log.i(TAG, "initRadioButtonView: OK")
    }

    private fun initDrawerLayout() {
        mDrawerLayout = getViewBinding().drawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            mToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        Log.i(TAG, "initDrawerLayout: OK")
    }

    private fun initNavigationView() {
        mNavigationView = getViewBinding().navView
        mNavigationView.setNavigationItemSelectedListener { item ->
            presenter.onNavigationItemSelected(item.itemId)
            Log.i(TAG, "onNavigationItemSelected: OK")
            true
        }
        mNavigationView.setCheckedItem(R.id.item_jpstart)

        Log.i(TAG, "initNavigationView: OK")
    }

    private fun initBanner() {
        val view = mNavigationView.getHeaderView(0)

        mBannerViewPager = view.findViewById<View>(R.id.banner_view_pager) as ViewPager
        mCircleIndicator = view.findViewById<View>(R.id.indicator) as CircleIndicator
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer()
        } else {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                showSnackBar(mToolbar, R.string.one_more_press_to_exit)

                mExitTime = System.currentTimeMillis()
            } else {
                ActivityManager.getInstance().finishAll()
            }
        }
    }

    override fun openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START)
        Log.i(TAG, "openDrawer: OK")
    }

    override fun closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START)
        Log.i(TAG, "closeDrawer: OK")
    }

    override fun switchJPStart() {
        mToolbar.setTitle(R.string.jp_start)

        mRadioButtonView.visibility = View.VISIBLE

        supportFragmentManager.beginTransaction().replace(R.id.content_main, JPStartTabFragment())
            .commit()

        Log.i(TAG, "switchJPStart: OK")
    }

    override fun switchMemory() {
        mToolbar.setTitle(R.string.memory)

        mRadioButtonView.visibility = View.GONE

        supportFragmentManager.beginTransaction().replace(R.id.content_main, MemoryFragment())
            .commit()

        Log.i(TAG, "switchMemory: OK")
    }

    override fun switchTranslate() {
        mToolbar.setTitle(R.string.translate)

        mRadioButtonView.visibility = View.GONE

        supportFragmentManager.beginTransaction().replace(R.id.content_main, TranslateFragment())
            .commit()

        Log.i(TAG, "switchTranslate: OK")
    }

    override fun switchGame() {
        mToolbar.setTitle(R.string.game)

        mRadioButtonView.visibility = View.GONE

        supportFragmentManager.beginTransaction().replace(R.id.content_main, GameFragment())
            .commit()

        Log.i(TAG, "switchGame: OK")
    }

    override fun switchSetting() {
        startActivity(Intent(this@MainActivity, SettingActivity::class.java))

        Log.i(TAG, "switchSetting: OK")
    }

    override fun startPhotoViewActivity(bundle: Bundle?) {
        val intent = Intent(
            this@MainActivity,
            PhotoViewActivity::class.java
        )
        intent.putExtras(bundle!!)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun startPuzzleActivity() {
        val intent = Intent(
            this@MainActivity,
            PuzzleActivity::class.java
        )
        startActivity(intent)
    }

    override fun showAlertDialog(
        titleId: Int, messageId: Int,
        positiveTextId: Int, positiveButtonListener: DialogInterface.OnClickListener?,
        negativeTextId: Int, negativeButtonListener: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(this@MainActivity)
            .setTitle(titleId)
            .setMessage(messageId)
            .setPositiveButton(positiveTextId, positiveButtonListener)
            .setNegativeButton(negativeTextId, negativeButtonListener)
            .setIcon(R.drawable.ic_lightbulb_outline_black_24dp)
            .create()
            .show()
    }

    override fun switchAbout() {
        startActivity(Intent(this@MainActivity, AboutActivity::class.java))

        Log.i(TAG, "switchAbout: OK")
    }

    private val radioButtonOptions: List<String>
        get() {
            val options = mutableListOf<String>()
            options.add(ResourceUtils.getString(this@MainActivity, R.string.hiragana))
            options.add(ResourceUtils.getString(this@MainActivity, R.string.katakana))
            return options
        }

    override fun onDestroy() {
        super.onDestroy()


        if (busSubscription.isUnsubscribed) {
            busSubscription.unsubscribe()
        }

        bannerSubscription?.let {
            if (it.isUnsubscribed) {
                it.unsubscribe()
            }
        }
    }

    override fun setViewPager(data: List<BannerItem>) {
        bannerSubscription?.let {
            if (it.isUnsubscribed) {
                it.unsubscribe()
            }
        }

        mBannerViewPager.adapter = BannerPagerAdapter(supportFragmentManager, data)
        mCircleIndicator.setViewPager(mBannerViewPager)

        bannerSubscription = Observable.timer(10, 10, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(Action1<Long?> {
                val next = (mBannerViewPager.currentItem + 1) % data.size
                mBannerViewPager.currentItem = next
            })
    }
}
