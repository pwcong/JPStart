package com.github.pwcong.jpstart.mvp.model

import com.github.pwcong.jpstart.mvp.bean.BannerItem
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.bean.JPTab
import com.github.pwcong.jpstart.mvp.bean.TranslateBean
import com.github.pwcong.jpstart.mvp.bean.TranslateSpinnerItem
import rx.Subscriber

interface BaseModel<T> {
    val data: List<T>

    interface MainActivityModel : BaseModel<BannerItem>

    interface JPStartTabFragmentModel : BaseModel<JPTab>

    interface JPStartFragmentModel {
        fun getData(category: Int, subscriber: Subscriber<List<JPItem>>)
    }

    interface MemoryFragmentModel {
        val qingYinWithoutHeader: List<JPItem>

        val zhuoYinWithoutHeader: List<JPItem>

        val aoYinWithoutHeader: List<JPItem>
    }

    interface PuzzleActivityModel {
        val options: Array<String>

        val items: List<JPItem>
    }

    interface TranslateFragmentModel {
        val fromList: List<TranslateSpinnerItem>

        val toList: List<TranslateSpinnerItem>

        fun translate(
            q: String,
            from: String,
            to: String,
            subscriber: Subscriber<TranslateBean>
        )
    }


}