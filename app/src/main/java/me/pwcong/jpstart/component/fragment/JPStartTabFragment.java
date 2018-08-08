package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.App;
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


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private BasePresenter.JPStartTabFragmentPresenter presenter;


    @Override
    protected int getViewId() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter=new JPStartTabFragmentPresenterImpl(this);

        initTabLayout();
        initViewPager();

    }

    private void initTabLayout(){
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViewPager(){

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                App.CURRENT_ITEM = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    protected void doAction() {

        presenter.initJPStartTabFragment();

    }

    @Override
    public void setData(List<JPTab> data) {

        mViewPager.setAdapter(new JPStartTabPagerAdapter(getChildFragmentManager(),data));

    }

    @Override
    public void scrollViewPager(int position) {
        mViewPager.setCurrentItem(position,true);
    }
}
