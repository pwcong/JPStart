package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.BannerItem;

/**
 * Created by Pwcong on 2016/10/10.
 */

public class BannerFragment extends BaseFragment {

    @BindView(R.id.iv_banner)
    ImageView mImageView;

    private int banner;

    public static BannerFragment newInstance(BannerItem item){

        Bundle arguments = new Bundle();
        arguments.putInt(Constants.IMG_BANNER,item.getBanner());
        BannerFragment fragment=new BannerFragment();
        fragment.setArguments(arguments);

        return fragment;

    }


    @Override
    protected int getViewId() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        banner=getArguments().getInt(Constants.IMG_BANNER);

    }

    @Override
    protected void doAction() {
        Glide.with(getContext()).asBitmap().load(banner).into(mImageView);
    }
}
