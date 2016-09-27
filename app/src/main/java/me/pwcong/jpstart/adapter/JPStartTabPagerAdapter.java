package me.pwcong.jpstart.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import me.pwcong.jpstart.component.fragment.JPStartFragment;
import me.pwcong.jpstart.mvp.bean.JPTab;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartTabPagerAdapter extends BasePagerAdapter<JPTab> {

    public JPStartTabPagerAdapter(FragmentManager fm, List<JPTab> list) {
        super(fm, list);
    }

    @Override
    protected Fragment getFragment(JPTab jpTab) {
        return JPStartFragment.newInstance(jpTab.getType());
    }

    @Override
    protected CharSequence getTitle(JPTab jpTab) {
        return jpTab.getTitle();
    }
}
