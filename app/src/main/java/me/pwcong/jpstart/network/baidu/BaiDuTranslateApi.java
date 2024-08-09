package me.pwcong.jpstart.network.baidu;

import me.pwcong.jpstart.mvp.bean.BaiduTranslateBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Pwcong on 2016/10/1.
 */

public interface BaiduTranslateApi {

    String AUTO = "auto";
    String ZH = "zh";
    String JP = "jp";
    String EN = "en";

    String OK = "52000";
    String ERROR_TIMEOUT = "52001";
    String ERROR_SYSTEM = "52002";
    String ERROR_APPID = "52003";
    String ERROR_PARAMS = "54000";
    String ERROR_ILLEGAL_IP = "58000";
    String ERROR_SIGN = "54001";
    String ERROR_FREQUENT = "54003";
    String ERROR_TRANSLATE = "58001";
    String ERROR_NO_MONEY = "54004";
    String ERROR_LONG_FREQUENT = "54005";

    @GET("/api/trans/vip/translate")
    Observable<BaiduTranslateBean> request(@Query("q") String q, @Query("from") String from, @Query("to") String to,
            @Query("appid") String appId, @Query("salt") int salt, @Query("sign") String sign);

}
