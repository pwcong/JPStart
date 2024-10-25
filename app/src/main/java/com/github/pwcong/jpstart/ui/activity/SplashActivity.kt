package com.github.pwcong.jpstart.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager

import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.databinding.ActivitySplashBinding
import com.github.pwcong.jpstart.manager.DBManager
import com.github.pwcong.jpstart.manager.GifManager
import com.github.pwcong.jpstart.manager.SoundPoolManager
import com.github.pwcong.jpstart.utils.ResourceUtils

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun initViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun doAction() {
        Observable.create<String> { subscriber ->
            subscriber.onStart()
            subscriber.onNext(
                ResourceUtils.getString(
                    this@SplashActivity,
                    R.string.loading_data
                )
            )
            DBManager.getInstance().init()
            SoundPoolManager.getInstance().init()
            GifManager.getInstance().init()
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            subscriber.onNext(
                ResourceUtils.getString(
                    this@SplashActivity,
                    R.string.loading_data_success
                )
            )
            try {
                Thread.sleep(200)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            subscriber.onCompleted()
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<String?>() {
                override fun onCompleted() {
                    startActivity(
                        Intent(
                            this@SplashActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
                }

                override fun onError(e: Throwable) {
                    getViewBinding().tvTips.text =
                        ResourceUtils.getString(this@SplashActivity, R.string.loading_data_error)
                }

                override fun onNext(t: String?) {
                    getViewBinding().tvTips.text = t
                }
            })
    }

    private fun hideState() {
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}