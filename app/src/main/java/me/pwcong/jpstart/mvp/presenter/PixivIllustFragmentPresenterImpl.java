package me.pwcong.jpstart.mvp.presenter;

import android.content.DialogInterface;

import java.util.List;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.bean.PixivIllustBean;
import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.PixivIllustFragmentModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.utils.ActivityUtils;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/10/3.
 */

public class PixivIllustFragmentPresenterImpl extends BasePresenter<BaseView.PixivIllustFragmentView> implements BasePresenter.PixivIllustFragmentPresenter {

    private BaseModel.PixivIllustFragmentModel model;


    public PixivIllustFragmentPresenterImpl(BaseView.PixivIllustFragmentView view) {
        super(view);
        model = new PixivIllustFragmentModelImpl();
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
                view.showMsg(R.string.loading_error);
                view.hideProgress();
            }

            @Override
            public void onNext(List<PixivIllustBean> list) {
                view.setData(list);
            }
        });

    }

    @Override
    public void onItemClick(final PixivIllustBean bean) {

        view.showOptionsDialog(model.getOptions(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {

                    case 0:
                        ActivityUtils.openUrl(bean.getLink());
                        break;
                    case 1:
                        view.showImg(bean.getImg_240x480(), bean.getId());
                        break;
                    case 2:
                        ActivityUtils.share(bean.getLink());
                        break;
                    default:
                        break;
                }

                dialog.dismiss();
            }
        });


    }
}
