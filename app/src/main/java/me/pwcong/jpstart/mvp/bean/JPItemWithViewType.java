package me.pwcong.jpstart.mvp.bean;

/**
 * Created by Pwcong on 2016/9/28.
 */

public class JPItemWithViewType {

    int viewType;
    JPItem item;

    public JPItemWithViewType(int viewType, JPItem item) {
        this.viewType = viewType;
        this.item = item;
    }

    @Override
    public String toString() {
        return "JPItemWithViewType{" +
                "viewType=" + viewType +
                ", item=" + item +
                '}';
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public JPItem getItem() {
        return item;
    }

    public void setItem(JPItem item) {
        this.item = item;
    }
}
