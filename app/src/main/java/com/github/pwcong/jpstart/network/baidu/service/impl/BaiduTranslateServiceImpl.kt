package com.github.pwcong.jpstart.network.baidu.service.impl

import com.blankj.utilcode.util.EncryptUtils
import com.github.pwcong.jpstart.mvp.bean.BaiduTranslateBean
import com.github.pwcong.jpstart.network.baidu.api.BaiduTranslateApi
import com.github.pwcong.jpstart.network.baidu.service.BaiduService.TranslateService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.Locale

class BaiduTranslateServiceImpl : TranslateService {
    override fun translate(
        q: String,
        from: String,
        to: String,
        subscriber: Subscriber<BaiduTranslateBean>
    ) {
        val salt = (Math.random() * 99999).toInt()
        val sign: String = EncryptUtils
            .encryptMD5ToString(BaiduTranslateApi.BAIDU_TRANSLATE_APPID + q + salt + BaiduTranslateApi.BAIDU_TRANSLATE_SECRETKEY)
            .lowercase(
                Locale.getDefault()
            )

        getInstance().create(
            BaiduTranslateApi::class.java
        )
            .request(q, from, to, BaiduTranslateApi.BAIDU_TRANSLATE_APPID, salt, sign)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
    }

    companion object {
        private var instance: Retrofit? = null

        @Synchronized
        fun getInstance(): Retrofit {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BaiduTranslateApi.BAIDU_TRANSLATE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
            }
            return instance!!
        }
    }

}
