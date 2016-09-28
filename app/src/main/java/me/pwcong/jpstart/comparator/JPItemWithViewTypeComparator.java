package me.pwcong.jpstart.comparator;

import java.util.Comparator;

import me.pwcong.jpstart.mvp.bean.JPItemWithViewType;

/**
 * Created by Pwcong on 2016/9/28.
 */

public class JPItemWithViewTypeComparator implements Comparator<JPItemWithViewType> {

    @Override
    public int compare(JPItemWithViewType o1, JPItemWithViewType o2) {
        if (o1.getItem().getRow() < o2.getItem().getRow()) {
            return -1;
        } else if(o1.getItem().getRow()==o2.getItem().getRow()){

            if(o1.getItem().getColumn()<=o2.getItem().getColumn()){
                return -1;
            }else
                return 1;

        } else{
            return 1;
        }
    }
}
