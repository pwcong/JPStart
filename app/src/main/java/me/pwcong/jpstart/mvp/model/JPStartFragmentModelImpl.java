package me.pwcong.jpstart.mvp.model;

import java.util.List;

import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.mvp.bean.JPItem;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragmentModelImpl implements BaseModel.JPStartFragmentModel {

    @Override
    public List<JPItem> getData(int type) {
        return DBManager.getInstance().query();
    }
}
