package me.pwcong.jpstart.component.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.adapter.PixivIllustRecyclerAdapter;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.PixivIllustBean;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.PixivIllustFragmentPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.rxbus.RxBus;
import me.pwcong.jpstart.rxbus.event.EventContainer;
import me.pwcong.jpstart.rxbus.event.PhotoViewEvent;

/**
 * Created by Pwcong on 2016/9/28.
 */

public class PixivIllustFragment extends BaseFragment implements BaseView.PixivIllustFragmentView{


    @BindView(R.id.layout_root)
    LinearLayout mRootLayout;
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

        initRecyclerView();
        initRefreshLayout();

    }

    private void initRecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);

    }


    private void initRefreshLayout(){

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.blue,R.color.orange,R.color.amber,R.color.green,R.color.purple);

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
        showSnackBar(mRootLayout,msg);
    }

    @Override
    public void showMsg(String msg) {
        showSnackBar(mRootLayout,msg);
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
    public void showOptionsDialog(String[] options, DialogInterface.OnClickListener listener) {

        new AlertDialog.Builder(getContext()).setItems(options, listener).create().show();

    }

    @Override
    public void showImg(String url,int id) {

        PhotoViewEvent event=new PhotoViewEvent(url,id);
        RxBus.getDefault().post(new EventContainer(EventContainer.TYPE_PHOTOVIEW,event));


    }


    @Override
    public void setData(List<PixivIllustBean> data) {

        PixivIllustRecyclerAdapter adapter=new PixivIllustRecyclerAdapter(getContext(),data);
        adapter.setOnItemClickListener(new PixivIllustRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PixivIllustBean bean) {
                presenter.onItemClick(bean);
            }
        });

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.refreshDrawableState();

    }

}
