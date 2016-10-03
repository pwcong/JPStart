package me.pwcong.jpstart.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import me.pwcong.jpstart.component.fragment.PixivIllustFragment;
import me.pwcong.jpstart.mvp.bean.PixivIllustTab;

/**
 * Created by Pwcong on 2016/10/3.
 */

public class PixivIllustTabPagerAdapter extends BasePagerAdapter<PixivIllustTab> {

    public PixivIllustTabPagerAdapter(FragmentManager fm, List<PixivIllustTab> list) {
        super(fm, list);
    }

    @Override
    protected Fragment getFragment(PixivIllustTab pixivIllustTab) {
        return PixivIllustFragment.newInstance(pixivIllustTab.getMode());
    }

    @Override
    protected CharSequence getTitle(PixivIllustTab pixivIllustTab) {
        return pixivIllustTab.getTitle();
    }
}
