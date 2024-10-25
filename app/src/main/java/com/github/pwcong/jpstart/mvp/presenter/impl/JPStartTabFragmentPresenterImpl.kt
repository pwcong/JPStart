package com.github.pwcong.jpstart.mvp.presenter.impl

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.mvp.model.BaseModel.JPStartTabFragmentModel
import com.github.pwcong.jpstart.mvp.model.impl.JPStartTabFragmentModelImpl
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter.JPStartTabFragmentPresenter
import com.github.pwcong.jpstart.mvp.view.BaseView.JPStartTabFragmentView

class JPStartTabFragmentPresenterImpl(view: JPStartTabFragmentView) :
    BasePresenter<JPStartTabFragmentView>(view), JPStartTabFragmentPresenter {
    private val model: JPStartTabFragmentModel = JPStartTabFragmentModelImpl()

    override fun initJPStartTabFragment() {
        view.setData(model.data)
        view.scrollViewPager(App.CURRENT_ITEM)
    }
}
