package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.bean.BannerItem;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class MainActivityModelImpl implements BaseModel.MainActivityModel {


    @Override
    public List<BannerItem> getData() {

        List<BannerItem> list = new ArrayList<>();
        list.add(new BannerItem(R.drawable.banner01, ""));
        list.add(new BannerItem(R.drawable.banner02, ""));
        list.add(new BannerItem(R.drawable.banner03, ""));

        return list;
    }
}
