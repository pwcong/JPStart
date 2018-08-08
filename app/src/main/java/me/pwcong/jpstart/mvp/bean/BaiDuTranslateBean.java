package me.pwcong.jpstart.mvp.bean;

import java.util.Arrays;

/**
 * Created by Pwcong on 2016/10/1.
 */

public class BaiduTranslateBean {

    private String from;
    private String to;
    private TranslateResult[] trans_result;
    private String error_code;
    private String error_msg;

    public BaiduTranslateBean(String from, String to, TranslateResult[] trans_result, String error_code, String error_msg) {
        this.from = from;
        this.to = to;
        this.trans_result = trans_result;
        this.error_code = error_code;
        this.error_msg = error_msg;
    }

    @Override
    public String toString() {
        return "BaiduTranslateBean{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + Arrays.toString(trans_result) +
                ", error_code='" + error_code + '\'' +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public TranslateResult[] getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(TranslateResult[] trans_result) {
        this.trans_result = trans_result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public class TranslateResult{

        String src;
        String dst;

        public TranslateResult(String src, String dst) {
            this.src = src;
            this.dst = dst;
        }

        @Override
        public String toString() {
            return "TranslateResult{" +
                    "src='" + src + '\'' +
                    ", dst='" + dst + '\'' +
                    '}';
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }


}
