package com.github.pwcong.jpstart.view.activity

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.databinding.ActivityPuzzleBinding
import com.github.pwcong.jpstart.manager.SharedPreferenceManager
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.impl.PuzzleActivityPresenterImpl
import com.github.pwcong.jpstart.mvp.view.BaseView.PuzzleActivityView
import java.util.Collections

class PuzzleActivity : BaseActivity<ActivityPuzzleBinding>(), PuzzleActivityView,
    View.OnClickListener {

    private lateinit var mToolbar: Toolbar

    private lateinit var mShowTextView: TextView

    private lateinit var mCountTextView: TextView

    private lateinit var mTipsTextView: TextView

    private lateinit var mButton1: Button

    private lateinit var mButton2: Button

    private lateinit var mButton3: Button

    private lateinit var mButton4: Button

    private var type = 0
    private var count: Int = 0

    private lateinit var current: JPItem
    private lateinit var items: MutableList<JPItem>

    private lateinit var presenter: BasePresenter.PuzzleActivityPresenter

    override fun initViewBinding(): ActivityPuzzleBinding {
        return ActivityPuzzleBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        mToolbar = viewBinding.root.findViewById(R.id.toolbar)
        mShowTextView = viewBinding.tvShow
        mCountTextView = viewBinding.tvCount
        mTipsTextView = viewBinding.tvTips
        mButton1 = viewBinding.btnAnswer1
        mButton2 = viewBinding.btnAnswer2
        mButton3 = viewBinding.btnAnswer3
        mButton4 = viewBinding.btnAnswer4

        presenter = PuzzleActivityPresenterImpl(this)

        initToolbar()
        initButton()
        initTextView()

        presenter.initPuzzleActivity()
    }

    private fun initToolbar() {
        mToolbar.setTitle(R.string.whoami)
        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp)
        setSupportActionBar(mToolbar)

        mToolbar.setNavigationOnClickListener { finish() }
    }

    private fun initButton() {
        mButton1.setOnClickListener(this)
        mButton2.setOnClickListener(this)
        mButton3.setOnClickListener(this)
        mButton4.setOnClickListener(this)
    }

    private fun initTextView() {
        mCountTextView.text = count.toString()
    }

    override fun onClick(v: View) {
        presenter.checkAnswerSelect(v.id, current, items)
    }

    override fun setData(current: JPItem, jams: List<JPItem>) {
        this.current = current

        items = mutableListOf()
        items.add(current)
        items.addAll(jams)
        Collections.shuffle(items)

        when (type) {
            TYPE_HIRAGANA_ROME -> {
                mShowTextView.text = this.current.hiragana

                mButton1.text = items.get(0).rome
                mButton2.text = items.get(1).rome
                mButton3.text = items.get(2).rome
                mButton4.text = items.get(3).rome
            }

            TYPE_HIRAGANA_KATAKANA -> {
                mShowTextView.text = this.current.hiragana

                mButton1.text = items.get(0).katakana
                mButton2.text = items.get(1).katakana
                mButton3.text = items.get(2).katakana
                mButton4.text = items.get(3).katakana
            }

            TYPE_KATAKANA_ROME -> {
                mShowTextView.text = this.current.katakana

                mButton1.text = items[0].rome
                mButton2.text = items[1].rome
                mButton3.text = items[2].rome
                mButton4.text = items[3].rome
            }

            else -> {}
        }
    }

    override fun showSelectDialog(selection: Array<String>) {
        AlertDialog.Builder(this).setItems(
            selection
        ) { _, which ->
            type = which
            presenter.checkTypeSelect(which)
        }
            .setCancelable(false)
            .create()
            .show()
    }

    override fun showResultDialog(
        title: Int, msg: String, icon: Int,
        pbt: Int, pbl: DialogInterface.OnClickListener?,
        nbt: Int, nbl: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setMessage(msg)
            .setTitle(title)
            .setPositiveButton(pbt, pbl)
            .setNegativeButton(nbt, nbl)
            .create()
            .show()
    }

    override fun showDialog(icon: Int, title: Int, msg: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setIcon(icon)
            .setMessage(msg)
            .create()
            .show()
    }

    override fun setTitle(title: String) {
        mToolbar.setTitle(title)
    }

    override fun setTitle(title: Int) {
        mToolbar.setTitle(title)
    }

    override fun addCount() {
        count++
        mCountTextView.text = count.toString()
        mTipsTextView.text = count.toString()
        mTipsTextView.startAnimation(tipsAnimation)
    }

    override fun clearCount() {
        val hs = SharedPreferenceManager.getInstance().getInt(Constants.HIGHEST_SCORE, 0)
        if (count > hs) SharedPreferenceManager.getInstance().putInt(Constants.HIGHEST_SCORE, count)

        count = 0
        mCountTextView.text = count.toString()
    }

    override fun showMsg(msg: Int) {
        showSnackBar(mToolbar, msg)
    }

    override fun showMsg(msg: String) {
        showSnackBar(mToolbar, msg)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_puzzle_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_hiragana_rome -> {
                type = TYPE_HIRAGANA_ROME
                presenter.checkTypeSelect(TYPE_HIRAGANA_ROME)
            }

            R.id.menu_hiragana_katakana -> {
                type = TYPE_HIRAGANA_KATAKANA
                presenter.checkTypeSelect(TYPE_HIRAGANA_KATAKANA)
            }

            R.id.menu_katakana_rome -> {
                type = TYPE_KATAKANA_ROME
                presenter.checkTypeSelect(TYPE_KATAKANA_ROME)
            }

            else -> presenter.checkMenuSelect(item.itemId)

        }
        return true
    }

    private val tipsAnimation: Animation
        get() {
            val animationSet = AnimationSet(true)
            animationSet.addAnimation(AlphaAnimation(1f, 0f))
            animationSet.duration = 1000
            animationSet.fillAfter = true

            return animationSet
        }

    companion object {
        const val TYPE_HIRAGANA_ROME: Int = 0
        const val TYPE_HIRAGANA_KATAKANA: Int = 1
        const val TYPE_KATAKANA_ROME: Int = 2
    }
}
