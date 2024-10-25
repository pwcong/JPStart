package com.github.pwcong.jpstart.mvp.presenter.impl

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constant.Constant
import com.github.pwcong.jpstart.manager.ActivityManager
import com.github.pwcong.jpstart.manager.SharedPreferenceManager
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.model.BaseModel
import com.github.pwcong.jpstart.mvp.model.impl.PuzzleActivityModelImpl
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter.PuzzleActivityPresenter
import com.github.pwcong.jpstart.mvp.view.BaseView.PuzzleActivityView
import com.github.pwcong.jpstart.ui.activity.PuzzleActivity
import com.github.pwcong.jpstart.utils.ResourceUtils

class PuzzleActivityPresenterImpl(view: PuzzleActivityView) :
    BasePresenter<PuzzleActivityView>(view),
    PuzzleActivityPresenter {
    private val model: BaseModel.PuzzleActivityModel = PuzzleActivityModelImpl()

    override fun initPuzzleActivity() {
        view.showSelectDialog(model.options)
    }

    override fun loadData() {
        val items: List<JPItem> = model.items
        val jams: MutableList<JPItem> = ArrayList()

        val current = items[0]
        jams.add(items[1])
        jams.add(items[2])
        jams.add(items[3])

        view.setData(current, jams)
    }

    override fun checkTypeSelect(which: Int) {
        when (which) {
            PuzzleActivity.TYPE_HIRAGANA_ROME -> {
                view.setTitle(R.string.hiragana_rome)
                view.clearCount()
            }

            PuzzleActivity.TYPE_HIRAGANA_KATAKANA -> {
                view.setTitle(R.string.hiragana_katakana)
                view.clearCount()
            }

            PuzzleActivity.TYPE_KATAKANA_ROME -> {
                view.setTitle(R.string.katakana_rome)
                view.clearCount()
            }

            else -> {}
        }
        loadData()
    }

    override fun checkAnswerSelect(id: Int, current: JPItem, items: List<JPItem>) {
        when (id) {
            R.id.btn_answer1 -> if (items[0].id == current.id) {
                view.addCount()
                loadData()
            } else {
                view.clearCount()
                showResult(current)
            }

            R.id.btn_answer2 -> if (items[1].id == current.id) {
                view.addCount()
                loadData()
            } else {
                view.clearCount()
                showResult(current)
            }

            R.id.btn_answer3 -> if (items[2].id == current.id) {
                view.addCount()
                loadData()
            } else {
                view.clearCount()
                showResult(current)
            }

            R.id.btn_answer4 -> if (items[3].id == current.id) {
                view.addCount()
                loadData()
            } else {
                view.clearCount()
                showResult(current)
            }

            else -> {}
        }
    }

    override fun checkMenuSelect(id: Int) {
        when (id) {
            R.id.menu_help -> view.showDialog(
                R.drawable.ic_help_outline_black_24dp,
                R.string.help,
                ResourceUtils.getString(App.getInstance(), R.string.tips_puzzle_contents)
            )

            R.id.menu_ranking -> {
                val hs = SharedPreferenceManager.getInstance().getInt(Constant.HIGHEST_SCORE, 0)

                view.showDialog(
                    R.drawable.ic_filter_list_black_24dp, R.string.highedt_score,
                    ResourceUtils.getString(
                        App.getInstance(),
                        R.string.tips_highest_score_contents
                    ) + hs.toString()
                )
            }

            else -> {}
        }
    }

    private fun showResult(currrent: JPItem) {
        val msg = currrent.hiragana + " -> " + currrent.katakana + " -> " + currrent.rome

        view.showResultDialog(R.string.sad, msg, R.drawable.ic_mood_bad_black_24dp,
            R.string.one_more_time_2, { dialog, _ ->
                loadData()
                dialog.dismiss()
            }, R.string.exit, { dialog, _ ->
                dialog.dismiss()
                ActivityManager.getInstance().getCurrent()?.finish()
            })
    }
}
