package com.github.pwcong.jpstart.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.pwcong.jpstart.mvp.bean.BannerItem
import com.github.pwcong.jpstart.ui.fragment.BannerFragment

class BannerPagerAdapter(fm: FragmentManager, list: List<BannerItem>) :
    BasePagerAdapter<BannerItem>(fm, list) {
    override fun getFragment(t: BannerItem): Fragment {
        return BannerFragment.newInstance(t)
    }

    override fun getTitle(t: BannerItem): CharSequence? {
        return t.title
    }
}
