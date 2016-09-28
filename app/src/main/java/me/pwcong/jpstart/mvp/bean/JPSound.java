package me.pwcong.jpstart.mvp.bean;

/**
 * Created by Pwcong on 2016/9/29.
 */

public class JPSound {

    String rome;
    int resId;

    public JPSound(String rome, int resId) {
        this.rome = rome;
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "JPSound{" +
                "rome='" + rome + '\'' +
                ", resId=" + resId +
                '}';
    }

    public String getRome() {
        return rome;
    }

    public void setRome(String rome) {
        this.rome = rome;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
