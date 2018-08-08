package me.pwcong.jpstart.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import me.pwcong.jpstart.R;

/**
 * Created by Pwcong on 2016/9/30.
 */

public class ImageDialog extends Dialog{


    private ImageDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{

        Context context;
        int resId;
        int width;
        int height;

        public Builder(Context context){
            this.context=context;
        }

        public Builder setResId(int resId){
            this.resId=resId;
            return this;
        }

        public Builder override(int width,int height){
            this.width=width;
            this.height=height;
            return this;
        }

        public ImageDialog create(){

            ImageDialog dialog=new ImageDialog(context, R.style.AppTheme_Dialog_NoTitle);
            ImageView imageView=new ImageView(context);

            RequestOptions requestOptions = new RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(context).asGif().load(resId).apply(requestOptions).into(imageView);

            dialog.addContentView(imageView,new LinearLayout.LayoutParams(width,height));

            return dialog;
        }


    }


}
