package me.pwcong.jpstart.mvp.view;

import android.content.DialogInterface;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.bean.JPTab;
import me.pwcong.jpstart.mvp.bean.PixivIllustBean;
import me.pwcong.jpstart.mvp.bean.PixivIllustTab;
import me.pwcong.jpstart.mvp.bean.TranslateSpinnerItem;

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
        void switchPixivIllust();
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
        void showMsg(int msg);
        String getSrcText();
        void setSrcEditText(String text);
        String getDstText();
        void setDstTextView(String text);
        void setFromSpinner(List<TranslateSpinnerItem> list);
        void setToSpinner(List<TranslateSpinnerItem> list);
    }

    interface PixivIllustTabFragmentView extends BaseView<PixivIllustTab>{

    }

    interface PixivIllustFragmentView extends BaseView<PixivIllustBean>{

        void showMsg(int msg);
        void showMsg(String msg);
        void showProgress();
        void hideProgress();
        void showOptionsDialog(String[] options, DialogInterface.OnClickListener listener);
        void showImg(String url,int id);

    }



}
