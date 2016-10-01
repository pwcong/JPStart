package me.pwcong.jpstart.network.youdao.service;

import me.pwcong.jpstart.mvp.bean.YoudaoTranslateBean;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/10/1.
 */

public interface YoudaoService {

    interface YoudaoTranslateService{

        void translate(String q, Subscriber<YoudaoTranslateBean> subscriber);
    }

}
