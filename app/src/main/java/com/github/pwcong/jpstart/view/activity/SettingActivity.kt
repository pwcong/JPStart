package com.github.pwcong.jpstart.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.databinding.ActivitySettingBinding
import com.github.pwcong.jpstart.rxbus.RxBus
import com.github.pwcong.jpstart.rxbus.event.EventContainer
import com.github.pwcong.jpstart.rxbus.event.SettingEvent
import com.github.pwcong.jpstart.view.fragment.SettingFragment
import rx.Subscription

class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    private lateinit var mRootLayout: CoordinatorLayout

    private lateinit var mToolbar: Toolbar

    private var registered: Boolean = false

    private var subscription: Subscription? = null

    override fun initViewBinding(): ActivitySettingBinding {
        return ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        mRootLayout = viewBinding.layoutRoot
        mToolbar = viewBinding.toolbar

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
