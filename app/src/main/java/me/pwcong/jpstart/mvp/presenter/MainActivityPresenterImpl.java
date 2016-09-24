package me.pwcong.jpstart.mvp.presenter;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class MainActivityPresenterImpl extends BasePresenter<BaseView.MainActivityView> implements BasePresenter.MainActivityPresenter {

    public MainActivityPresenterImpl(BaseView.MainActivityView view) {
        super(view);
    }

    @Override
    public void onNavigationItemSelected(int id) {

        switch (id){
            case R.id.item_jpstart:
                view.switchJPStart();
                break;
            case R.id.item_translate:
                view.switchTranslate();
                break;
            case R.id.item_pixiv:
                view.switchPixiv();
                break;
            case R.id.item_setting:
                view.switchSetting();
                break;
            case R.id.item_about:
                view.switchAbout();
                break;
            default:break;

        }

        view.closeDrawer();


    }
}
