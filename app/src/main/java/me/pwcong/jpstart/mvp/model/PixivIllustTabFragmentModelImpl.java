package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.bean.PixivIllustTab;
import me.pwcong.jpstart.network.pixiv.PixivIllustApi;
import me.pwcong.jpstart.utils.ResourceUtils;

/**
 * Created by Pwcong on 2016/10/3.
 */

public class PixivIllustTabFragmentModelImpl implements BaseModel.PixivIllustTabFragmentModel {

    @Override
    public List<PixivIllustTab> getData() {

        List<PixivIllustTab> data = new ArrayList<>();
        data.add(new PixivIllustTab(PixivIllustApi.MODE_DAILY, ResourceUtils.getString(App.getInstance(), R.string.daily)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_WEEKLY, ResourceUtils.getString(App.getInstance(), R.string.weekly)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_MONTHLY, ResourceUtils.getString(App.getInstance(), R.string.monthly)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_ROOKIE, ResourceUtils.getString(App.getInstance(), R.string.rookie)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_ORIGINAL, ResourceUtils.getString(App.getInstance(), R.string.original)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_MALE, ResourceUtils.getString(App.getInstance(), R.string.male)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_FEMALE, ResourceUtils.getString(App.getInstance(), R.string.female)));

        return data;
    }
}
