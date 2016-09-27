package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.adapter.SimpleRecyclerAdapter;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.JPStartFragmentPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragment extends BaseFragment implements BaseView.JPStartFragmentView{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    BasePresenter.JPStartFragmentPresenter presenter;

    int type_yin = 0;

    @Override
    protected int getViewId() {
        return R.layout.fragment_jpstart;
    }

    public static JPStartFragment newInstance(int type){

        Bundle argument=new Bundle();
        argument.putInt(Constants.TYPE_YIN,type);

        JPStartFragment fragment=new JPStartFragment();
        fragment.setArguments(argument);

        return fragment;

    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        type_yin = getArguments().getInt(Constants.TYPE_YIN);

        presenter=new JPStartFragmentPresenterImpl(this);

    }


    @Override
    protected void doAction() {
        presenter.initJPStartFragment(type_yin);
    }

    @Override
    public void setData(List<JPItem> data) {

        mRecyclerView.setAdapter(new SimpleRecyclerAdapter(data));

    }

    @Override
    public void setRecyclerView(int type) {

        switch (type){
            case Constants.TYPE_QINGYIN:
            case Constants.TYPE_ZHUOYIN:
                RecyclerView.LayoutManager layoutManager1=new GridLayoutManager(getContext(),5);
                mRecyclerView.setLayoutManager(layoutManager1);
                break;
            case Constants.TYPE_AOYIN:
                RecyclerView.LayoutManager layoutManager2=new GridLayoutManager(getContext(),3);
                mRecyclerView.setLayoutManager(layoutManager2);
                break;
            default:break;
        }

    }
}
