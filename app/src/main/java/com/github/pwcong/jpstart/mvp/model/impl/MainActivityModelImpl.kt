package com.github.pwcong.jpstart.mvp.model.impl

import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.mvp.bean.BannerItem
import com.github.pwcong.jpstart.mvp.model.BaseModel.MainActivityModel

class MainActivityModelImpl : MainActivityModel {
    override val data: List<BannerItem>
        get() {
            val list: MutableList<BannerItem> = ArrayList()
            list.add(BannerItem(R.drawable.banner01, ""))
            list.add(BannerItem(R.drawable.banner02, ""))
            list.add(BannerItem(R.drawable.banner03, ""))

            return list
        }
}
