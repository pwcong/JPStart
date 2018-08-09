package me.pwcong.jpstart.network.baidu.service;

import me.pwcong.jpstart.mvp.bean.BaiduTranslateBean;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/10/1.
 */

public interface BaiduService {

    interface TranslateService {

        void translate(String q, String from, String to, Subscriber<BaiduTranslateBean> subscriber);

    }


}
