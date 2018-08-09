package me.pwcong.jpstart.component.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.FrameLayout;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.widget.webclient.MyWebChromeClient;
import me.pwcong.jpstart.widget.webclient.MyWebViewClient;

/**
 * Created by Pwcong on 2016/10/24.
 */

public class SupperzzleActivity extends BaseActivity {

    private static final String SUPPERZZLE_INDEX_URL = "file:///android_asset/webview/game/supperzzle/index.html";

    @BindView(R.id.layout_root)
    FrameLayout rootLayout;

    @BindView(R.id.wv)
    WebView webView;

    @Override
    protected int getViewId() {
        return R.layout.activity_supperzzle;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);

        MyWebChromeClient myWebChromeClient = new MyWebChromeClient();
        myWebChromeClient.setOnAlertListener(new MyWebChromeClient.OnAlertListener() {
            @Override
            public boolean call(WebView view, String url, String message, JsResult result) {
                Log.e(getClass().getSimpleName(), message);
                showSnackBar(rootLayout, message);
                result.confirm();
                return true;
            }
        });
        myWebChromeClient.setOnConfirmListener(new MyWebChromeClient.OnConfirmListener() {
            @Override
            public boolean call(WebView view, String url, String message, final JsResult result) {

                showAlertDialog(SupperzzleActivity.this, R.string.small_tips, message, R.drawable.ic_lightbulb_outline_black_24dp,
                        R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result.confirm();
                                dialogInterface.dismiss();
                            }
                        },
                        R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result.cancel();
                                dialogInterface.dismiss();
                            }
                        });
                return true;
            }
        });
        webView.setWebChromeClient(myWebChromeClient);

        MyWebViewClient myWebViewClient = new MyWebViewClient();
        webView.setWebViewClient(myWebViewClient);


    }

    @Override
    protected void doAction() {
        webView.loadUrl(SUPPERZZLE_INDEX_URL);
    }
}
