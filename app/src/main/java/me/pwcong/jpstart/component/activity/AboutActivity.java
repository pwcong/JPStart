package me.pwcong.jpstart.component.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.FileUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.utils.ActivityUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pwcong on 2016/10/6.
 */

public class AboutActivity extends BaseActivity {

    private final int MY_PERMISSIONS_REQUEST_STORAGE = 10000;

    @BindView(R.id.layout_root)
    CoordinatorLayout mRootLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.btn_pwcong)
    Button mPwcongButton;
    @BindView(R.id.btn_panda)
    Button mPandaButton;
    @BindView(R.id.img_qrcode)
    ImageView qrcodeImage;

    @Override
    protected int getViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        initToolbar();
        initButton();
        initQRCodeImage();
    }

    private void initToolbar() {

        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initButton() {

        mPwcongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openUrl(Constants.URL_PWCONG);
            }
        });

        mPandaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openUrl(Constants.URL_PANDA);
            }
        });

    }

    private void initQRCodeImage() {
        qrcodeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(R.string.small_tips,
                        R.string.trust_to_save_qrcode, R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(AboutActivity.this,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(AboutActivity.this,
                                            new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                                            MY_PERMISSIONS_REQUEST_STORAGE);
                                } else {
                                    saveQRCodeImage();
                                }
                            }
                        }, R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_STORAGE) {
            saveQRCodeImage();
        }

    }

    private void saveQRCodeImage() {

        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                subscriber.onStart();
                subscriber.onNext(R.string.saving);

                try {
                    if (FileUtils.createOrExistsDir(Constants.FILEDIR_ROOT)) {

                        File dstFile = new File(Constants.FILEDIR_ROOT, "qrcode_pwcong" + Constants.FILETYPE_PNG);
                        FileUtils.createOrExistsFile(dstFile);
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) qrcodeImage.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();

                        FileOutputStream outputStream = new FileOutputStream(dstFile);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();

                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new Exception("文件夹创建失败！"));
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                showSnackBar(mRootLayout, R.string.success_to_save);
            }

            @Override
            public void onError(Throwable e) {
                showSnackBar(mRootLayout, R.string.fail_to_save);
            }

            @Override
            public void onNext(Integer s) {
                showSnackBar(mRootLayout, s);
            }
        });
    }

    public void showAlertDialog(int titleId, int messageId,
            int positiveTextId, DialogInterface.OnClickListener positiveButtonListener,
            int negativeTextId, DialogInterface.OnClickListener negativeButtonListener) {

        new AlertDialog.Builder(AboutActivity.this)
                .setTitle(titleId)
                .setMessage(messageId)
                .setPositiveButton(positiveTextId, positiveButtonListener)
                .setNegativeButton(negativeTextId, negativeButtonListener)
                .setIcon(R.drawable.ic_lightbulb_outline_black_24dp)
                .create()
                .show();

    }

    @Override
    protected void doAction() {

    }
}
