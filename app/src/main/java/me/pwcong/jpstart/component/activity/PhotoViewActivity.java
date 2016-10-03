package me.pwcong.jpstart.component.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.blankj.utilcode.utils.FileUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Pwcong on 2016/10/3.
 */

public class PhotoViewActivity extends BaseActivity {


    @BindView(R.id.layout_root)
    LinearLayout mRootLayout;
    @BindView(R.id.pv)
    PhotoView mPhotoView;

    String img_url;
    int img_id;

    @Override
    protected int getViewId() {
        return R.layout.activity_photoview;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        img_url = getIntent().getExtras().getString(Constants.IMG_URL);
        img_id = getIntent().getExtras().getInt(Constants.IMG_ID);

        initPhotoView();

    }

    private void initPhotoView(){

        Glide.with(this).load(img_url).asBitmap().into(mPhotoView);

        mPhotoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override

            public boolean onLongClick(View v) {

                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        try {

                            File srcFile = Glide.with(PhotoViewActivity.this)
                                    .load(img_url)
                                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                    .get();


                            if(FileUtils.createOrExistsDir(Constants.FILEDIR_ROOT)){

                                File dstFile = new File(Constants.FILEDIR_ROOT,String.valueOf(img_id)+Constants.FILETYPE_JPG);
                                FileUtils.copyFile(srcFile,dstFile);

                            }

                        } catch (InterruptedException | ExecutionException e) {
                            subscriber.onError(e);
                        }

                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        showSnackBar(mRootLayout,R.string.save_error);
                    }

                    @Override
                    public void onNext(String s) {

                        showSnackBar(mRootLayout,R.string.save_success);
                    }
                });

                return true;
            }
        });


    }

    @Override
    protected void doAction() {
        hideState();
    }

    private void hideState(){

        if(Build.VERSION.SDK_INT < 16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

    }
}
