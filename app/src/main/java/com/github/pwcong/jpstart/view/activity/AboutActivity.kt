package com.github.pwcong.jpstart.ui.activity

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.FileUtils
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.databinding.ActivityAboutBinding
import com.github.pwcong.jpstart.utils.ActivityUtils
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class AboutActivity : BaseActivity<ActivityAboutBinding>() {
    private val MY_PERMISSIONS_REQUEST_STORAGE = 10000

    private lateinit var mRootLayout: CoordinatorLayout

    private lateinit var mToolbar: Toolbar

    private lateinit var mPwcongButton: Button

    private lateinit var qrcodeImage: ImageView

    override fun initViewBinding(): ActivityAboutBinding {
        return ActivityAboutBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        mRootLayout = viewBinding.layoutRoot
        mToolbar = viewBinding.toolbar
        mPwcongButton = viewBinding.root.findViewById(R.id.btn_pwcong)
        qrcodeImage = viewBinding.root.findViewById(R.id.img_qrcode)

        initToolbar()
        initButton()
        initQRCodeImage()
    }

    private fun initToolbar() {
        mToolbar.setTitle(R.string.app_name)
        setSupportActionBar(mToolbar)
        mToolbar.setNavigationOnClickListener { finish() }
    }

    private fun initButton() {
        mPwcongButton.setOnClickListener { ActivityUtils.openUrl(Constants.URL_PWCONG) }

    }

    private fun initQRCodeImage() {
        qrcodeImage.setOnClickListener {
            showAlertDialog(R.string.small_tips,
                R.string.trust_to_save_qrcode, R.string.yes,
                { dialog, which ->
                    dialog.dismiss()
                    if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(
                            this@AboutActivity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            this@AboutActivity,
                            arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            MY_PERMISSIONS_REQUEST_STORAGE
                        )
                    } else {
                        saveQRCodeImage()
                    }
                }, R.string.no,
                { dialog, which -> dialog.dismiss() })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_PERMISSIONS_REQUEST_STORAGE) {
            saveQRCodeImage()
        }
    }

    private fun saveQRCodeImage() {
        Observable.create { subscriber ->
            subscriber.onStart()
            subscriber.onNext(R.string.saving)

            try {
                if (FileUtils.createOrExistsDir(Constants.FILEDIR_ROOT)) {
                    val dstFile =
                        File(Constants.FILEDIR_ROOT, "qrcode_pwcong" + Constants.FILETYPE_PNG)
                    FileUtils.createOrExistsFile(dstFile)
                    val bitmapDrawable = qrcodeImage.drawable as BitmapDrawable
                    val bitmap = bitmapDrawable.bitmap

                    val outputStream = FileOutputStream(dstFile)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.flush()
                    outputStream.close()

                    subscriber.onCompleted()
                } else {
                    subscriber.onError(Exception("文件夹创建失败！"))
                }
            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribe(object : Subscriber<Int>() {
                override fun onCompleted() {
                    showSnackBar(mRootLayout, R.string.success_to_save)
                }

                override fun onError(e: Throwable?) {
                    showSnackBar(mRootLayout, R.string.fail_to_save)
                }

                override fun onNext(s: Int) {
                    showSnackBar(mRootLayout, s)
                }
            })
    }

    private fun showAlertDialog(
        titleId: Int, messageId: Int,
        positiveTextId: Int, positiveButtonListener: DialogInterface.OnClickListener?,
        negativeTextId: Int, negativeButtonListener: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(this@AboutActivity)
            .setTitle(titleId)
            .setMessage(messageId)
            .setPositiveButton(positiveTextId, positiveButtonListener)
            .setNegativeButton(negativeTextId, negativeButtonListener)
            .setIcon(R.drawable.ic_lightbulb_outline_black_24dp)
            .create()
            .show()
    }
}
