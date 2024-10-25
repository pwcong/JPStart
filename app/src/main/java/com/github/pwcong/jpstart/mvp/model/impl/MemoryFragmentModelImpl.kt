package com.github.pwcong.jpstart.mvp.model.impl

import com.github.pwcong.jpstart.manager.DBManager
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.model.BaseModel.MemoryFragmentModel

class MemoryFragmentModelImpl : MemoryFragmentModel {
    override val qingYinWithoutHeader: List<JPItem>
        get() {
            val list: MutableList<JPItem> = mutableListOf()
            list.addAll(DBManager.getInstance().getQingYinWithoutHeader())
            list.shuffle()

            return list
        }

    override val zhuoYinWithoutHeader: List<JPItem>
        get() {
            val list: MutableList<JPItem> = mutableListOf()
            list.addAll(DBManager.getInstance().getZhuoYinWithoutHeader())
            list.shuffle()

            return list
        }

    override val aoYinWithoutHeader: List<JPItem>
        get() {
            val list: MutableList<JPItem> = mutableListOf()
            list.addAll(DBManager.getInstance().getAoYinWithoutHeader())
            list.shuffle()

            return list
        }
}
