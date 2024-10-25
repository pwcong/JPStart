package com.github.pwcong.jpstart.mvp.bean

class BannerItem(var banner: Int, var title: String) {
    override fun toString(): String {
        return "BannerItem{" +
                "banner=" + banner +
                ", title='" + title + '\'' +
                '}'
    }
}