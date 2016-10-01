package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.adapter.JPStartRecyclerAdapter;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SoundPoolManager;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.JPStartFragmentPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.utils.ResourceUtils;
import me.pwcong.jpstart.widget.ImageDialog;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragment extends BaseFragment implements BaseView.JPStartFragmentView{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    BasePresenter.JPStartFragmentPresenter presenter;

    JPStartRecyclerAdapter adapter;

    int category_yin = 0;

    @Override
    protected int getViewId() {
        return R.layout.fragment_jpstart;
    }

    public static JPStartFragment newInstance(int type){

        Bundle argument=new Bundle();
        argument.putInt(Constants.CATEGORY_YIN,type);

        JPStartFragment fragment=new JPStartFragment();
        fragment.setArguments(argument);

        return fragment;

    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        category_yin = getArguments().getInt(Constants.CATEGORY_YIN);

        presenter=new JPStartFragmentPresenterImpl(this);

    }


    @Override
    protected void doAction() {
        presenter.initJPStartFragment(category_yin);
    }

    @Override
    public void setData(List<JPItem> data) {

        adapter=new JPStartRecyclerAdapter(data);
        adapter.setOnItemClickListener(new JPStartRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(JPItem item) {
                SoundPoolManager.getInstance().play(item.getRome());
            }
        });

        adapter.setOnItemLongClickListener(new JPStartRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(JPItem item) {
                new ImageDialog.Builder(getContext())
                        .setResId(R.raw.write_a)
                        .override((int)ResourceUtils.getDimension(getContext(),R.dimen.dialog_width),
                                (int)ResourceUtils.getDimension(getContext(),R.dimen.dialog_height))
                        .create()
                        .show();
            }
        });

        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void setRecyclerView(int type) {

        switch (type){
            case Constants.CATEGORY_QINGYIN:
                RecyclerView.LayoutManager layoutManager1=new GridLayoutManager(getContext(),Constants.COLUMN_QINGYIN);
                mRecyclerView.setLayoutManager(layoutManager1);
            case Constants.CATEGORY_ZHUOYIN:
                RecyclerView.LayoutManager layoutManager2=new GridLayoutManager(getContext(),Constants.COLUMN_ZHUOYIN);
                mRecyclerView.setLayoutManager(layoutManager2);
                break;
            case Constants.CATEGORY_AOYIN:
                RecyclerView.LayoutManager layoutManager3=new GridLayoutManager(getContext(),Constants.COLUMN_AOYIN);
                mRecyclerView.setLayoutManager(layoutManager3);
                break;
            default:break;
        }

    }
}
