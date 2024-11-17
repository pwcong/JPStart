package com.github.pwcong.jpstart.mvp.model.impl

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.mvp.bean.JPTab
import com.github.pwcong.jpstart.mvp.model.BaseModel.JPStartTabFragmentModel
import com.github.pwcong.jpstart.utils.ResourceUtils

class JPStartTabFragmentModelImpl : JPStartTabFragmentModel {
    override val data: List<JPTab>
        get() {
            val list: MutableList<JPTab> = ArrayList()
            val qingyin: String = ResourceUtils.getString(App.getInstance(), R.string.qingyin)
            val zhuoyin: String = ResourceUtils.getString(App.getInstance(), R.string.zhuoyin)
            val aoyin: String = ResourceUtils.getString(App.getInstance(), R.string.aoyin)

            list.add(JPTab(Constants.CATEGORY_QINGYIN, qingyin))
            list.add(JPTab(Constants.CATEGORY_ZHUOYIN, zhuoyin))
            list.add(JPTab(Constants.CATEGORY_AOYIN, aoyin))

            return list
        }
}
