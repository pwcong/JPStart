package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.adapter.MemorySwipeAdapter;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.MemoryFragmentPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.utils.ResourceUtils;
import me.pwcong.jpstart.widget.swipecardview.SwipeFlingAdapterView;

/**
 * Created by Pwcong on 2016/9/29.
 */

public class MemoryFragment extends BaseFragment implements BaseView.MemoryFragmentView{

    @BindView(R.id.layout_root)
    RelativeLayout mRootLayout;
    @BindView(R.id.swipe_view)
    SwipeFlingAdapterView mSwipeFlingAdapterView;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu mFabMenu;

    MemorySwipeAdapter adapter;
    BasePresenter.MemoryFragmentPresenter presenter;

    int category = Constants.CATEGORY_QINGYIN;

    @Override
    protected int getViewId() {
        return R.layout.fragment_memory;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter=new MemoryFragmentPresenterImpl(this);

        initSwipeFlingAdapterView();
        initFabMenu();

    }


    private void initSwipeFlingAdapterView(){
        mSwipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                adapter.remove(0);
                if (adapter.isEmpty()){
                    presenter.loadMore(category);
                }
            }

            @Override
            public void onLeftCardExit(Object dataObject) {

            }

            @Override
            public void onRightCardExit(Object dataObject) {

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float progress, float scrollXProgress) {

            }
        });
    }

    private void initFabMenu(){

        FloatingActionButton fab_qingyin = new FloatingActionButton(getContext());
        fab_qingyin.setTitle(ResourceUtils.getString(getContext(),R.string.qingyin));
        fab_qingyin.setColorNormal(getContext().getResources().getColor(R.color.green));
        fab_qingyin.setColorPressed(getContext().getResources().getColor(R.color.window));
        fab_qingyin.setSize(FloatingActionButton.SIZE_MINI);
        fab_qingyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.setDate(Constants.CATEGORY_QINGYIN);
                category=Constants.CATEGORY_QINGYIN;
                hideFabMenu();

            }
        });

        FloatingActionButton fab_zhuoyin = new FloatingActionButton(getContext());
        fab_zhuoyin.setTitle(ResourceUtils.getString(getContext(),R.string.zhuoyin));
        fab_zhuoyin.setColorNormal(getContext().getResources().getColor(R.color.orange));
        fab_zhuoyin.setColorPressed(getContext().getResources().getColor(R.color.window));
        fab_zhuoyin.setSize(FloatingActionButton.SIZE_MINI);
        fab_zhuoyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDate(Constants.CATEGORY_ZHUOYIN);
                category=Constants.CATEGORY_ZHUOYIN;
                hideFabMenu();
            }
        });

        FloatingActionButton fab_aoyin = new FloatingActionButton(getContext());
        fab_aoyin.setTitle(ResourceUtils.getString(getContext(),R.string.aoyin));
        fab_aoyin.setColorNormal(getContext().getResources().getColor(R.color.blue));
        fab_aoyin.setColorPressed(getContext().getResources().getColor(R.color.window));
        fab_aoyin.setSize(FloatingActionButton.SIZE_MINI);
        fab_aoyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDate(Constants.CATEGORY_AOYIN);
                category=Constants.CATEGORY_AOYIN;
                hideFabMenu();
            }
        });

        mFabMenu.addButton(fab_qingyin);
        mFabMenu.addButton(fab_zhuoyin);
        mFabMenu.addButton(fab_aoyin);

    }


    @Override
    protected void doAction() {
        presenter.initMemoryFragment();
    }

    @Override
    public void setData(List<JPItem> data) {

        adapter=new MemorySwipeAdapter(data);
        adapter.notifyDataSetChanged();
        mSwipeFlingAdapterView.setAdapter(adapter);
        mSwipeFlingAdapterView.refreshDrawableState();

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
    public void hideFabMenu() {
        mFabMenu.collapse();
    }

}
