package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.PixivIllustBean;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.PixivIllustFragmentPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/9/28.
 */

public class PixivIllustFragment extends BaseFragment implements BaseView.PixivIllustFragmentView{

    private final String TAG=getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    String mode_illust;

    BasePresenter.PixivIllustFragmentPresenter presenter;

    public static PixivIllustFragment newInstance(String mode){

        Bundle arguments=new Bundle();
        arguments.putString(Constants.MODE_ILLUST,mode);

        PixivIllustFragment fragment=new PixivIllustFragment();
        fragment.setArguments(arguments);
        return fragment;

    }


    @Override
    protected int getViewId() {
        return R.layout.fragment_pixivillust;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        mode_illust=getArguments().getString(Constants.MODE_ILLUST);

        presenter=new PixivIllustFragmentPresenterImpl(this);

        initRefreshLayout();

    }


    private void initRefreshLayout(){

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.reloadData(mode_illust);
            }
        });

    }


    @Override
    protected void doAction() {
        presenter.initPixivIllustFragment(mode_illust);
    }

    @Override
    public void showMsg(int msg) {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setData(List<PixivIllustBean> data) {

        for (PixivIllustBean bean:data){
            Log.i(TAG, "setData: "+bean.toString());

        }


    }
}
