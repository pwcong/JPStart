package com.github.pwcong.jpstart.mvp.model.impl

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.mvp.bean.TranslateBean
import com.github.pwcong.jpstart.mvp.bean.TranslateSpinnerItem
import com.github.pwcong.jpstart.mvp.model.BaseModel.TranslateFragmentModel
import com.github.pwcong.jpstart.network.aliyun.service.AliyunService
import com.github.pwcong.jpstart.network.aliyun.service.impl.AliyunTranslateServiceImpl
import com.github.pwcong.jpstart.utils.ResourceUtils
import rx.Subscriber

class TranslateFragmentModelImpl : TranslateFragmentModel {
    private val service: AliyunService.TranslateService = AliyunTranslateServiceImpl()

    override val fromList: List<TranslateSpinnerItem>
        get() {
            val fromList: MutableList<TranslateSpinnerItem> = ArrayList()
            fromList.add(
                TranslateSpinnerItem(
                    0,
                    ResourceUtils.getString(App.getInstance(), R.string.auto_check),
                    false
                )
            )
            fromList.add(
                TranslateSpinnerItem(
                    R.drawable.china_icon_64,
                    ResourceUtils.getString(App.getInstance(), R.string.chinese), true
                )
            )
            fromList.add(
                TranslateSpinnerItem(
                    R.drawable.kingdom_united_icon_64,
                    ResourceUtils.getString(App.getInstance(), R.string.english), true
                )
            )
            fromList.add(
                TranslateSpinnerItem(
                    R.drawable.japan_icon_64,
                    ResourceUtils.getString(App.getInstance(), R.string.japanese), true
                )
            )

            return fromList
        }

    override val toList: List<TranslateSpinnerItem>
        get() {
            val toList: MutableList<TranslateSpinnerItem> = ArrayList()
            toList.add(
                TranslateSpinnerItem(
                    R.drawable.china_icon_64,
                    ResourceUtils.getString(App.getInstance(), R.string.chinese), true
                )
            )
            toList.add(
                TranslateSpinnerItem(
                    R.drawable.kingdom_united_icon_64,
                    ResourceUtils.getString(App.getInstance(), R.string.english), true
                )
            )
            toList.add(
                TranslateSpinnerItem(
                    R.drawable.japan_icon_64,
                    ResourceUtils.getString(App.getInstance(), R.string.japanese), true
                )
            )

            return toList
        }

    override fun translate(
        q: String,
        from: String,
        to: String,
        subscriber: Subscriber<TranslateBean>
    ) {
        service.translate(q, from, to, subscriber)
    }
}
