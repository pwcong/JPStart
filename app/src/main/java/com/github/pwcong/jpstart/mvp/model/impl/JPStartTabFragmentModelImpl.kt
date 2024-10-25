package com.github.pwcong.jpstart.mvp.model.impl

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constant.Constant
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

            list.add(JPTab(Constant.CATEGORY_QINGYIN, qingyin))
            list.add(JPTab(Constant.CATEGORY_ZHUOYIN, zhuoyin))
            list.add(JPTab(Constant.CATEGORY_AOYIN, aoyin))

            return list
        }
}
