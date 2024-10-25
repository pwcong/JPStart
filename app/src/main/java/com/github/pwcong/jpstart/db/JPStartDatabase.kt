package com.github.pwcong.jpstart.db

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class JPStartDatabase(context: Context) :
    SQLiteAssetHelper(context, DB_NAME, null, DB_VERSON) {
    companion object {
        const val DB_NAME: String = "jpstart.db"
        const val DB_TABLE_NAME: String = "data"
        const val DB_VERSON: Int = 1
    }
}