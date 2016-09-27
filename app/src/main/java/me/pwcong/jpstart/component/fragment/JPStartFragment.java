package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

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


    }

    @Override
    protected void doAction() {

    }
}
