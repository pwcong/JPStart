package com.github.pwcong.jpstart.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

abstract class BasePagerAdapter<T>(fm: FragmentManager, private val list: List<T>) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return getFragment(list[position])
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return getTitle(list[position])
    }

    protected abstract fun getFragment(t: T): Fragment

    protected abstract fun getTitle(t: T): CharSequence?
}
