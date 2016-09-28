package me.pwcong.jpstart.mvp.presenter;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SharedPreferenceManager;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class MainActivityPresenterImpl extends BasePresenter<BaseView.MainActivityView> implements BasePresenter.MainActivityPresenter {

    public MainActivityPresenterImpl(BaseView.MainActivityView view) {
        super(view);
    }

    @Override
    public void initMainActivity() {

        view.switchJPStart();

    }

    @Override
    public void onRadioButtonChanged(int position) {
        switch (position){

            case 0:
                App.TYPE_MING=Constants.TYPE_HIRAGANA;
                break;
            case 1:
                App.TYPE_MING=Constants.TYPE_KATAKANA;
                break;
            default:break;
        }

        view.switchJPStart();
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
