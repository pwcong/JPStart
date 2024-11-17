package com.github.pwcong.jpstart.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.pwcong.jpstart.mvp.bean.JPTab
import com.github.pwcong.jpstart.view.fragment.JPStartFragment

class JPStartTabPagerAdapter(fm: FragmentManager, list: List<JPTab>) :
    BasePagerAdapter<JPTab>(fm, list) {

    override fun getFragment(t: JPTab): Fragment {
        return JPStartFragment.newInstance(t.type)
    }

    override fun getTitle(t: JPTab): CharSequence? {
        return t.title
    }

}
