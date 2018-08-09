package me.pwcong.jpstart.widget.webclient;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyWebChromeClient extends WebChromeClient {

    private OnAlertListener onAlertListener;
    private OnConfirmListener onConfirmListener;
    private OnPromptListener onPromptListener;

    public MyWebChromeClient() {
    }

    public interface OnAlertListener {
        boolean call(WebView view, String url, String message, JsResult result);
    }

    public interface OnConfirmListener {
        boolean call(WebView view, String url, String message, JsResult result);
    }

    public interface OnPromptListener {
        boolean call(WebView view, String url, String message, String defaultValue, JsPromptResult result);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

        if (onAlertListener != null) {
            return onAlertListener.call(view, url, message, result);
        }

        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

        if (onConfirmListener != null) {
            return onConfirmListener.call(view, url, message, result);
        }

        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

        if (onPromptListener != null) {
            return onPromptListener.call(view, url, message, defaultValue, result);
        }

        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    public OnAlertListener getOnAlertListener() {
        return onAlertListener;
    }

    public void setOnAlertListener(OnAlertListener onAlertListener) {
        this.onAlertListener = onAlertListener;
    }

    public OnConfirmListener getOnConfirmListener() {
        return onConfirmListener;
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public OnPromptListener getOnPromptListener() {
        return onPromptListener;
    }

    public void setOnPromptListener(OnPromptListener onPromptListener) {
        this.onPromptListener = onPromptListener;
    }
}
