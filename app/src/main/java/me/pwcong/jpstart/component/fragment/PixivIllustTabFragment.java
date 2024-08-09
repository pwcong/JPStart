package me.pwcong.jpstart.component.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.adapter.PixivIllustTabPagerAdapter;
import me.pwcong.jpstart.mvp.bean.PixivIllustTab;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.PixivIllustTabFragmentPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/9/25.
 */

public class PixivIllustTabFragment extends BaseFragment implements BaseView.PixivIllustTabFragmentView {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    BasePresenter.PixivIllustTabFragmentPresenter presenter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_pixivillust_tab;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter = new PixivIllustTabFragmentPresenterImpl(this);

        initTabLayout();
        initViewPager();

    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViewPager() {
        // doo something
    }

    @Override
    protected void doAction() {

        presenter.initPixivIllustTabFragment();

        Log.i(TAG, "doAction: OK");

    }

    @Override
    public void setData(List<PixivIllustTab> data) {
        mViewPager.setAdapter(new PixivIllustTabPagerAdapter(getChildFragmentManager(), data));
    }

    @Override
    public void showAlertDialog(int titleId, int messageId, int positiveTextId,
            DialogInterface.OnClickListener positiveButtonListener, int negativeTextId,
            DialogInterface.OnClickListener negativeButtonListener) {
        new AlertDialog.Builder(getContext())
                .setTitle(titleId)
                .setMessage(messageId)
                .setCancelable(false)
                .setPositiveButton(positiveTextId, positiveButtonListener)
                .setNegativeButton(negativeTextId, negativeButtonListener)
                .setIcon(R.drawable.ic_lightbulb_outline_black_24dp)
                .create()
                .show();
    }

    @Override
    public void showMsg(String msg) {
        showSnackBar(mTabLayout, msg);
    }

    @Override
    public void showMsg(int msg) {
        showSnackBar(mTabLayout, msg);
    }

}
