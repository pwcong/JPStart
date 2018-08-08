package me.pwcong.jpstart.mvp.presenter;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.MemoryFragmentModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/10/5.
 */

public class MemoryFragmentPresenterImpl extends BasePresenter<BaseView.MemoryFragmentView> implements BasePresenter.MemoryFragmentPresenter {

    private BaseModel.MemoryFragmentModel model;

    public MemoryFragmentPresenterImpl(BaseView.MemoryFragmentView view) {
        super(view);
        model = new MemoryFragmentModelImpl();
    }

    @Override
    public void initMemoryFragment() {
        setDate(Constants.CATEGORY_QINGYIN);
    }

    @Override
    public void loadMore(int categiry) {

        setDate(categiry);
        view.showMsg(R.string.one_more_time);
    }

    @Override
    public void setDate(int category) {

        switch (category) {

            case Constants.CATEGORY_QINGYIN:

                view.setData(model.getQingYinWithoutHeader());

                break;
            case Constants.CATEGORY_ZHUOYIN:

                view.setData(model.getZhuoYinWithoutHeader());

                break;
            case Constants.CATEGORY_AOYIN:

                view.setData(model.getAoYinWithoutHeader());

                break;
            default:
                break;


        }

    }


}
