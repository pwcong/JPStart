package me.pwcong.jpstart.component.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.utils.ActivityUtils;

/**
 * Created by Pwcong on 2016/10/6.
 */

public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.btn_pwcong)
    Button mPwcongButton;
    @BindView(R.id.btn_panda)
    Button mPandaButton;

    @Override
    protected int getViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        initToolbar();
        initButton();
    }

    private void initToolbar(){

        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initButton(){

        mPwcongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openUrl(Constants.URL_PWCONG);
            }
        });

        mPandaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openUrl(Constants.URL_PANDA);
            }
        });

    }


    @Override
    protected void doAction() {

    }
}
