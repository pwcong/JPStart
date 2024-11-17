package com.github.pwcong.jpstart.constants

import android.os.Environment

class Constants private constructor() {
    init {
        throw RuntimeException("/(ㄒoㄒ)/~~")
    }

    companion object {
        const val OK: Int = 200

        const val CATEGORY_YIN: String = "category_yin"
        const val CATEGORY_QINGYIN: Int = 1
        const val CATEGORY_ZHUOYIN: Int = 2
        const val CATEGORY_AOYIN: Int = 3

        const val ROW_QINGYIN: Int = 12
        const val COLUMN_QINGYIN: Int = 6
        const val ROW_ZHUOYIN: Int = 6
        const val COLUMN_ZHUOYIN: Int = 6
        const val ROW_AOYIN: Int = 12
        const val COLUMN_AOYIN: Int = 4

        const val TYPE_HIRAGANA: Int = 666
        const val TYPE_KATAKANA: Int = 999

        const val TYPE_HEADER: Int = 0
        const val TYPE_ITEM: Int = 1
        const val TYPE_ITEM_DISABLE: Int = 2

        const val PREF_NAME: String = "pref_jpstart"

        const val FLAG_TIPS_JPSTART: String = "flag_tips_jpstart"
        const val FLAG_TIPS_TRANSLATE: String = "flag_tips_translate"
        const val FLAG_TIPS_MEMORY: String = "flag_tips_memory"

        const val IMG_URL: String = "img_url"
        const val IMG_ID: String = "img_id"

        val FILEDIR_ROOT: String = Environment.getExternalStorageDirectory().toString() + "/JPStart"
        const val FILETYPE_JPG: String = ".jpg"
        const val FILETYPE_PNG: String = ".png"

        const val MODE_THEME: String = "mode_theme"
        const val MODE_AUTO: String = "auto"
        const val MODE_DAY: String = "day"
        const val MODE_NIGHT: String = "night"

        const val URL_PWCONG: String = "http://github.com/pwcong"

        const val IMG_BANNER: String = "img_banner"

        const val ALLOW_CONNECT_WITHOUT_WIFI: String = "allow_connect_without_wifi"

        const val HIGHEST_SCORE: String = "highest_score"
    }
}