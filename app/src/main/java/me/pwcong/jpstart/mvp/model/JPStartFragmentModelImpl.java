package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.bean.JPItemWithViewType;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragmentModelImpl implements BaseModel.JPStartFragmentModel {

    @Override
    public List<JPItemWithViewType> getData(int type) {

        switch (type){

            case Constants.TYPE_QINGYIN:

                break;
            case Constants.TYPE_ZHUOYIN:

                break;
            case Constants.TYPE_AOYIN:

                break;

            default:break;
        }

        return null;
    }

    public static List<JPItemWithViewType> getQingYin(){

        List<JPItemWithViewType> res=new ArrayList<>();

        List<JPItem> qingYin = DBManager.getInstance().getQingYin();



        return res;

    }

    public static List<JPItem> getFirstRow(List<JPItem> list){

        List<JPItem> res = new ArrayList<>();
        for(JPItem item:list){
            if(item.getRow() == Constants.ROW_FIRST){
                res.add(item);
            }
        }

        

        return res;
    }



}
