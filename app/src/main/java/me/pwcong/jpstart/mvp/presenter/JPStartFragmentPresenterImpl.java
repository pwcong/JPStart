package me.pwcong.jpstart.mvp.presenter;

import android.util.Log;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.JPStartFragmentModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragmentPresenterImpl extends BasePresenter<BaseView.JPStartFragmentView>
        implements BasePresenter.JPStartFragmentPresenter {

    private final String TAG = getClass().getSimpleName();

    private BaseModel.JPStartFragmentModel model;

    public JPStartFragmentPresenterImpl(BaseView.JPStartFragmentView view) {
        super(view);

        model = new JPStartFragmentModelImpl();
    }

    @Override
    public void initJPStartFragment(int type) {

        view.setRecyclerView(type);
        model.getData(type, new Subscriber<List<JPItem>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: OK");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: OK");
            }

            @Override
            public void onNext(List<JPItem> list) {
                view.setData(list);
                Log.i(TAG, "onNext: OK");
            }
        });

    }
}
