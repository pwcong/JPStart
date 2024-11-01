package com.github.pwcong.jpstart.mvp.view

import android.content.DialogInterface
import android.os.Bundle
import com.github.pwcong.jpstart.mvp.bean.BannerItem
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.bean.JPTab
import com.github.pwcong.jpstart.mvp.bean.TranslateSpinnerItem

interface BaseView<T> {
    fun setData(data: List<T>)

    interface MainActivityView {
        fun setViewPager(data: List<BannerItem>)

        fun openDrawer()

        fun closeDrawer()

        fun switchJPStart()

        fun switchMemory()

        fun switchTranslate()

        fun switchGame()

        fun switchAbout()

        fun switchSetting()

        fun startPhotoViewActivity(bundle: Bundle?)

        fun startPuzzleActivity()

        fun showAlertDialog(
            titleId: Int, messageId: Int,
            positiveTextId: Int, positiveButtonListener: DialogInterface.OnClickListener?,
            negativeTextId: Int, negativeButtonListener: DialogInterface.OnClickListener?
        )
    }

    interface JPStartTabFragmentView : BaseView<JPTab> {
        fun scrollViewPager(position: Int)
    }

    interface JPStartFragmentView : BaseView<JPItem> {
        fun setRecyclerView(type: Int)
    }

    interface MemoryFragmentView : BaseView<JPItem> {
        fun showMsg(msg: Int)

        fun showMsg(msg: String)

        fun hideFabMenu()
    }

    interface PuzzleActivityView {
        fun setData(current: JPItem, jams: List<JPItem>)

        fun showSelectDialog(selection: Array<String>)

        fun showResultDialog(
            title: Int, msg: String, icon: Int,
            pbt: Int, pbl: DialogInterface.OnClickListener?,
            nbt: Int, nbl: DialogInterface.OnClickListener?
        )

        fun showDialog(icon: Int, title: Int, msg: String)

        fun setTitle(title: String)

        fun setTitle(title: Int)

        fun addCount()

        fun clearCount()

        fun showMsg(msg: Int)

        fun showMsg(msg: String)
    }

    interface TranslateFragmentView {
        fun showMsg(msg: String)

        fun showMsg(msg: Int)

        val srcText: String

        fun setSrcEditText(text: String)

        val dstText: String

        fun setDstTextView(text: String)

        fun setFromSpinner(list: List<TranslateSpinnerItem>)

        fun setToSpinner(list: List<TranslateSpinnerItem>)
    }


}
