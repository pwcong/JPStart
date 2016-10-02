package me.pwcong.jpstart.mvp.view;

import android.content.DialogInterface;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.JPItem;
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
        void switchMemory();
        void switchTranslate();
        void switchPixiv();
        void switchAbout();
        void switchSetting();
        void showAlertDialog(int titleId, int messageId,
                             int positiveTextId, DialogInterface.OnClickListener positiveButtonListener,
                             int negativeTextId, DialogInterface.OnClickListener negativeButtonListener);
    }

    interface JPStartTabFragmentView extends BaseView<JPTab>{
        void scrollViewPager(int position);
    }

    interface JPStartFragmentView extends BaseView<JPItem>{

        void setRecyclerView(int type);

    }

    interface TranslateFragmentView{
        void showMsg(String msg);
        void setSrcEditText(String text);
        void setDstTextView(String text);
    }




}
