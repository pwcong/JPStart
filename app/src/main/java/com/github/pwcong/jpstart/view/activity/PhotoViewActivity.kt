package com.github.pwcong.jpstart.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnLongClickListener
import android.view.WindowManager
import com.blankj.utilcode.util.FileUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target;
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.databinding.ActivityPhotoviewBinding
import rx.Observable
import rx.Observable.OnSubscribe
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.util.concurrent.ExecutionException

class PhotoViewActivity : BaseActivity<ActivityPhotoviewBinding>() {
    var imgUrl: String = ""
    private var imgId: Int = 0
    override fun initViewBinding(): ActivityPhotoviewBinding {
        return ActivityPhotoviewBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        imgUrl = intent.extras!!.getString(Constants.IMG_URL).toString()
        imgId = intent.extras!!.getInt(Constants.IMG_ID)

        initPhotoView()

        hideState()
    }

    private fun initPhotoView() {
        Glide.with(this).asBitmap().load(imgUrl).into(viewBinding.pv)

        viewBinding.pv.setOnLongClickListener(OnLongClickListener {
            Observable.create(OnSubscribe<Int> { subscriber ->
                subscriber.onStart()

                try {
                    val srcFile = Glide.with(this@PhotoViewActivity)
                        .load(imgUrl)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get()

                    if (FileUtils.createOrExistsDir(Constants.FILEDIR_ROOT)) {
                        val dstFile = File(
                            Constants.FILEDIR_ROOT,
                            imgId.toString() + Constants.FILETYPE_JPG
                        )
                        FileUtils.copy(srcFile, dstFile)
                    }
                } catch (e: InterruptedException) {
                    subscriber.onError(e)
                } catch (e: ExecutionException) {
                    subscriber.onError(e)
                }

                subscriber.onNext(R.string.save_success)
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<Int>() {
                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        showSnackBar(viewBinding.layoutRoot, R.string.save_error)
                    }

                    override fun onNext(s: Int) {
                        showSnackBar(viewBinding.layoutRoot, s)
                    }
                })
            false
        })
    }

    private fun hideState() {
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
