package me.pwcong.jpstart.network.pixiv.service;

import me.pwcong.jpstart.network.Api;
import me.pwcong.jpstart.network.pixiv.PixivIllustApi;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pwcong on 2016/10/2.
 */

public class PixivIllustServiceImpl implements PixivService.IllustService {

    private static Retrofit instance = null;

    public static synchronized Retrofit getInstance(){

        if (instance==null){

            instance=new Retrofit.Builder()
                    .baseUrl(Api.PIXIV_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return instance;
    }


    @Override
    public void getIllusts(String mode, Subscriber<ResponseBody> subscriber) {

        getInstance().create(PixivIllustApi.class)
                .requestIllusts(mode, PixivIllustApi.CONTENT_ILLUST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    @Override
    public void getIllust(int id, Subscriber<ResponseBody> subscriber) {

        getInstance().create(PixivIllustApi.class)
                .requestIllust(PixivIllustApi.MODE_MEDIUM,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}
