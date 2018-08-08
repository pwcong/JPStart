package me.pwcong.jpstart.component.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        ActivityManager.setCurrent(this);

        setContentView(getViewId());

        ButterKnife.bind(this);

        initVariable(savedInstanceState);

        doAction();

    }

    protected abstract int getViewId();

    protected abstract void initVariable(@Nullable Bundle savedInstanceState);

    protected abstract void doAction();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityManager.getInstance().unregister(this);

    }

    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackBar(View view, int msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.setCurrent(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
