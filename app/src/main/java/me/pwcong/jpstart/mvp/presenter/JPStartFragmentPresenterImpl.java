package me.pwcong.jpstart.mvp.presenter;

import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.JPStartFragmentModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragmentPresenterImpl extends BasePresenter<BaseView.JPStartFragmentView> implements BasePresenter.JPStartFragmentPresenter {

    BaseModel.JPStartFragmentModel model;

    public JPStartFragmentPresenterImpl(BaseView.JPStartFragmentView view) {
        super(view);

        model=new JPStartFragmentModelImpl();
    }

    @Override
    public void initJPStartFragment(int type) {

        view.setRecyclerView(type);
        view.setData(model.getData(type));

    }
}
