package me.pwcong.jpstart.mvp.bean;

/**
 * Created by Pwcong on 2016/10/2.
 */

public class TranslateSpinnerItem {

    private int icon;
    private String name;
    private boolean hasIcon;


    public TranslateSpinnerItem(int icon, String name, boolean hasIcon) {
        this.icon = icon;
        this.name = name;
        this.hasIcon = hasIcon;
    }

    @Override
    public String toString() {
        return "TranslateSpinnerItem{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                ", hasIcon=" + hasIcon +
                '}';
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }
}
