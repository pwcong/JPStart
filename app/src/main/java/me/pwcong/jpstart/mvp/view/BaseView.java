package me.pwcong.jpstart.mvp.view;

/**
 * Created by Pwcong on 2016/9/24.
 */

public interface BaseView {


    interface MainActivityView extends BaseView{
        void openDrawer();
        void closeDrawer();
        void switchJPStart();
        void switchTranslate();
        void switchPixiv();
        void switchAbout();
        void switchSetting();
    }




}
