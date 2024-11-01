package com.github.pwcong.jpstart.mvp.bean

class TranslateBean(
    var from: String,
    var to: String,
    var result: String,
    var code: Int,
) {
    override fun toString(): String {
        return "AliyunTranslateBean{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", result=" + result +
                ", code='" + code + '\'' +
                '}'
    }

}