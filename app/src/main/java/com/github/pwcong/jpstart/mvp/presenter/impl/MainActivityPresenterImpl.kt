package com.github.pwcong.jpstart.mvp.presenter.impl

import android.os.Bundle
import android.util.Log

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constant.Constant
import com.github.pwcong.jpstart.manager.SharedPreferenceManager
import com.github.pwcong.jpstart.mvp.model.BaseModel.MainActivityModel
import com.github.pwcong.jpstart.mvp.model.impl.MainActivityModelImpl
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter
import com.github.pwcong.jpstart.mvp.presenter.BasePresenter.MainActivityPresenter
import com.github.pwcong.jpstart.mvp.view.BaseView.MainActivityView
import com.github.pwcong.jpstart.rxbus.event.EventContainer
import com.github.pwcong.jpstart.rxbus.event.GameEvent
import com.github.pwcong.jpstart.rxbus.event.PhotoViewEvent

class MainActivityPresenterImpl(view: MainActivityView) : BasePresenter<MainActivityView>(view),
    MainActivityPresenter {
    private val TAG: String = javaClass.simpleName

    private val model: MainActivityModel = MainActivityModelImpl()

    override fun initMainActivity() {
        onNavigationItemSelected(R.id.item_jpstart)
        view.setViewPager(model.data)
    }

    override fun onRadioButtonChanged(position: Int) {
        when (position) {
            0 -> App.TYPE_MING = Constant.TYPE_HIRAGANA
            1 -> App.TYPE_MING = Constant.TYPE_KATAKANA
            else -> {}
        }
        view.switchJPStart()
    }

    override fun onNavigationItemSelected(id: Int) {
        when (id) {
            R.id.item_jpstart -> {
                view.switchJPStart()

                if (SharedPreferenceManager.getInstance()
                        .getBoolean(Constant.FLAG_TIPS_JPSTART, true)
                ) {
                    view.showAlertDialog(R.string.small_tips,
                        R.string.tips_jpstart, R.string.remember,
                        { dialog, which -> dialog.dismiss() }, R.string.do_not_remind,
                        { dialog, which ->
                            SharedPreferenceManager.getInstance().putBoolean(
                                Constant.FLAG_TIPS_JPSTART,
                                false
                            )
                            dialog.dismiss()
                        })
                }
            }

            R.id.item_memory -> {
                if (SharedPreferenceManager.getInstance()
                        .getBoolean(Constant.FLAG_TIPS_MEMORY, true)
                ) {
                    view.showAlertDialog(R.string.small_tips,
                        R.string.tips_memory, R.string.remember,
                        { dialog, which -> dialog.dismiss() }, R.string.do_not_remind,
                        { dialog, which ->
                            SharedPreferenceManager.getInstance()
                                .putBoolean(Constant.FLAG_TIPS_MEMORY, false)
                            dialog.dismiss()
                        })
                }

                view.switchMemory()
            }

            R.id.item_translate -> {
                view.switchTranslate()

                if (SharedPreferenceManager.getInstance()
                        .getBoolean(Constant.FLAG_TIPS_TRANSLATE, true)
                ) {
                    view.showAlertDialog(R.string.small_tips,
                        R.string.tips_translate, R.string.remember,
                        { dialog, which -> dialog.dismiss() }, R.string.do_not_remind,
                        { dialog, which ->
                            SharedPreferenceManager.getInstance().putBoolean(
                                Constant.FLAG_TIPS_TRANSLATE,
                                false
                            )
                            dialog.dismiss()
                        })
                }
            }

            R.id.item_game -> view.switchGame()

            R.id.item_setting -> view.switchSetting()
            R.id.item_about -> view.switchAbout()
            else -> {}
        }
        view.closeDrawer()
    }

    override fun onBusEventInteraction(eventContainer: EventContainer) {
        Log.i(TAG, "onBusEventInteraction: $eventContainer")

        when (eventContainer.type) {
            EventContainer.TYPE_PHOTOVIEW -> {
                val photoViewEvent = eventContainer.event as PhotoViewEvent

                val bundle = Bundle()
                bundle.putString(Constant.IMG_URL, photoViewEvent.img_url)
                bundle.putInt(Constant.IMG_ID, photoViewEvent.img_id)

                view.startPhotoViewActivity(bundle)
            }

            EventContainer.TYPE_GAME -> {
                val gameEvent = eventContainer.event as GameEvent
                when (gameEvent.type) {
                    GameEvent.TYPE_PUZZLE -> view.startPuzzleActivity()
                    else -> {}
                }
            }

            else -> {}
        }
    }
}
