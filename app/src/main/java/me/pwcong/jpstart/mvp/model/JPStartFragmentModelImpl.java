package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.comparator.JPItemComporator;
import me.pwcong.jpstart.comparator.JPItemWithViewTypeComparator;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.DBManager;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.bean.JPItemWithViewType;
import me.pwcong.jpstart.utils.ResourceUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.R.attr.data;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartFragmentModelImpl implements BaseModel.JPStartFragmentModel {



    @Override
    public void getData(final int type, Subscriber<List<JPItemWithViewType>> subscriber) {

        Observable.create(new Observable.OnSubscribe<List<JPItemWithViewType>>() {

            @Override
            public void call(Subscriber<? super List<JPItemWithViewType>> subscriber) {
                List<JPItemWithViewType> data = null;

                switch (type){
                    case Constants.TYPE_QINGYIN:
                        data = getYinWithViewType(Constants.TYPE_QINGYIN,Constants.COLUMN_QINGYIN);break;
                    case Constants.TYPE_ZHUOYIN:
                        data = getYinWithViewType(Constants.TYPE_ZHUOYIN,Constants.COLUMN_ZHUOYIN);break;
                    case Constants.TYPE_AOYIN:
                        data = getYinWithViewType(Constants.TYPE_AOYIN,Constants.COLUMN_AOYIN);break;
                    default:
                        break;
                }

                subscriber.onStart();
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);


    }

    public  List<JPItemWithViewType> getYinWithViewType(int type_yin,int column){


        List<JPItemWithViewType> res=new ArrayList<>();

        List<JPItem> yin = DBManager.getInstance().getYin(type_yin);

        addColumnHeader(res,yin);

        addRowHeader(res,yin,column);

        sort(res);

        return res;
    }


    public  void addColumnHeader(List<JPItemWithViewType> res, List<JPItem> list){

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

        Collections.sort(header, new JPItemComporator());

        for(JPItem item:header){
            res.add(new JPItemWithViewType(Constants.TYPE_HEADER,item));
        }

    }

    public  void addRowHeader(List<JPItemWithViewType> res,List<JPItem> list,int column){

        for(int i=0;i<list.size();i++){

            JPItem item = list.get(i);

            if(i%column==0){
                res.add(new JPItemWithViewType(Constants.TYPE_HEADER,
                        new JPItem(0,item.getRow(),0,
                                item.getHiragana()+ ResourceUtils.getString(App.getInstance(), R.string.row),
                                item.getKatakana()+ResourceUtils.getString(App.getInstance(),R.string.row),
                                item.getRome(),item.getCategory(),false)));
            }

            if(item.isExisted()){
                res.add(new JPItemWithViewType(Constants.TYPE_ITEM,item));
            }else {
                res.add(new JPItemWithViewType(Constants.TYPE_ITEM_DISABLE,item));
            }

        }

    }

    public  void sort(List<JPItemWithViewType> list){
        Collections.sort(list, new JPItemWithViewTypeComparator());
    }


}
