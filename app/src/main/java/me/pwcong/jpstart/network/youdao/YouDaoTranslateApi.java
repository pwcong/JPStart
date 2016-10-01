package me.pwcong.jpstart.network.youdao;

import me.pwcong.jpstart.mvp.bean.YoudaoTranslateBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Pwcong on 2016/10/1.
 */

public interface YoudaoTranslateApi {

    String DOCTYPE_JSON     = "json";
    String DOCTYPE_XML      = "xml";
    String DOCTYPE_JSONP    = "jsonp";

    String VERSION          = "1.1";

    String TYPE             = "data";


    int OK                  = 0;
    int ERROR_TOOLONG       = 20;
    int ERROR_INVALID       = 30;
    int ERROR_UNSUPPORT     = 40;
    int ERROR_KEY           = 50;
    int ERROR_NONE          = 60;



    @GET("/openapi.do")
    Observable<YoudaoTranslateBean> request(@Query("keyfrom") String keyfrom, @Query("key") String key, @Query("type") String type,
                                            @Query("doctype") String doctype, @Query("version")String version, @Query("q") String q);


}
