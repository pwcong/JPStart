package com.github.pwcong.jpstart.ui.component.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class BannerViewPager : ViewPager {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(
        context, attrs
    )

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }
}
