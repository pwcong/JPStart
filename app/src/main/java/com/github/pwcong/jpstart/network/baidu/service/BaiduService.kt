package com.github.pwcong.jpstart.network.baidu.service

import com.github.pwcong.jpstart.mvp.bean.BaiduTranslateBean
import rx.Subscriber

interface BaiduService {
    interface TranslateService {
        fun translate(
            q: String,
            from: String,
            to: String,
            subscriber: Subscriber<BaiduTranslateBean>
        )
    }
}
