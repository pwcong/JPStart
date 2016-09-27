package me.pwcong.jpstart.component.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.component.fragment.JPStartTabFragment;
import me.pwcong.jpstart.manager.ActivityManager;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.MainActivityPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.utils.ResourceUtils;
import me.pwcong.radiobuttonview.view.RadioButtonView;

public class MainActivity extends BaseActivity implements BaseView.MainActivityView {

    private final String TAG=getClass().getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    RadioButtonView mRadioButtonView;

    private long mExitTime;
    private BasePresenter.MainActivityPresenter presenter;

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter=new MainActivityPresenterImpl(this);

        initToolbar();
        initRadioButtonView();
        initDrawerLayout();
        initNavigationView();


    }

    private void initToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Log.i(TAG, "initToolbar: OK");
    }

    private void initRadioButtonView(){

        mRadioButtonView = (RadioButtonView) getLayoutInflater().inflate(R.layout.button_radio,mToolbar,false);
        mRadioButtonView.setOptions(getRadioButtonOptions());
        mRadioButtonView.setOnRadioButtonChangedListener(new RadioButtonView.OnRadioButtonChangedListener() {
            @Override
            public void onRadioButtonChanged(String s, int i) {
                presenter.onRadioButtonChanged(i);
            }
        });

        Toolbar.LayoutParams params=new Toolbar.LayoutParams(120,52,GravityCompat.END);
        mToolbar.addView(mRadioButtonView,params);

        Log.i(TAG, "initRadioButtonView: OK");
    }

    private void initDrawerLayout(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Log.i(TAG, "initDrawerLayout: OK");
    }

    private void initNavigationView(){
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                presenter.onNavigationItemSelected(item.getItemId());
                Log.i(TAG, "onNavigationItemSelected: OK");

                return true;
            }
        });
        mNavigationView.setCheckedItem(R.id.item_jpstart);

        Log.i(TAG, "initNavigationView: OK");
    }

    @Override
    protected void doAction() {

        presenter.initMainActivity();

        Log.i(TAG, "doAction: OK");
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {

                showSnackBar(mToolbar,"再按一次退出程序");

                mExitTime = System.currentTimeMillis();

            } else {
                ActivityManager.getInstance().finishAll();
            }
        }
    }


    @Override
    public void openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
        Log.i(TAG, "openDrawer: OK");
    }

    @Override
    public void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        Log.i(TAG, "closeDrawer: OK");
    }

    @Override
    public void switchJPStart() {

        mRadioButtonView.setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,new JPStartTabFragment()).commit();

        Log.i(TAG, "switchJPStart: OK");
    }

    @Override
    public void switchTranslate() {

        mRadioButtonView.setVisibility(View.GONE);
        Log.i(TAG, "switchTranslate: OK");
    }

    @Override
    public void switchPixiv() {

        mRadioButtonView.setVisibility(View.GONE);
        Log.i(TAG, "switchPixiv: OK");
    }

    @Override
    public void switchSetting() {

        Log.i(TAG, "switchSetting: OK");
    }

    @Override
    public void switchAbout() {

        Log.i(TAG, "switchAbout: OK");
    }


    private ArrayList<String> getRadioButtonOptions(){

        ArrayList<String> options = new ArrayList<>();
        options.add(ResourceUtils.getString(MainActivity.this,R.string.hiragana));
        options.add(ResourceUtils.getString(MainActivity.this,R.string.katakana));
        return options;

    }
}
