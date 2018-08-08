package me.pwcong.jpstart.component.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
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

    private void initToolbar(){

        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initButton(){

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
                        R.string.trust_to_save_qrcode, R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }, R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Observable.create(new Observable.OnSubscribe<Integer>() {

                                    @Override
                                    public void call(Subscriber<? super Integer> subscriber) {

                                        subscriber.onStart();

                                        FileOutputStream outputStream = null;
                                        try {

                                            if(FileUtils.createOrExistsDir(Constants.FILEDIR_ROOT)){

                                                File dstFile = new File(Constants.FILEDIR_ROOT,"qrcode_pwcong" + Constants.FILETYPE_PNG);

                                                Bitmap drawingCache = qrcodeImage.getDrawingCache();
                                                outputStream = new FileOutputStream(dstFile);
                                                drawingCache.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

                                                subscriber.onNext(R.string.success_to_save);
                                                subscriber.onCompleted();

                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        } finally {
                                            if (outputStream != null) {
                                                try {
                                                    outputStream.close();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }

                                    }
                                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
                                    @Override
                                    public void onCompleted() {
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        showSnackBar(mRootLayout,R.string.fail_to_save);
                                    }

                                    @Override
                                    public void onNext(Integer s) {
                                        showSnackBar(mRootLayout,s);
                                    }
                                });

                                dialog.dismiss();
                            }
                        });
            }
        });
    }

    public void showAlertDialog(int titleId, int messageId,
                                int positiveTextId, DialogInterface.OnClickListener positiveButtonListener,
                                int negativeTextId, DialogInterface.OnClickListener negativeButtonListener) {

        new AlertDialog.Builder(AboutActivity.this)
                .setTitle(titleId)
                .setMessage(messageId)
                .setPositiveButton(positiveTextId,positiveButtonListener)
                .setNegativeButton(negativeTextId,negativeButtonListener)
                .setIcon(R.drawable.ic_lightbulb_outline_black_24dp)
                .create()
                .show();

    }


    @Override
    protected void doAction() {

    }
}
