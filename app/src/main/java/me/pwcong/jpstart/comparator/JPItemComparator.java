package me.pwcong.jpstart.comparator;

import java.util.Comparator;

import me.pwcong.jpstart.mvp.bean.JPItem;

/**
 * Created by Pwcong on 2016/9/28.
 */

public class JPItemComparator implements Comparator<JPItem> {

    @Override
    public int compare(JPItem o1, JPItem o2) {

        if (o1.getRow() < o2.getRow()) {
            return -1;
        } else if (o1.getRow() == o2.getRow()) {

            if (o1.getColumn() < o2.getColumn()) {
                return -1;
            } else if (o1.getColumn() == o2.getColumn()) {
                return 0;
            } else {
                return 1;
            }

        } else {
            return 1;
        }
    }
}
