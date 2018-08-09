package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.utils.ResourceUtils;

/**
 * Created by Pwcong on 2016/10/24.
 */

public class PuzzleActivityModelImpl implements BaseModel.PuzzleActivityModel {
    @Override
    public String[] getOptions() {
        return new String[]{
                ResourceUtils.getString(App.getInstance(), R.string.hiragana_rome),
                ResourceUtils.getString(App.getInstance(), R.string.hiragana_katakana),
                ResourceUtils.getString(App.getInstance(), R.string.katakana_rome)
        };
    }

    @Override
    public List<JPItem> getItems() {

        List<JPItem> items = new ArrayList<>();
        items.addAll(DBManager.getInstance().getQingYinWithoutHeader());

        Collections.shuffle(items);

        return items;
    }
}
