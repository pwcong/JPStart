package me.pwcong.jpstart.mvp.presenter;

import android.content.DialogInterface;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SharedPreferenceManager;
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

        if(App.ISWIFI){

            view.setData(model.getData());


        }else {

            if(SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_WIFI,true)){

                view.showAlertDialog(R.string.warn,
                        R.string.warn_no_wifi, R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_WIFI,false);
                                SharedPreferenceManager.getInstance().putBoolean(Constants.ALLOW_CONNECT_WITHOUT_WIFI,true);
                                dialog.dismiss();
                                view.setData(model.getData());
                            }
                        }, R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_WIFI,false);
                                SharedPreferenceManager.getInstance().putBoolean(Constants.ALLOW_CONNECT_WITHOUT_WIFI,false);
                                dialog.dismiss();
                                view.showMsg(R.string.loading_disallow_pixiv);
                            }
                        });

            }else {

                if(SharedPreferenceManager.getInstance().getBoolean(Constants.ALLOW_CONNECT_WITHOUT_WIFI,false)){

                    view.setData(model.getData());

                }else {
                    view.showMsg(R.string.loading_disallow_pixiv);
                }

            }

        }



    }
}
