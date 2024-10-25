package com.github.pwcong.jpstart.mvp.model.impl

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.manager.DBManager
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.model.BaseModel.PuzzleActivityModel
import com.github.pwcong.jpstart.utils.ResourceUtils

class PuzzleActivityModelImpl() : PuzzleActivityModel {
    override val options: Array<String>
        get() = arrayOf<String>(
            ResourceUtils.getString(App.getInstance(), R.string.hiragana_rome),
            ResourceUtils.getString(App.getInstance(), R.string.hiragana_katakana),
            ResourceUtils.getString(App.getInstance(), R.string.katakana_rome)
        )

    override val items: List<JPItem>
        get() {
            val items: MutableList<JPItem> = mutableListOf()
            items.addAll(DBManager.getInstance().getQingYinWithoutHeader())
            items.shuffle()

            return items
        }
}
