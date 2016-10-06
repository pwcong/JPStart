package me.pwcong.jpstart.component.fragment;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.amulyakhare.textdrawable.TextDrawable;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.adapter.MemorySwipeAdapter;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SoundPoolManager;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.MemoryFragmentPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.utils.ResourceUtils;
import me.pwcong.jpstart.widget.dialog.ImageDialog;
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


        TextDrawable textQing = TextDrawable.builder()
                .beginConfig()
                .fontSize((int) getContext().getResources().getDimension(R.dimen.memory_item_fab_text_size))
                .endConfig()
                .buildRound("清", getContext().getResources().getColor(R.color.green));


        FloatingActionButton fab_qingyin = new FloatingActionButton(getContext());
        fab_qingyin.setIconDrawable(textQing);
        fab_qingyin.setColorNormal(getContext().getResources().getColor(R.color.green));
        fab_qingyin.setColorPressed(getContext().getResources().getColor(R.color.window));
        fab_qingyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.setDate(Constants.CATEGORY_QINGYIN);
                category=Constants.CATEGORY_QINGYIN;
                hideFabMenu();

            }
        });

        TextDrawable textZhuo = TextDrawable.builder()
                .beginConfig()
                .fontSize((int) getContext().getResources().getDimension(R.dimen.memory_item_fab_text_size))
                .endConfig()
                .buildRound("浊", getContext().getResources().getColor(R.color.orange));

        FloatingActionButton fab_zhuoyin = new FloatingActionButton(getContext());
        fab_zhuoyin.setIconDrawable(textZhuo);
        fab_zhuoyin.setColorNormal(getContext().getResources().getColor(R.color.orange));
        fab_zhuoyin.setColorPressed(getContext().getResources().getColor(R.color.window));
        fab_zhuoyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setDate(Constants.CATEGORY_ZHUOYIN);
                category=Constants.CATEGORY_ZHUOYIN;
                hideFabMenu();
            }
        });

        TextDrawable textAo = TextDrawable.builder()
                .beginConfig()
                .fontSize((int) getContext().getResources().getDimension(R.dimen.memory_item_fab_text_size))
                .endConfig()
                .buildRound("拗", getContext().getResources().getColor(R.color.blue));

        FloatingActionButton fab_aoyin = new FloatingActionButton(getContext());
        fab_aoyin.setIconDrawable(textAo);
        fab_aoyin.setColorNormal(getContext().getResources().getColor(R.color.blue));
        fab_aoyin.setColorPressed(getContext().getResources().getColor(R.color.window));
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

        if(adapter==null){
            adapter=new MemorySwipeAdapter(data);

            adapter.setOnWriteButtonClickListener(new MemorySwipeAdapter.OnWriteButtonClickListener() {
                @Override
                public void onClick(JPItem item) {

                    new ImageDialog.Builder(getContext())
                            .setResId(R.raw.write_a)
                            .override((int)ResourceUtils.getDimension(getContext(),R.dimen.dialog_width),
                                    (int)ResourceUtils.getDimension(getContext(),R.dimen.dialog_height))
                            .create()
                            .show();

                }
            });

            adapter.setOnYinButtonClickListener(new MemorySwipeAdapter.OnYinButtonClickListener() {
                @Override
                public void onClick(JPItem item) {
                    SoundPoolManager.getInstance().play(item.getRome());
                }
            });




        }
        adapter.setList(data);
        mSwipeFlingAdapterView.setAdapter(adapter);

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
