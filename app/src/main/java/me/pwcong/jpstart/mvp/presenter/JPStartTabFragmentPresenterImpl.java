package me.pwcong.jpstart.mvp.presenter;

import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SharedPreferenceManager;
import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.JPStartTabFragmentModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartTabFragmentPresenterImpl extends BasePresenter<BaseView.JPStartTabFragmentView> implements BasePresenter.JPStartTabFragmentPresenter {

    BaseModel.JPStartTabFragmentModel model;

    public JPStartTabFragmentPresenterImpl(BaseView.JPStartTabFragmentView view) {
        super(view);
        model=new JPStartTabFragmentModelImpl();
    }

    @Override
    public void initJPStartTabFragment() {
        view.setData(model.getData());
        view.scrollViewPager(SharedPreferenceManager.getInstance().getInt(Constants.CURRENT_ITEM,0));
    }
}
