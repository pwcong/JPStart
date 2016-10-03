package me.pwcong.jpstart.mvp.presenter;

import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.PixivIllustTabFragmentModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/10/3.
 */

public class PixivIllustTabFragmentPresenterImpl extends BasePresenter<BaseView.PixivIllustTabFragmentView> implements BasePresenter.PixivIllustTabFragmentPresenter {


    BaseModel.PixivIllustTabFragmentModel model;

    public PixivIllustTabFragmentPresenterImpl(BaseView.PixivIllustTabFragmentView view) {
        super(view);
        model=new PixivIllustTabFragmentModelImpl();
    }

    @Override
    public void initPixivIllustTabFragment() {

        view.setData(model.getData());

    }
}
