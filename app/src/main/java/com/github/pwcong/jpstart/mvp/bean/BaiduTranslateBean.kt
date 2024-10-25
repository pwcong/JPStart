package com.github.pwcong.jpstart.mvp.bean

class BaiduTranslateBean(
    var from: String,
    var to: String,
    var trans_result: Array<TranslateResult>,
    var error_code: String,
    var error_msg: String
) {
    override fun toString(): String {
        return "BaiduTranslateBean{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + trans_result.contentToString() +
                ", error_code='" + error_code + '\'' +
                ", error_msg='" + error_msg + '\'' +
                '}'
    }

    inner class TranslateResult(var src: String, var dst: String) {
        override fun toString(): String {
            return "TranslateResult{" +
                    "src='" + src + '\'' +
                    ", dst='" + dst + '\'' +
                    '}'
        }
    }
}