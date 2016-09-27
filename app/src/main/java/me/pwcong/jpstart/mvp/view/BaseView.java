package me.pwcong.jpstart.mvp.view;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.JPTab;

/**
 * Created by Pwcong on 2016/9/24.
 */

public interface BaseView<T> {

    void setData(List<T> data);

    interface MainActivityView {
        void openDrawer();
        void closeDrawer();
        void switchJPStart();
        void switchTranslate();
        void switchPixiv();
        void switchAbout();
        void switchSetting();
    }

    interface JPStartTabFragmentView extends BaseView<JPTab>{
        void scrollViewPager(int position);
    }




}
