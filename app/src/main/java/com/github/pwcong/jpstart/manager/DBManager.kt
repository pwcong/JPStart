package com.github.pwcong.jpstart.manager

import java.util.Collections

import android.database.sqlite.SQLiteDatabase

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.comparator.JPItemComparator
import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.db.JPStartDatabase
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.utils.ResourceUtils

class DBManager private constructor() {
    private var query: MutableList<JPItem>? = null
    private var qingYin: MutableList<JPItem>? = null
    private var zhuoYin: MutableList<JPItem>? = null
    private var aoYin: MutableList<JPItem>? = null
    private var qingYinWithoutHeader: MutableList<JPItem>? = null
    private var zhuoYinWithoutHeader: MutableList<JPItem>? = null
    private var aoYinWithoutHeader: MutableList<JPItem>? = null

    fun init() {
        getQingYinWithoutHeader()
        getZhuoYinWithoutHeader()
        getAoYinWithoutHeader()
    }

    @Synchronized
    fun query(): List<JPItem> {
        if (query == null) {
            val db: SQLiteDatabase = JPStartDatabase(App.getInstance()).getReadableDatabase()
            val cursor = db.rawQuery("select * from " + JPStartDatabase.DB_TABLE_NAME, null)
            query = mutableListOf<JPItem>()
            var item: JPItem
            while (cursor.moveToNext()) {
                val idIndex = cursor.getColumnIndex("id")
                val id = cursor.getInt(idIndex)

                val rowIndex = cursor.getColumnIndex("row")
                val row = cursor.getInt(rowIndex)

                val columnIndex = cursor.getColumnIndex("column")
                val column = cursor.getInt(columnIndex)

                val hiraganaIndex = cursor.getColumnIndex("hiragana")
                val hiragana = cursor.getString(hiraganaIndex)

                val katakanaIndex = cursor.getColumnIndex("katakana")
                val katakana = cursor.getString(katakanaIndex)

                val romeIndex = cursor.getColumnIndex("rome")
                val rome = cursor.getString(romeIndex)

                val categoryIndex = cursor.getColumnIndex("category")
                val category = cursor.getInt(categoryIndex)

                val typeIndex = cursor.getColumnIndex("type")
                val type = cursor.getInt(typeIndex)

                val existedIndex = cursor.getColumnIndex("existed")
                val existed = cursor.getInt(existedIndex) == 1

                item = JPItem(id, row, column, hiragana, katakana, rome, category, type, existed)

                query?.add(item)
            }
            cursor.close()
            db.close()
        }

        return query!!
    }

    @Synchronized
    fun getQingYin(): List<JPItem> {
        if (qingYin == null) {
            qingYin = mutableListOf()
            val query = query()
            for (item in query) {
                if (item.category == Constants.CATEGORY_QINGYIN) {
                    qingYin!!.add(item)
                }
            }

            Collections.sort<JPItem>(qingYin!!, JPItemComparator())
        }

        return qingYin!!
    }

    @Synchronized
    fun getZhuoYin(): List<JPItem> {
        if (zhuoYin == null) {
            zhuoYin = mutableListOf()
            val query = query()
            for (item in query) {
                if (item.category == Constants.CATEGORY_ZHUOYIN) {
                    zhuoYin!!.add(item)
                }
            }

            Collections.sort<JPItem>(zhuoYin!!, JPItemComparator())
        }

        return zhuoYin!!
    }

    @Synchronized
    fun getAoYin(): List<JPItem> {
        if (aoYin == null) {
            aoYin = mutableListOf()
            val query = query()
            for (item in query) {
                if (item.category == Constants.CATEGORY_AOYIN) {
                    aoYin!!.add(item)
                }
            }

            Collections.sort<JPItem>(aoYin!!, JPItemComparator())
        }

        return aoYin!!
    }

    fun getQingYinWithoutHeader(): List<JPItem> {
        if (qingYinWithoutHeader == null) {
            qingYinWithoutHeader = mutableListOf()
            val query = getQingYin()
            for (item in query) {
                if (item.row != 0 && item.column != 0 && item.isExisted) {
                    qingYinWithoutHeader!!.add(item)
                }
            }
        }

        return qingYinWithoutHeader!!
    }

    fun getZhuoYinWithoutHeader(): List<JPItem> {
        if (zhuoYinWithoutHeader == null) {
            zhuoYinWithoutHeader = mutableListOf()
            val query = getZhuoYin()
            for (item in query) {
                if (item.row != 0 && item.column != 0 && item.isExisted) {
                    zhuoYinWithoutHeader!!.add(item)
                }
            }
        }

        return zhuoYinWithoutHeader!!
    }

    fun getAoYinWithoutHeader(): List<JPItem> {
        if (aoYinWithoutHeader == null) {
            aoYinWithoutHeader = mutableListOf()
            val query = getAoYin()
            for (item in query) {
                if (item.row != 0 && item.column != 0 && item.isExisted) {
                    aoYinWithoutHeader!!.add(item)
                }
            }
        }

        return aoYinWithoutHeader!!
    }

    companion object {
        private var instance: DBManager? = null

        @Synchronized
        fun getInstance(): DBManager {
            if (instance == null) {
                instance = DBManager()
            }

            return instance!!
        }

        fun addHeaderString(list: List<JPItem>, row: Int, column: Int) {
            for (i in 1 until column) {
                val item = list[i]
                val hiragana = item.hiragana
                val katakana = item.katakana
                item.hiragana =
                    hiragana + ResourceUtils.getString(App.getInstance(), R.string.column)
                item.katakana =
                    katakana + ResourceUtils.getString(App.getInstance(), R.string.column)
            }

            for (i in 1 until row) {
                val item = list[i * column]
                val hiragana = item.hiragana
                val katakana = item.katakana
                item.hiragana = hiragana + ResourceUtils.getString(App.getInstance(), R.string.row)
                item.katakana = katakana + ResourceUtils.getString(App.getInstance(), R.string.row)
            }
        }
    }
}