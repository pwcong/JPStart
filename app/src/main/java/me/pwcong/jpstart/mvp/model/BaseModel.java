package me.pwcong.jpstart.mvp.model;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.bean.JPItemWithViewType;
import me.pwcong.jpstart.mvp.bean.JPTab;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/9/24.
 */

public interface BaseModel<T> {

    List<T> getData();

    interface JPStartTabFragmentModel extends BaseModel<JPTab>{
    }

    interface JPStartFragmentModel {
        void getData(int type, Subscriber<List<JPItemWithViewType>> subscriber);
    }


}
