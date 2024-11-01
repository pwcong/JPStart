package com.github.pwcong.jpstart.mvp.presenter.impl

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.manager.ClipboardManager
import com.github.pwcong.jpstart.mvp.bean.TranslateBean
import com.github.pwcong.jpstart.mvp.model.BaseModel
import com.github.pwcong.jpstart.mvp.model.impl.TranslateFragmentModelImpl
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter.TranslateFragmentPresenter
import com.github.pwcong.jpstart.mvp.view.BaseView.TranslateFragmentView
import com.github.pwcong.jpstart.network.baidu.api.BaiduTranslateApi
import rx.Subscriber

class TranslateFragmentPresenterImpl(view: TranslateFragmentView) :
    BasePresenter<TranslateFragmentView>(view), TranslateFragmentPresenter {
    private val model: BaseModel.TranslateFragmentModel = TranslateFragmentModelImpl()

    override fun initTranslateFragment() {
        view.setFromSpinner(model.fromList)
        view.setToSpinner(model.toList)
    }

    override fun checkFromLanguage(from: Int) {
        App.FROM_LAN = from
    }

    override fun checkToLanguage(to: Int) {
        App.TO_LAN = to
    }

    override fun checkImageViewClick(id: Int) {
        when (id) {
            R.id.iv_src_copy -> {
                val srcText = view.srcText
                if (!srcText.isNullOrEmpty()) {
                    ClipboardManager.getInstance().setText("label", view.srcText)
                    view.showMsg(R.string.copy_successfully)
                }
            }

            R.id.iv_src_paste -> view.setSrcEditText(ClipboardManager.getInstance().text)
            R.id.iv_src_clear -> view.setSrcEditText("")
            R.id.iv_dst_copy -> {
                val dstText = view.dstText
                if (!dstText.isNullOrEmpty()) {
                    ClipboardManager.getInstance().setText("label", view.dstText)
                    view.showMsg(R.string.copy_successfully)
                }
            }

            R.id.iv_dst_clear -> view!!.setDstTextView("")
            else -> {}
        }
    }

    override fun doTranslate() {
        val srcText = view.srcText

        if (!srcText.isNullOrEmpty()) {
            val from: String = when (App.FROM_LAN) {
                0 -> BaiduTranslateApi.AUTO
                1 -> BaiduTranslateApi.ZH
                2 -> BaiduTranslateApi.EN
                3 -> BaiduTranslateApi.JP
                else -> BaiduTranslateApi.AUTO
            }

            val to: String = when (App.TO_LAN) {
                0 -> BaiduTranslateApi.ZH
                1 -> BaiduTranslateApi.EN
                2 -> BaiduTranslateApi.JP
                else -> BaiduTranslateApi.ZH
            }
            model.translate(view.srcText, from, to, object : Subscriber<TranslateBean>() {
                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    view.showMsg(R.string.translate_error)
                    e.printStackTrace()
                }

                override fun onNext(translateBean: TranslateBean) {
                    if (translateBean.code == 200) {
                        view.setDstTextView(translateBean.result)
                    } else {
                        view.showMsg(R.string.translate_error)
                    }
                }
            })
        }
    }
}
