package me.pwcong.jpstart.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import me.pwcong.jpstart.component.fragment.BannerFragment;
import me.pwcong.jpstart.mvp.bean.BannerItem;

/**
 * Created by Pwcong on 2016/10/10.
 */

public class BannerPagerAdapter extends BasePagerAdapter<BannerItem> {

    public BannerPagerAdapter(FragmentManager fm, List<BannerItem> list) {
        super(fm, list);
    }

    @Override
    protected Fragment getFragment(BannerItem bannerItem) {
        return BannerFragment.newInstance(bannerItem);
    }

    @Override
    protected CharSequence getTitle(BannerItem bannerItem) {
        return bannerItem.getTitle();
    }
}
