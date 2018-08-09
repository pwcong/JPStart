package me.pwcong.jpstart.mvp.model;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.BaiduTranslateBean;
import me.pwcong.jpstart.mvp.bean.BannerItem;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.bean.JPTab;
import me.pwcong.jpstart.mvp.bean.PixivIllustBean;
import me.pwcong.jpstart.mvp.bean.PixivIllustTab;
import me.pwcong.jpstart.mvp.bean.TranslateSpinnerItem;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/9/24.
 */

public interface BaseModel<T> {

    List<T> getData();


    interface MainActivityModel extends BaseModel<BannerItem> {
    }


    interface JPStartTabFragmentModel extends BaseModel<JPTab> {
    }

    interface JPStartFragmentModel {
        void getData(int category, Subscriber<List<JPItem>> subscriber);
    }

    interface TranslateFragmentModel {

        List<TranslateSpinnerItem> getFromList();

        List<TranslateSpinnerItem> getToList();

        void translate(String q, String from, String to, Subscriber<BaiduTranslateBean> subscriber);

    }

    interface PixivIllustFragmentModel {

        void getIllusts(String mode, Subscriber<List<PixivIllustBean>> subscriber);

        String[] getOptions();

    }

    interface PixivIllustTabFragmentModel extends BaseModel<PixivIllustTab> {
    }

    interface MemoryFragmentModel {

        List<JPItem> getQingYinWithoutHeader();

        List<JPItem> getZhuoYinWithoutHeader();

        List<JPItem> getAoYinWithoutHeader();


    }

    interface PuzzleActivityModel {
        String[] getOptions();

        List<JPItem> getItems();
    }

}
