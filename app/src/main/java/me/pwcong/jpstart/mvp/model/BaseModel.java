package me.pwcong.jpstart.mvp.model;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.BaiduTranslateBean;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.bean.JPTab;
import me.pwcong.jpstart.mvp.bean.TranslateSpinnerItem;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/9/24.
 */

public interface BaseModel<T> {

    List<T> getData();

    interface JPStartTabFragmentModel extends BaseModel<JPTab>{
    }

    interface JPStartFragmentModel {
        void getData(int category, Subscriber<List<JPItem>> subscriber);
    }

    interface TranslateFragmentModel{

        List<TranslateSpinnerItem> getFromList();
        List<TranslateSpinnerItem> getToList();

        void translate(String q, String from, String to, Subscriber<BaiduTranslateBean> subscriber);

    }


}
