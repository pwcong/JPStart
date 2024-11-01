package com.github.pwcong.jpstart.mvp.presenter.impl

import android.util.Log
import rx.Subscriber

import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.model.BaseModel.JPStartFragmentModel
import com.github.pwcong.jpstart.mvp.model.impl.JPStartFragmentModelImpl
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter.JPStartFragmentPresenter
import com.github.pwcong.jpstart.mvp.view.BaseView.JPStartFragmentView

class JPStartFragmentPresenterImpl(view: JPStartFragmentView) :
    BasePresenter<JPStartFragmentView>(view),
    JPStartFragmentPresenter {
    private val TAG: String = javaClass.simpleName

    private val model: JPStartFragmentModel = JPStartFragmentModelImpl()

    override fun initJPStartFragment(category: Int) {
        view.setRecyclerView(category)
        model.getData(category, object : Subscriber<List<JPItem>>() {
            override fun onCompleted() {
                Log.i(TAG, "onCompleted: OK")
            }

            override fun onError(e: Throwable?) {
                Log.i(TAG, "onError: OK")
            }

            override fun onNext(list: List<JPItem>) {
                view.setData(list)
                Log.i(TAG, "onNext: OK")
            }
        })
    }
}
