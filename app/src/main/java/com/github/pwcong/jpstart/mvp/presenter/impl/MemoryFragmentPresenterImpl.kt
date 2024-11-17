package com.github.pwcong.jpstart.mvp.presenter.impl


import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.mvp.model.BaseModel
import com.github.pwcong.jpstart.mvp.model.impl.MemoryFragmentModelImpl
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter.MemoryFragmentPresenter
import com.github.pwcong.jpstart.mvp.view.BaseView.MemoryFragmentView

class MemoryFragmentPresenterImpl(view: MemoryFragmentView) :
    BasePresenter<MemoryFragmentView>(view),
    MemoryFragmentPresenter {
    private val model: BaseModel.MemoryFragmentModel = MemoryFragmentModelImpl()

    override fun initMemoryFragment() {
        setDate(Constants.CATEGORY_QINGYIN)
    }

    override fun loadMore(category: Int) {
        setDate(category)
        view.showMsg(R.string.one_more_time)
    }

    override fun setDate(category: Int) {
        when (category) {
            Constants.CATEGORY_QINGYIN -> view.setData(model.qingYinWithoutHeader)

            Constants.CATEGORY_ZHUOYIN -> view.setData(model.zhuoYinWithoutHeader)

            Constants.CATEGORY_AOYIN -> view.setData(model.aoYinWithoutHeader)

            else -> {}
        }
    }
}
