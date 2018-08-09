package me.pwcong.jpstart.mvp.model;

import java.util.List;

import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.mvp.bean.JPItem;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragmentModelImpl implements BaseModel.JPStartFragmentModel {

    @Override
    public void getData(final int category, Subscriber<List<JPItem>> subscriber) {
        Observable.create(new Observable.OnSubscribe<List<JPItem>>() {

            @Override
            public void call(Subscriber<? super List<JPItem>> subscriber) {

                List<JPItem> list;

                subscriber.onStart();

                switch (category) {

                    case Constants.CATEGORY_QINGYIN:
                        list = DBManager.getInstance().getQingYin();
                        break;
                    case Constants.CATEGORY_ZHUOYIN:
                        list = DBManager.getInstance().getZhuoYin();
                        break;
                    case Constants.CATEGORY_AOYIN:
                        list = DBManager.getInstance().getAoYin();
                        break;
                    default:
                        list = null;
                        break;

                }

                if (list == null) {
                    subscriber.onError(new Exception());
                } else {
                    subscriber.onNext(list);
                }

                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(subscriber);
    }
}
