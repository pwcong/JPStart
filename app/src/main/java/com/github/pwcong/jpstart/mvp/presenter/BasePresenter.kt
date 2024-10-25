package com.github.pwcong.jpstart.mvp.presenter

import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.rxbus.event.EventContainer

abstract class BasePresenter<T>(val view: T) {
    interface MainActivityPresenter {
        fun initMainActivity()

        fun onRadioButtonChanged(position: Int)

        fun onNavigationItemSelected(id: Int)

        fun onBusEventInteraction(eventContainer: EventContainer)
    }

    interface JPStartTabFragmentPresenter {
        fun initJPStartTabFragment()
    }

    interface JPStartFragmentPresenter {
        fun initJPStartFragment(category: Int)
    }

    interface MemoryFragmentPresenter {
        fun initMemoryFragment()

        fun loadMore(category: Int)

        fun setDate(category: Int)
    }

    interface PuzzleActivityPresenter {
        fun initPuzzleActivity()

        fun loadData()

        fun checkTypeSelect(which: Int)

        fun checkAnswerSelect(id: Int, current: JPItem, items: List<JPItem>)

        fun checkMenuSelect(id: Int)
    }


}
