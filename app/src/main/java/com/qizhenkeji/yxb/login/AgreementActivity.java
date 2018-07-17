package com.qizhenkeji.yxb.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.custom.BaseTitlebar;
import com.qizhenkeji.yxb.R;

/**
 * Created by hc101 on 18/6/15.
 */

public class AgreementActivity extends BaseActivity {
    private WebView wvAgreement;
    private String loadUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        wvAgreement = (WebView) findViewById(R.id.wv_agreement);
        initTitlebar();
        localHtml();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initTitlebar() {
        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("用户协议");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }

    private void localHtml() {
        loadUrl = "file:///android_asset/agreement.html";
        try {
            WebSettings webSettings = wvAgreement.getSettings();
            //支持javascript
            webSettings.setJavaScriptEnabled(true);
            // 设置可以支持缩放
            webSettings.setSupportZoom(true);
            // 设置出现缩放工具
            webSettings.setBuiltInZoomControls(true);
            //扩大比例的缩放
            webSettings.setUseWideViewPort(true);
            //自适应屏幕
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setLoadWithOverviewMode(true);
            wvAgreement.loadUrl(loadUrl);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wvAgreement.canGoBack()) {
            wvAgreement.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
