package me.pwcong.jpstart.mvp.presenter;

import android.content.DialogInterface;
import android.os.Bundle;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SharedPreferenceManager;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.rxbus.event.EventContainer;
import me.pwcong.jpstart.rxbus.event.PhotoViewEvent;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class MainActivityPresenterImpl extends BasePresenter<BaseView.MainActivityView> implements BasePresenter.MainActivityPresenter {

    public MainActivityPresenterImpl(BaseView.MainActivityView view) {
        super(view);
    }

    @Override
    public void initMainActivity() {

        onNavigationItemSelected(R.id.item_jpstart);

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

                if(SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_JPSTART,true)){

                    view.showAlertDialog(R.string.small_tips,
                            R.string.tips_jpstart, R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_JPSTART,false);
                                }
                            });

                }

                break;
            case R.id.item_memory:
                view.switchMemory();
                break;
            case R.id.item_translate:

                view.switchTranslate();

                if(SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_TRANSLATE,true)){

                    view.showAlertDialog(R.string.small_tips,
                            R.string.tips_translate, R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_TRANSLATE,false);
                                }
                            });

                }


                break;
            case R.id.item_pixiv_illust:

                view.switchPixivIllust();

                if(SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_PIXIVILLUST,true)){

                    view.showAlertDialog(R.string.small_tips, R.string.tips_pixivillust,
                            R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_PIXIVILLUST,false);
                                }
                            });


                }


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

    @Override
    public void onBusEventInteraction(EventContainer eventContainer) {

        switch (eventContainer.getType()){

            case EventContainer.TYPE_PHOTOVIEW:

                PhotoViewEvent event= (PhotoViewEvent) eventContainer.getEvent();

                Bundle bundle=new Bundle();
                bundle.putString(Constants.IMG_URL,event.getImg_url());
                bundle.putInt(Constants.IMG_ID,event.getImg_id());

                view.startPhotoViewActivity(bundle);

                break;
            default:break;


        }


    }
}
