package me.pwcong.jpstart.mvp.bean;

import java.util.Arrays;

/**
 * Created by Pwcong on 2016/10/1.
 */

public class YoudaoTranslateBean {


    String[] translation;
    String query;
    int errorCode;

    public YoudaoTranslateBean(String[] translation, String query, int errorCode) {
        this.translation = translation;
        this.query = query;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "BaiduTranslateBean{" +
                "translation=" + Arrays.toString(translation) +
                ", query='" + query + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }

    public String[] getTranslation() {
        return translation;
    }

    public void setTranslation(String[] translation) {
        this.translation = translation;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
