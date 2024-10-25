package com.github.pwcong.jpstart.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.adapter.JPStartRecyclerAdapter
import com.github.pwcong.jpstart.constant.Constant
import com.github.pwcong.jpstart.databinding.FragmentJpstartBinding
import com.github.pwcong.jpstart.manager.GifManager
import com.github.pwcong.jpstart.manager.SoundPoolManager
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter.JPStartFragmentPresenter
import com.github.pwcong.jpstart.mvp.presenter.impl.JPStartFragmentPresenterImpl
import com.github.pwcong.jpstart.mvp.view.BaseView.JPStartFragmentView
import com.github.pwcong.jpstart.ui.component.dialog.ImageDialog
import com.github.pwcong.jpstart.utils.ResourceUtils

class JPStartFragment : BaseFragment<FragmentJpstartBinding>(), JPStartFragmentView {

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var presenter: JPStartFragmentPresenter

    private var categoryYin = 0

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentJpstartBinding {
        return FragmentJpstartBinding.inflate(inflater, container, false)
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mRecyclerView = getViewBinding().recyclerView

        categoryYin = requireArguments().getInt(Constant.CATEGORY_YIN)

        presenter = JPStartFragmentPresenterImpl(this)
    }

    override fun doAction() {
        presenter.initJPStartFragment(categoryYin)
    }

    override fun setData(data: List<JPItem>) {
        val adapter = JPStartRecyclerAdapter(data)

        adapter.setOnItemClickListener(object : JPStartRecyclerAdapter.OnItemClickListener {
            override fun onClick(item: JPItem) {
                SoundPoolManager.getInstance().play(item.rome)
            }
        })

        adapter.setOnItemLongClickListener(object :
            JPStartRecyclerAdapter.OnItemLongClickListener {
            override fun onLongClick(item: JPItem) {
                val gif = GifManager.getInstance().getJPGif(item.rome)
                if (gif != null) {
                    if (App.TYPE_MING == Constant.TYPE_HIRAGANA) {
                        context?.let {
                            ImageDialog.Builder(it)
                                .setResId(gif.hiragana)
                                .override(
                                    ResourceUtils.getDimension(it, R.dimen.dialog_width).toInt(),
                                    ResourceUtils.getDimension(it, R.dimen.dialog_height).toInt()
                                )
                                .create()
                                .show()
                        }
                    } else {
                        context?.let {
                            ImageDialog.Builder(it)
                                .setResId(gif.katakana)
                                .override(
                                    ResourceUtils.getDimension(it, R.dimen.dialog_width).toInt(),
                                    ResourceUtils.getDimension(it, R.dimen.dialog_height).toInt()
                                )
                                .create()
                                .show()
                        }
                    }
                }
            }
        })

        mRecyclerView.adapter = adapter
    }

    override fun setRecyclerView(type: Int) {
        when (type) {
            Constant.CATEGORY_QINGYIN -> {
                val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(
                    context,
                    Constant.COLUMN_QINGYIN
                )
                mRecyclerView.layoutManager = layoutManager
            }

            Constant.CATEGORY_ZHUOYIN -> {
                val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(
                    context,
                    Constant.COLUMN_ZHUOYIN
                )
                mRecyclerView.layoutManager = layoutManager
            }

            Constant.CATEGORY_AOYIN -> {
                val layoutManager: RecyclerView.LayoutManager =
                    GridLayoutManager(context, Constant.COLUMN_AOYIN)
                mRecyclerView.layoutManager = layoutManager
            }

            else -> {}
        }
    }

    companion object {
        fun newInstance(type: Int): JPStartFragment {
            val argument = Bundle()
            argument.putInt(Constant.CATEGORY_YIN, type)

            val fragment = JPStartFragment()
            fragment.arguments = argument

            return fragment
        }
    }
}
