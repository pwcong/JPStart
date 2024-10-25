package com.github.pwcong.jpstart.mvp.bean

class YoudaoTranslateBean(var translation: Array<String>, var query: String, var errorCode: Int) {
    override fun toString(): String {
        return "BaiduTranslateBean{" +
                "translation=" + translation.contentToString() +
                ", query='" + query + '\'' +
                ", errorCode=" + errorCode +
                '}'
    }
}