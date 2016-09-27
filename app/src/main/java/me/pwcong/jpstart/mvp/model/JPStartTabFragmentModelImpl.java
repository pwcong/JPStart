package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.JPTab;
import me.pwcong.jpstart.utils.ResourceUtils;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartTabFragmentModelImpl implements BaseModel.JPStartTabFragmentModel {

    @Override
    public List<JPTab> getData() {

        List<JPTab> list=new ArrayList<>();
        String hiragana = ResourceUtils.getString(App.getInstance(), R.string.hiragana);
        String katakana = ResourceUtils.getString(App.getInstance(), R.string.katakana);

        list.add(new JPTab(Constants.TYPE_HIRAGANA,hiragana));
        list.add(new JPTab(Constants.TYPE_KATAKANA,katakana));

        return list;
    }
}
