package com.github.pwcong.jpstart.mvp.bean

class JPItem(
    var id: Int,
    var row: Int,
    var column: Int,
    var hiragana: String,
    var katakana: String,
    var rome: String,
    var category: Int,
    var type: Int,
    var isExisted: Boolean
) {
    override fun toString(): String {
        return "JPItem{" +
                "id=" + id +
                ", row=" + row +
                ", column=" + column +
                ", hiragana='" + hiragana + '\'' +
                ", katakana='" + katakana + '\'' +
                ", rome='" + rome + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", existed=" + isExisted +
                '}'
    }
}