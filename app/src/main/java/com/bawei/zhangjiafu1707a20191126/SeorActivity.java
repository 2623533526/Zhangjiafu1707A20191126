package com.bawei.zhangjiafu1707a20191126;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.zhangjiafu1707a20191126.Base.BaseActivity;

public class SeorActivity extends BaseActivity {


    private WebView web;

    @Override
    protected void initData() {
        //获取接受的key
        String key = getIntent().getStringExtra("key");
        //设置支持JavaScript
        web.getSettings().setJavaScriptEnabled(true);
        //web打开指定网址
        web.loadUrl(key);
        //设置监听
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.i("xxx","在本APP的界面打开");
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i("xxx","加载开始");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i("xxx","加载结束");
                super.onPageFinished(view, url);
            }
        });
        //设置监听
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.i("xxx","进度改变");
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.i("xxx","标题");
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                Log.i("xxx","图标");
                super.onReceivedIcon(view, icon);
            }
        });
    }

    @Override
    protected void initView() {
        web = findViewById(R.id.web);
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_seor;
    }
}
