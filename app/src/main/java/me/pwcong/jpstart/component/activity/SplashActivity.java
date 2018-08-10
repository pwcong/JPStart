package me.pwcong.jpstart.component.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.manager.GifManager;
import me.pwcong.jpstart.manager.SoundPoolManager;
import me.pwcong.jpstart.utils.ResourceUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
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
                subscriber.onNext(ResourceUtils.getString(SplashActivity.this, R.string.loading_data));
                DBManager.getInstance().init();
                SoundPoolManager.getInstance().init();
                GifManager.getInstance().init();
                subscriber.onNext(ResourceUtils.getString(SplashActivity.this, R.string.loading_data_success));
                subscriber.onCompleted();

            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mTextView.setText(ResourceUtils.getString(SplashActivity.this, R.string.loading_data_error));
                    }

                    @Override
                    public void onNext(String s) {
                        mTextView.setText(s);
                    }
                });
    }

    private void hideState() {

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

    }
}
