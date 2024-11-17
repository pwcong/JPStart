package com.github.pwcong.jpstart.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.adapter.TranslateSpinnerAdapter
import com.github.pwcong.jpstart.databinding.FragmentTranslateBinding
import com.github.pwcong.jpstart.mvp.bean.TranslateSpinnerItem
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.impl.TranslateFragmentPresenterImpl
import com.github.pwcong.jpstart.mvp.view.BaseView.TranslateFragmentView


class TranslateFragment : BaseFragment<FragmentTranslateBinding>(), TranslateFragmentView,
    View.OnClickListener {

    private lateinit var mFromSpinner: Spinner

    private lateinit var mToSpinner: Spinner

    private lateinit var mTranslateButton: LinearLayout

    private lateinit var mSrcEditText: EditText

    private lateinit var mDstTextView: TextView

    private lateinit var mSrcCopyImageView: ImageView

    private lateinit var mSrcPasteImageView: ImageView

    private lateinit var mSrcClearImageView: ImageView

    private lateinit var mDstCopyImageView: ImageView

    private lateinit var mDstClearImageView: ImageView

    private lateinit var presenter: BasePresenter.TranslateFragmentPresenter

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTranslateBinding {
        return FragmentTranslateBinding.inflate(inflater, container, false)
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mFromSpinner = getViewBinding().spinnerFrom
        mToSpinner = getViewBinding().spinnerTo
        mTranslateButton = getViewBinding().btnTranslate
        mSrcEditText = getViewBinding().etSrc
        mDstTextView = getViewBinding().tvDst
        mSrcCopyImageView = getViewBinding().ivSrcCopy
        mSrcPasteImageView = getViewBinding().ivSrcPaste
        mSrcClearImageView = getViewBinding().ivSrcClear
        mDstCopyImageView = getViewBinding().ivDstCopy
        mDstClearImageView = getViewBinding().ivDstClear

        presenter = TranslateFragmentPresenterImpl(this)

        initSpinner()
        initButton()
        initImageView()
    }

    private fun initSpinner() {
        mFromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        mToSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        mTranslateButton.setOnClickListener { presenter.doTranslate() }
    }

    private fun initImageView() {
        mSrcCopyImageView.setOnClickListener(this)
        mSrcPasteImageView.setOnClickListener(this)
        mSrcClearImageView.setOnClickListener(this)
        mDstCopyImageView.setOnClickListener(this)
        mDstClearImageView.setOnClickListener(this)
    }

    override fun doAction() {
        presenter.initTranslateFragment()
    }

    override fun showMsg(msg: String) {
        showSnackBar(msg)
    }

    override fun showMsg(msg: Int) {
        showSnackBar(msg)
    }

    override val srcText: String
        get() = mSrcEditText.text.toString()

    override fun setSrcEditText(text: String) {
        mSrcEditText.setText(text)
    }

    override val dstText: String
        get() = mDstTextView.text.toString()

    override fun setDstTextView(text: String) {
        mDstTextView.text = text
    }

    override fun setFromSpinner(list: List<TranslateSpinnerItem>) {
        mFromSpinner.adapter = context?.let { TranslateSpinnerAdapter(list, it) }
        mFromSpinner.setSelection(App.FROM_LAN)
    }

    override fun setToSpinner(list: List<TranslateSpinnerItem>) {
        mToSpinner.adapter = context?.let { TranslateSpinnerAdapter(list, it) }
        mToSpinner.setSelection(App.TO_LAN)
    }

    override fun onClick(v: View) {
        presenter.checkImageViewClick(v.id)
    }
}
