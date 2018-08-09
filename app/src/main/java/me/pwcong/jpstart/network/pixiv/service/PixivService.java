package me.pwcong.jpstart.network.pixiv.service;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/10/2.
 */

public interface PixivService {

    interface IllustService {
        void getIllusts(String mode, Subscriber<ResponseBody> subscriber);

        void getIllust(int id, Subscriber<ResponseBody> subscriber);

    }

}
