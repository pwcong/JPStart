package com.github.pwcong.jpstart.network.aliyun.service

import com.github.pwcong.jpstart.mvp.bean.TranslateBean
import rx.Subscriber

interface AliyunService {
    interface TranslateService {
        fun translate(
            q: String,
            from: String,
            to: String,
            subscriber: Subscriber<TranslateBean>
        )
    }
}
