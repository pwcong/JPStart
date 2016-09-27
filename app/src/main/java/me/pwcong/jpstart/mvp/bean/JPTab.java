package me.pwcong.jpstart.mvp.bean;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPTab {

    int type;
    String title;

    public JPTab(int type, String title) {
        this.type = type;
        this.title = title;
    }

    @Override
    public String toString() {
        return "JPTab{" +
                "type=" + type +
                ", title='" + title + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
