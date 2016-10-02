package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.bean.TranslateSpinnerItem;
import me.pwcong.jpstart.utils.ResourceUtils;

/**
 * Created by Pwcong on 2016/10/2.
 */

public class TranslateFragmentModelImpl implements BaseModel.TranslateFragmentModel {

    @Override
    public List<TranslateSpinnerItem> getFromList() {

        List<TranslateSpinnerItem> fromList = new ArrayList<>();
        fromList.add(new TranslateSpinnerItem(0, ResourceUtils.getString(App.getInstance(), R.string.auto_check),false));
        fromList.add(new TranslateSpinnerItem(R.drawable.china_icon_64,ResourceUtils.getString(App.getInstance(),R.string.chinese),true));
        fromList.add(new TranslateSpinnerItem(R.drawable.kingdom_united_icon_64,ResourceUtils.getString(App.getInstance(),R.string.english),true));
        fromList.add(new TranslateSpinnerItem(R.drawable.japan_icon_64,ResourceUtils.getString(App.getInstance(),R.string.japanese),true));

        return fromList;
    }

    @Override
    public List<TranslateSpinnerItem> getToList() {

        List<TranslateSpinnerItem> toList = new ArrayList<>();
        toList.add(new TranslateSpinnerItem(R.drawable.china_icon_64,ResourceUtils.getString(App.getInstance(),R.string.chinese),true));
        toList.add(new TranslateSpinnerItem(R.drawable.kingdom_united_icon_64,ResourceUtils.getString(App.getInstance(),R.string.english),true));
        toList.add(new TranslateSpinnerItem(R.drawable.japan_icon_64,ResourceUtils.getString(App.getInstance(),R.string.japanese),true));

        return toList;
    }
}
