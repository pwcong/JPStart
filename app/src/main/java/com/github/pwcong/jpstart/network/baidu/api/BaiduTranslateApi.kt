package com.github.pwcong.jpstart.network.baidu.api

import com.github.pwcong.jpstart.mvp.bean.BaiduTranslateBean
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface BaiduTranslateApi {
    @GET("/api/trans/vip/translate")
    fun request(
        @Query("q") q: String?, @Query("from") from: String?, @Query("to") to: String?,
        @Query("appid") appId: String?, @Query("salt") salt: Int, @Query("sign") sign: String?
    ): Observable<BaiduTranslateBean>

    companion object {
        const val BAIDU_TRANSLATE_URL: String = "http://api.fanyi.baidu.com"
        const val BAIDU_TRANSLATE_APPID: String = "******"
        const val BAIDU_TRANSLATE_SECRETKEY: String = "******"

        const val AUTO: String = "auto"
        const val ZH: String = "zh"
        const val JP: String = "jp"
        const val EN: String = "en"
    }
}
