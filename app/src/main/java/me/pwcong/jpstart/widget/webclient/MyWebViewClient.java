package me.pwcong.jpstart.widget.webclient;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {


    private OnPageFinishedListener onPageFinishedListener;
    private OnPageStartedListener onPageStartedListener;
    private OnReceivedErrorListener onReceivedErrorListener;

    public interface OnPageStartedListener {
        void call(WebView view, String url, Bitmap favicon);
    }

    public interface OnPageFinishedListener {
        void call(WebView view, String url);
    }

    public interface OnReceivedErrorListener {
        void call(WebView view, WebResourceRequest request, WebResourceError error);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {

        if (onPageStartedListener != null) {
            onPageStartedListener.call(view, url, favicon);
        } else {
            super.onPageStarted(view, url, favicon);
        }


    }

    @Override
    public void onPageFinished(WebView view, String url) {

        if (onPageFinishedListener != null) {
            onPageFinishedListener.call(view, url);
        } else {

            super.onPageFinished(view, url);
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

        if (onReceivedErrorListener != null) {
            onReceivedErrorListener.call(view, request, error);
        } else {
            super.onReceivedError(view, request, error);
        }
    }
}
