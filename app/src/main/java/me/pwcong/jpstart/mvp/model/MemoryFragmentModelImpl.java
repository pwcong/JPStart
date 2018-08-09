package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.mvp.bean.JPItem;

/**
 * Created by Pwcong on 2016/10/5.
 */

public class MemoryFragmentModelImpl implements BaseModel.MemoryFragmentModel {


    @Override
    public List<JPItem> getQingYinWithoutHeader() {

        List<JPItem> list = new ArrayList<>();
        list.addAll(DBManager.getInstance().getQingYinWithoutHeader());

        Collections.shuffle(list);

        return list;

    }

    @Override
    public List<JPItem> getZhuoYinWithoutHeader() {

        List<JPItem> list = new ArrayList<>();
        list.addAll(DBManager.getInstance().getZhuoYinWithoutHeader());

        Collections.shuffle(list);

        return list;
    }

    @Override
    public List<JPItem> getAoYinWithoutHeader() {

        List<JPItem> list = new ArrayList<>();
        list.addAll(DBManager.getInstance().getAoYinWithoutHeader());

        Collections.shuffle(list);

        return list;
    }
}
