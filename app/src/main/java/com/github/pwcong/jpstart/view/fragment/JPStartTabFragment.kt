package com.github.pwcong.jpstart.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.adapter.JPStartTabPagerAdapter
import com.github.pwcong.jpstart.databinding.FragmentTabBinding
import com.github.pwcong.jpstart.mvp.bean.JPTab
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter.JPStartTabFragmentPresenter
import com.github.pwcong.jpstart.mvp.presenter.impl.JPStartTabFragmentPresenterImpl
import com.github.pwcong.jpstart.mvp.view.BaseView.JPStartTabFragmentView

class JPStartTabFragment : BaseFragment<FragmentTabBinding>(), JPStartTabFragmentView {

    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    private lateinit var presenter: JPStartTabFragmentPresenter

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTabBinding {
        return FragmentTabBinding.inflate(inflater, container, false)
    }

    override fun init(savedInstanceState: Bundle?) {
        mTabLayout = viewBinding.tabLayout
        mViewPager = viewBinding.viewPager

        presenter = JPStartTabFragmentPresenterImpl(this)

        initTabLayout()
        initViewPager()

        presenter.initJPStartTabFragment()
    }

    private fun initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager)
    }

    private fun initViewPager() {
        mViewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                App.CURRENT_ITEM = position
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    override fun scrollViewPager(position: Int) {
        mViewPager.setCurrentItem(position, true)
    }

    override fun setData(data: List<JPTab>) {
        mViewPager.adapter =
            JPStartTabPagerAdapter(childFragmentManager, data)
    }
}
