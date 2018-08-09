package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.rxbus.RxBus;
import me.pwcong.jpstart.rxbus.event.EventContainer;
import me.pwcong.jpstart.rxbus.event.GameEvent;

/**
 * Created by Pwcong on 2016/10/24.
 */

public class GameFragment extends BaseFragment {

    @BindView(R.id.cv_puzzle)
    CardView mPuzzleCardView;
    @BindView(R.id.cv_supperzzle)
    CardView mSupperzzleCardView;

    @Override
    protected int getViewId() {
        return R.layout.fragment_game;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        mPuzzleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().post(new EventContainer(EventContainer.TYPE_GAME, new GameEvent(GameEvent.TYPE_PUZZLE)));
            }
        });

        mSupperzzleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().post(new EventContainer(EventContainer.TYPE_GAME, new GameEvent(GameEvent.TYPE_SUPPERZZLE)));
            }
        });

    }

    @Override
    protected void doAction() {

    }


}
