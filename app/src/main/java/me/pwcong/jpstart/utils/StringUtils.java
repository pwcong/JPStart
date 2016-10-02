package me.pwcong.jpstart.utils;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class StringUtils {

    private StringUtils(){
        throw new RuntimeException("(￣y▽,￣)╭ ");
    }


    public static String checkNullOrEmpty(String ss,String ds){
        if(ss==null||ss.isEmpty())
            return ds;
        else
            return ss;
    }

    public static boolean isNullOrEmpty(String s){

        return s == null || s.isEmpty();

    }



}
