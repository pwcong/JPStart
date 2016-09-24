package me.pwcong.jpstart.component.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import me.pwcong.jpstart.manager.ActivityManager;

/**
 * Created by Pwcong on 2016/9/23.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.getInstance().register(this);

        setContentView(setView());

        ButterKnife.bind(this);

        initVariable();
        doAction();

    }

    protected abstract int setView();

    protected abstract void initVariable();

    protected abstract void doAction();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityManager.getInstance().unregister(this);

    }
}
