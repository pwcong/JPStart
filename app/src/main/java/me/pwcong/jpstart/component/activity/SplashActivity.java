package me.pwcong.jpstart.component.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.manager.SoundPoolManager;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Pwcong on 2016/9/23.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected int getViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void doAction() {

        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onStart();
                DBManager.getInstance().init();
                SoundPoolManager.getInstance().init();
                subscriber.onNext(Constants.OK);

            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {

                if(integer==Constants.OK){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            }
        });
    }
}
