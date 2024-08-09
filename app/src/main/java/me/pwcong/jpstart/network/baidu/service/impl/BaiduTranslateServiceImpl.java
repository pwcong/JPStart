package me.pwcong.jpstart.network.baidu.service.impl;

import com.blankj.utilcode.util.EncryptUtils;

import me.pwcong.jpstart.mvp.bean.BaiduTranslateBean;
import me.pwcong.jpstart.network.Api;
import me.pwcong.jpstart.network.baidu.BaiduTranslateApi;
import me.pwcong.jpstart.network.baidu.service.BaiduService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pwcong on 2016/10/1.
 */

public class BaiduTranslateServiceImpl implements BaiduService.TranslateService {

    private static Retrofit instance = null;

    public static synchronized Retrofit getInstance() {

        if (instance == null) {

            instance = new Retrofit.Builder()
                    .baseUrl(Api.BAIDU_TRANSLATE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

        }
        return instance;
    }

    @Override
    public void translate(String q, String from, String to, Subscriber<BaiduTranslateBean> subscriber) {

        int salt = (int) (Math.random() * 99999);
        String sign = EncryptUtils
                .encryptMD5ToString(Api.BAIDU_TRANSLATE_APPID + q + salt + Api.BAIDU_TRANSLATE_SECRETKEY).toLowerCase();

        getInstance().create(BaiduTranslateApi.class)
                .request(q, from, to, Api.BAIDU_TRANSLATE_APPID, salt, sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);

    }
}
