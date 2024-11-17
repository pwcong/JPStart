package com.github.pwcong.jpstart.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

import com.github.pwcong.floatingactionbutton.FloatingActionButton
import com.github.pwcong.floatingactionbutton.FloatingActionsMenu
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.adapter.MemorySwipeAdapter
import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.databinding.FragmentMemoryBinding
import com.github.pwcong.jpstart.manager.GifManager
import com.github.pwcong.jpstart.manager.SoundPoolManager
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.impl.MemoryFragmentPresenterImpl
import com.github.pwcong.jpstart.mvp.view.BaseView.MemoryFragmentView
import com.github.pwcong.jpstart.ui.component.dialog.ImageDialog
import com.github.pwcong.jpstart.utils.ResourceUtils
import com.github.pwcong.swipecards.SwipeFlingAdapterView
import com.github.pwcong.textdrawable.view.TextDrawable

class MemoryFragment : BaseFragment<FragmentMemoryBinding>(), MemoryFragmentView {

    private lateinit var mSwipeFlingAdapterView: SwipeFlingAdapterView

    private lateinit var mFabMenu: FloatingActionsMenu

    private var adapter: MemorySwipeAdapter? = null
    private lateinit var presenter: BasePresenter.MemoryFragmentPresenter

    private var category: Int = Constants.CATEGORY_QINGYIN

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMemoryBinding {
        return FragmentMemoryBinding.inflate(inflater, container, false)
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mFabMenu = getViewBinding().fabMenu
        mSwipeFlingAdapterView = getViewBinding().swipeView
        presenter = MemoryFragmentPresenterImpl(this)

        initSwipeFlingAdapterView()
        initFabMenu()
    }

    private fun initSwipeFlingAdapterView() {
        mSwipeFlingAdapterView.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                adapter?.remove(0)
                if (adapter?.isEmpty == true) {
                    presenter.loadMore(category)
                }
            }

            override fun onLeftCardExit(p0: Any?) {
            }

            override fun onRightCardExit(p0: Any?) {
            }

            override fun onAdapterAboutToEmpty(p0: Int) {
            }

            override fun onScroll(p0: Float) {
            }
        })
    }

    private fun initFabMenu() {
        val textQing = TextDrawable.builder()
            .beginConfig()
            ?.fontSize(
                requireContext().resources.getDimension(R.dimen.memory_item_fab_text_size).toInt()
            )
            ?.endConfig()
            ?.buildRound("清", requireContext().resources.getColor(R.color.transparent))

        val fabQingyin = FloatingActionButton(requireContext())
        fabQingyin.setIconDrawable(textQing!!)
        fabQingyin.setColorNormal(requireContext().resources.getColor(R.color.green))
        fabQingyin.setColorPressed(requireContext().resources.getColor(R.color.window))
        fabQingyin.setOnClickListener {
            presenter.setDate(Constants.CATEGORY_QINGYIN)
            category = Constants.CATEGORY_QINGYIN
            hideFabMenu()
        }

        val textZhuo = TextDrawable.builder()
            .beginConfig()
            ?.fontSize(
                requireContext().resources.getDimension(R.dimen.memory_item_fab_text_size).toInt()
            )
            ?.endConfig()
            ?.buildRound("浊", requireContext().resources.getColor(R.color.transparent))

        val fabZhuoyin = FloatingActionButton(requireContext())
        fabZhuoyin.setIconDrawable(textZhuo!!)
        fabZhuoyin.setColorNormal(requireContext().resources.getColor(R.color.orange))
        fabZhuoyin.setColorPressed(requireContext().resources.getColor(R.color.window))
        fabZhuoyin.setOnClickListener {
            presenter.setDate(Constants.CATEGORY_ZHUOYIN)
            category = Constants.CATEGORY_ZHUOYIN
            hideFabMenu()
        }

        val textAo = TextDrawable.builder()
            .beginConfig()
            ?.fontSize(
                requireContext().resources.getDimension(R.dimen.memory_item_fab_text_size).toInt()
            )
            ?.endConfig()
            ?.buildRound("拗", requireContext().resources.getColor(R.color.transparent))

        val fabAoyin = FloatingActionButton(requireContext())
        fabAoyin.setIconDrawable(textAo!!)
        fabAoyin.setColorNormal(requireContext().resources.getColor(R.color.blue))
        fabAoyin.setColorPressed(requireContext().resources.getColor(R.color.window))
        fabAoyin.setOnClickListener {
            presenter.setDate(Constants.CATEGORY_AOYIN)
            category = Constants.CATEGORY_AOYIN
            hideFabMenu()
        }

        mFabMenu.addButton(fabQingyin)
        mFabMenu.addButton(fabZhuoyin)
        mFabMenu.addButton(fabAoyin)
    }

    override fun doAction() {
        presenter.initMemoryFragment()
    }

    override fun setData(data: List<JPItem>) {
        if (adapter == null) {
            adapter = MemorySwipeAdapter(data)

            adapter!!.setOnWriteButtonClickListener(object :
                MemorySwipeAdapter.OnWriteButtonClickListener {
                override fun onClick(item: JPItem) {
                    val gif = GifManager.getInstance().getJPGif(item.rome)
                    if (gif != null) {
                        ImageDialog.Builder(context!!)
                            .setResId(gif.hiragana)
                            .override(
                                ResourceUtils.getDimension(context!!, R.dimen.dialog_width).toInt(),
                                ResourceUtils.getDimension(context!!, R.dimen.dialog_height).toInt()
                            )
                            .create()
                            .show()
                    }
                }
            })

            adapter!!.setOnYinButtonClickListener(object :
                MemorySwipeAdapter.OnYinButtonClickListener {
                override fun onClick(item: JPItem) {
                    SoundPoolManager.getInstance().play(item.rome)
                }
            })
        }
        adapter!!.setList(data)
        mSwipeFlingAdapterView.adapter = adapter
    }

    override fun showMsg(msg: Int) {
        showSnackBar(msg)
    }

    override fun showMsg(msg: String) {
        showSnackBar(msg)
    }

    override fun hideFabMenu() {
        mFabMenu.collapse()
    }
}
