package me.pwcong.jpstart.component.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.manager.SoundPoolManager;
import rx.Subscriber;

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

        SoundPoolManager.getInstance().init(new Subscriber<Void>() {
            @Override
            public void onCompleted() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {

            }
        });

    }
}
