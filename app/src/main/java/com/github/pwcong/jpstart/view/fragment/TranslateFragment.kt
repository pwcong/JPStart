package com.github.pwcong.jpstart.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.adapter.TranslateSpinnerAdapter
import com.github.pwcong.jpstart.databinding.FragmentTranslateBinding
import com.github.pwcong.jpstart.mvp.bean.TranslateSpinnerItem
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.impl.TranslateFragmentPresenterImpl
import com.github.pwcong.jpstart.mvp.view.BaseView.TranslateFragmentView

class TranslateFragment : BaseFragment<FragmentTranslateBinding>(), TranslateFragmentView,
    View.OnClickListener {

    private lateinit var presenter: BasePresenter.TranslateFragmentPresenter

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTranslateBinding {
        return FragmentTranslateBinding.inflate(inflater, container, false)
    }

    override fun init(savedInstanceState: Bundle?) {
        presenter = TranslateFragmentPresenterImpl(this)

        initSpinner()
        initButton()
        initImageView()

        presenter.initTranslateFragment()
    }

    private fun initSpinner() {
        viewBinding.spinnerFrom.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    presenter.checkFromLanguage(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        viewBinding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                presenter.checkToLanguage(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun initButton() {
        viewBinding.btnTranslate.setOnClickListener { presenter.doTranslate() }
    }

    private fun initImageView() {
        viewBinding.ivSrcCopy.setOnClickListener(this)
        viewBinding.ivSrcPaste.setOnClickListener(this)
        viewBinding.ivSrcClear.setOnClickListener(this)
        viewBinding.ivDstCopy.setOnClickListener(this)
        viewBinding.ivDstClear.setOnClickListener(this)
    }

    override fun showMsg(msg: String) {
        showSnackBar(msg)
    }

    override fun showMsg(msg: Int) {
        showSnackBar(msg)
    }

    override val srcText: String
        get() = viewBinding.etSrc.text.toString()

    override fun setSrcEditText(text: String) {
        viewBinding.etSrc.setText(text)
    }

    override val dstText: String
        get() = viewBinding.tvDst.text.toString()

    override fun setDstTextView(text: String) {
        viewBinding.tvDst.text = text
    }

    override fun setFromSpinner(list: List<TranslateSpinnerItem>) {
        viewBinding.spinnerFrom.adapter = context?.let { TranslateSpinnerAdapter(list, it) }
        viewBinding.spinnerFrom.setSelection(App.FROM_LAN)
    }

    override fun setToSpinner(list: List<TranslateSpinnerItem>) {
        viewBinding.spinnerTo.adapter = context?.let { TranslateSpinnerAdapter(list, it) }
        viewBinding.spinnerTo.setSelection(App.TO_LAN)
    }

    override fun onClick(v: View) {
        presenter.checkImageViewClick(v.id)
    }
}
