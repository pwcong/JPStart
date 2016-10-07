package me.pwcong.jpstart.component.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.BindView;
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

    @BindView(R.id.tv_tips)
    TextView mTextView;

    @Override
    protected int getViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void doAction() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onStart();
                subscriber.onNext("正在加载数据…");
                DBManager.getInstance().init();
                SoundPoolManager.getInstance().init();
                subscriber.onNext("加载完成！");
                subscriber.onCompleted();

            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onError(Throwable e) {
                mTextView.setText("数据加载错误，请重新启动！");
            }

            @Override
            public void onNext(String s) {
                mTextView.setText(s);
            }
        });
    }
}
