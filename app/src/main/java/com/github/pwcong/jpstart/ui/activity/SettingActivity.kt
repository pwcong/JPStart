package com.github.pwcong.jpstart.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.databinding.ActivitySettingBinding
import com.github.pwcong.jpstart.rxbus.RxBus
import com.github.pwcong.jpstart.rxbus.event.EventContainer
import com.github.pwcong.jpstart.rxbus.event.SettingEvent
import com.github.pwcong.jpstart.ui.fragment.SettingFragment
import rx.Subscription

class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    private lateinit var mRootLayout: CoordinatorLayout

    private lateinit var mToolbar: Toolbar

    private var registered: Boolean = false

    private var subscription: Subscription? = null

    override fun initViewBinding(): ActivitySettingBinding {
        return ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mRootLayout = getViewBinding().layoutRoot
        mToolbar = getViewBinding().toolbar

        if (!registered) {
            subscription = RxBus.getDefault().toObserverable(EventContainer::class.java)
                .subscribe { eventContainer ->
                    if (eventContainer.type == EventContainer.TYPE_SETTING) {
                        val event = eventContainer.event as SettingEvent
                        showSnackBar(mRootLayout, event.msg)
                    }
                }

            registered = true
        }

        initToolbar()
    }

    override fun doAction() {
        fragmentManager.beginTransaction().replace(R.id.content, SettingFragment()).commit()
    }

    private fun initToolbar() {
        mToolbar.setTitle(R.string.setting)
        setSupportActionBar(mToolbar)

        mToolbar.setNavigationOnClickListener(View.OnClickListener { finish() })
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription?.unsubscribe()
    }
}
