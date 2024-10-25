package com.github.pwcong.jpstart.mvp.model.impl

import com.github.pwcong.jpstart.constant.Constant
import com.github.pwcong.jpstart.manager.DBManager
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.model.BaseModel.JPStartFragmentModel
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class JPStartFragmentModelImpl : JPStartFragmentModel {
    override fun getData(category: Int, subscriber: Subscriber<List<JPItem>>) {
        Observable.create { subscriber ->
            subscriber.onStart()
            val list: List<JPItem>? = when (category) {
                Constant.CATEGORY_QINGYIN -> DBManager.getInstance().getQingYin()

                Constant.CATEGORY_ZHUOYIN -> DBManager.getInstance().getZhuoYin()

                Constant.CATEGORY_AOYIN -> DBManager.getInstance().getAoYin()

                else -> null
            }
            if (list == null) {
                subscriber.onError(Exception())
            } else {
                subscriber.onNext(list)
            }
            subscriber.onCompleted()
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
            .subscribe(subscriber)
    }
}