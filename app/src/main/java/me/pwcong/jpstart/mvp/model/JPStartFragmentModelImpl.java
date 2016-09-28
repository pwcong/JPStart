package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.bean.JPItemWithViewType;
import me.pwcong.jpstart.utils.ResourceUtils;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragmentModelImpl implements BaseModel.JPStartFragmentModel {

    @Override
    public List<JPItemWithViewType> getData(int type) {

        switch (type){
            case Constants.TYPE_QINGYIN:
                return getYinWithViewType(Constants.TYPE_QINGYIN,Constants.COLUMN_QINGYIN);
            case Constants.TYPE_ZHUOYIN:
                return getYinWithViewType(Constants.TYPE_ZHUOYIN,Constants.COLUMN_ZHUOYIN);
            case Constants.TYPE_AOYIN:
                return getYinWithViewType(Constants.TYPE_AOYIN,Constants.COLUMN_AOYIN);
            default:
                return null;
        }
    }

    public static List<JPItemWithViewType> getYinWithViewType(int type_yin,int column){

        List<JPItemWithViewType> res=new ArrayList<>();

        List<JPItem> yin = DBManager.getInstance().getYin(type_yin);

        Collections.sort(yin, new Comparator<JPItem>() {
            @Override
            public int compare(JPItem o1, JPItem o2) {
                return o1.getRow()<=o2.getRow()&&o1.getColumn()<=o2.getColumn()?1:0;
            }
        });

        addColumnHeader(res,yin);

        addRowHeader(res,yin,column);

        sort(res);

        return res;

    }



    public static void addColumnHeader(List<JPItemWithViewType> res,List<JPItem> list){

        List<JPItem> header = new ArrayList<>();

        header.add(new JPItem(0,0,0,"","","",0,false));

        for(int i=0;i<list.size();i++){

            JPItem item = list.get(i);

            if(list.get(i).getRow() == Constants.ROW_FIRST){
                header.add(new JPItem(0,0,i+1,item.getHiragana()+ ResourceUtils.getString(App.getInstance(), R.string.column),
                        item.getKatakana()+ ResourceUtils.getString(App.getInstance(), R.string.column),
                        item.getRome(),item.getCategory(),false));
            }

        }

        Collections.sort(header, new Comparator<JPItem>() {
            @Override
            public int compare(JPItem o1, JPItem o2) {
                return o1.getColumn()<=o2.getColumn()?1:0;
            }
        });

        for(JPItem item:header){
            res.add(new JPItemWithViewType(Constants.TYPE_HEADER,item));
        }

    }

    public static void addRowHeader(List<JPItemWithViewType> res,List<JPItem> list,int column){

        for(int i=0;i<list.size();i++){

            JPItem item = list.get(i);

            if(i%column==0){
                res.add(new JPItemWithViewType(Constants.TYPE_HEADER,
                        new JPItem(0,item.getRow(),0,
                                item.getHiragana()+ ResourceUtils.getString(App.getInstance(), R.string.row),
                                item.getKatakana()+ResourceUtils.getString(App.getInstance(),R.string.row),
                                item.getRome(),item.getCategory(),false)));
            }

            res.add(new JPItemWithViewType(Constants.TYPE_ITEM,item));
        }

    }

    public static void sort(List<JPItemWithViewType> list){
        Collections.sort(list, new Comparator<JPItemWithViewType>() {
            @Override
            public int compare(JPItemWithViewType o1, JPItemWithViewType o2) {
                return o1.getItem().getRow()<=o2.getItem().getRow()&&o1.getItem().getColumn()<=o2.getItem().getColumn()?1:0;
            }
        });
    }


}
