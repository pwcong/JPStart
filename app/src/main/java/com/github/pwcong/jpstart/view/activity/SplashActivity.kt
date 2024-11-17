package com.github.pwcong.jpstart.view.activity

import android.content.Intent
import android.os.Bundle

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

    override fun init(savedInstanceState: Bundle?) {
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
                    viewBinding.tvTips.text =
                        ResourceUtils.getString(this@SplashActivity, R.string.loading_data_error)
                }

                override fun onNext(t: String?) {
                    viewBinding.tvTips.text = t
                }
            })
    }
}