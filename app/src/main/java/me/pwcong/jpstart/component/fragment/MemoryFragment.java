package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.adapter.MemorySwipeAdapter;
import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.widget.swipecardview.SwipeFlingAdapterView;

/**
 * Created by Pwcong on 2016/9/29.
 */

public class MemoryFragment extends BaseFragment {

    @BindView(R.id.swipe_view)
    SwipeFlingAdapterView mSwipeFlingAdapterView;

    MemorySwipeAdapter adapter;


    @Override
    protected int getViewId() {
        return R.layout.fragment_memory;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        adapter = new MemorySwipeAdapter(DBManager.getInstance().getQingYinWithoutHeader());

        mSwipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                adapter.remove(0);
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

        mSwipeFlingAdapterView.setAdapter(adapter);


    }

    @Override
    protected void doAction() {

    }
}
