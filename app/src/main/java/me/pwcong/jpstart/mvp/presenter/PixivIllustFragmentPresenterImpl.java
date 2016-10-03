package me.pwcong.jpstart.mvp.presenter;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.PixivIllustBean;
import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.PixivIllustFragmentModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/10/3.
 */

public class PixivIllustFragmentPresenterImpl extends BasePresenter<BaseView.PixivIllustFragmentView> implements BasePresenter.PixivIllustFragmentPresenter{

    BaseModel.PixivIllustFragmentModel model;


    public PixivIllustFragmentPresenterImpl(BaseView.PixivIllustFragmentView view) {
        super(view);
        model=new PixivIllustFragmentModelImpl();
    }

    @Override
    public void initPixivIllustFragment(String mode) {

        reloadData(mode);

    }

    @Override
    public void reloadData(String mode) {

        view.showProgress();

        model.getIllusts(mode, new Subscriber<List<PixivIllustBean>>() {
            @Override
            public void onCompleted() {
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<PixivIllustBean> list) {
                view.setData(list);
            }
        });

    }
}
