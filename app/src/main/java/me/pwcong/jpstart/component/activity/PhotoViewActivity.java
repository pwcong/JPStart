package me.pwcong.jpstart.component.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Pwcong on 2016/10/3.
 */

public class PhotoViewActivity extends BaseActivity {


    @BindView(R.id.pv)
    PhotoView mPhotoView;

    String img_url;

    @Override
    protected int getViewId() {
        return R.layout.activity_photoview;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        img_url = getIntent().getExtras().getString(Constants.IMG_URL);

        initPhotoView();

    }

    private void initPhotoView(){

        Glide.with(this).load(img_url).asBitmap().into(mPhotoView);

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
