package com.bawei.zhangjiafu1707a20191126.Base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * 张家辅
 * 2019年11月26日
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutid());
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layoutid();
}
