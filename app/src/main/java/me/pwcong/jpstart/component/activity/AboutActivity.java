package me.pwcong.jpstart.component.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import me.pwcong.jpstart.R;

/**
 * Created by Pwcong on 2016/10/6.
 */

public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {


        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void doAction() {

    }
}
