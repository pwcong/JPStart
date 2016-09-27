package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.adapter.JPStartTabPagerAdapter;
import me.pwcong.jpstart.mvp.bean.JPTab;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.JPStartTabFragmentPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/9/25.
 */

public class JPStartTabFragment extends BaseFragment implements BaseView.JPStartTabFragmentView {

    private final String TAG=getClass().getSimpleName();

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    BasePresenter.JPStartTabFragmentPresenter presenter;


    @Override
    protected int getViewId() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter=new JPStartTabFragmentPresenterImpl(this);

        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void doAction() {

        presenter.initJPStartTabFragment();

        Log.i(TAG, "doAction: OK");

    }

    @Override
    public void setData(List<JPTab> data) {

        mViewPager.setAdapter(new JPStartTabPagerAdapter(getChildFragmentManager(),data));

    }
}
