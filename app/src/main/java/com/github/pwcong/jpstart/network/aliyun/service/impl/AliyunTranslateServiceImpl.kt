package com.github.pwcong.jpstart.network.aliyun.service.impl

import com.aliyun.auth.credentials.Credential
import com.aliyun.auth.credentials.provider.StaticCredentialProvider
import com.aliyun.sdk.service.alimt20181012.AsyncClient
import com.aliyun.sdk.service.alimt20181012.models.TranslateGeneralRequest
import com.aliyun.sdk.service.alimt20181012.models.TranslateGeneralResponse
import com.github.pwcong.jpstart.BuildConfig
import com.github.pwcong.jpstart.mvp.bean.TranslateBean
import com.github.pwcong.jpstart.network.aliyun.service.AliyunService
import com.github.pwcong.jpstart.network.aliyun.service.api.AliyunTranslateApi
import darabonba.core.client.ClientOverrideConfiguration
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.CompletableFuture


class AliyunTranslateServiceImpl : AliyunService.TranslateService {
    override fun translate(
        q: String,
        from: String,
        to: String,
        subscriber: Subscriber<TranslateBean>
    ) {
        Observable.create<TranslateBean> { emitter ->
            val translateGeneralRequest = TranslateGeneralRequest.builder()
                .formatType("text")
                .sourceLanguage(from)
                .scene("general")
                .targetLanguage(to)
                .sourceText(q)
                .build()

            val response: CompletableFuture<TranslateGeneralResponse> =
                getInstance().translateGeneral(translateGeneralRequest)

            emitter.onStart()
            response.thenAccept { resp ->
                emitter.onNext(TranslateBean(from, to, resp.body.data.translated, resp.body.code))
                emitter.onCompleted()
            }.exceptionally { err ->
                emitter.onError(err)
                null
            }

        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
    }

    companion object {
        private var instance: AsyncClient? = null

        @Synchronized
        fun getInstance(): AsyncClient {
            if (instance == null) {
                val provider = StaticCredentialProvider.create(
                    Credential.builder()
                        .accessKeyId(BuildConfig.ALIYUN_API_KEY)
                        .accessKeySecret(BuildConfig.ALIYUN_API_SECRET)
                        .build()
                );

                instance = AsyncClient.builder()
                    .region(AliyunTranslateApi.ALIYUN_TRANSLATE_REGION)
                    .credentialsProvider(provider)
                    .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                            .setEndpointOverride(AliyunTranslateApi.ALIYUN_TRANSLATE_HOST)
                    )
                    .build()
            }

            return instance!!
        }

    }
}