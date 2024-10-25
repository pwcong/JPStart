package com.github.pwcong.jpstart.mvp.bean

class TranslateSpinnerItem(var icon: Int, var name: String, var isHasIcon: Boolean) {
    override fun toString(): String {
        return "TranslateSpinnerItem{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                ", hasIcon=" + isHasIcon +
                '}'
    }
}