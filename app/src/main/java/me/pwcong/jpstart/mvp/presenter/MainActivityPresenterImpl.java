package me.pwcong.jpstart.mvp.presenter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SharedPreferenceManager;
import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.MainActivityModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.rxbus.event.EventContainer;
import me.pwcong.jpstart.rxbus.event.GameEvent;
import me.pwcong.jpstart.rxbus.event.PhotoViewEvent;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class MainActivityPresenterImpl extends BasePresenter<BaseView.MainActivityView> implements BasePresenter.MainActivityPresenter {

    private final String TAG = getClass().getSimpleName();

    private BaseModel.MainActivityModel model;

    public MainActivityPresenterImpl(BaseView.MainActivityView view) {
        super(view);
        model = new MainActivityModelImpl();
    }

    @Override
    public void initMainActivity() {

        onNavigationItemSelected(R.id.item_jpstart);
        view.setViewPager(model.getData());

    }

    @Override
    public void onRadioButtonChanged(int position) {
        switch (position) {

            case 0:
                App.TYPE_MING = Constants.TYPE_HIRAGANA;
                break;
            case 1:
                App.TYPE_MING = Constants.TYPE_KATAKANA;
                break;
            default:
                break;
        }

        view.switchJPStart();
    }

    @Override
    public void onNavigationItemSelected(int id) {

        switch (id) {
            case R.id.item_jpstart:

                view.switchJPStart();

                if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_JPSTART, true)) {

                    view.showAlertDialog(R.string.small_tips,
                            R.string.tips_jpstart, R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_JPSTART, false);
                                    dialog.dismiss();
                                }
                            });

                }

                break;
            case R.id.item_memory:

                if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_MEMORY, true)) {

                    view.showAlertDialog(R.string.small_tips,
                            R.string.tips_memory, R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_MEMORY, false);
                                    dialog.dismiss();
                                }
                            });

                }

                view.switchMemory();
                break;
            case R.id.item_translate:

                view.switchTranslate();

                if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_TRANSLATE, true)) {

                    view.showAlertDialog(R.string.small_tips,
                            R.string.tips_translate, R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_TRANSLATE, false);
                                    dialog.dismiss();
                                }
                            });

                }

                break;
//            case R.id.item_pixiv_illust:
//
//                view.switchPixivIllust();
//
//                if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_PIXIVILLUST, true)) {
//
//                    view.showAlertDialog(R.string.small_tips, R.string.tips_pixivillust,
//                            R.string.remember, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_PIXIVILLUST, false);
//                                    dialog.dismiss();
//                                }
//                            });
//
//
//                }
//
//
//                break;

            case R.id.item_game:

                view.switchGame();

                break;

            case R.id.item_setting:
                view.switchSetting();
                break;
            case R.id.item_about:
                view.switchAbout();
                break;
            default:
                break;

        }

        view.closeDrawer();


    }

    @Override
    public void onBusEventInteraction(EventContainer eventContainer) {

        Log.i(TAG, "onBusEventInteraction: " + eventContainer);

        switch (eventContainer.getType()) {

            case EventContainer.TYPE_PHOTOVIEW:

                PhotoViewEvent photoViewEvent = (PhotoViewEvent) eventContainer.getEvent();

                Bundle bundle = new Bundle();
                bundle.putString(Constants.IMG_URL, photoViewEvent.getImg_url());
                bundle.putInt(Constants.IMG_ID, photoViewEvent.getImg_id());

                view.startPhotoViewActivity(bundle);

                break;

            case EventContainer.TYPE_GAME:

                GameEvent gameEvent = (GameEvent) eventContainer.getEvent();
                switch (gameEvent.getType()) {

                    case GameEvent.TYPE_PUZZLE:
                        view.startPuzzleActivity();
                        break;
                    case GameEvent.TYPE_SUPPERZZLE:
                        view.startSupperzzleActivity();
                        break;
                    default:
                        break;
                }
                break;

            default:
                break;


        }


    }
}
