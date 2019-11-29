package com.bawei.zhangjiafu1707a20191126;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bawei.zhangjiafu1707a20191126.Base.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private TabLayout tab;
    private ViewPager vp;
    private Fragment_Login fragment_1;
    private Fragment_Login fragment_2;
    private Fragment_Login fragment_3;
    private Fragment_Login fragment_4;
    private Fragment_Login fragment_5;
    private Fragment_Login fragment_6;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;
    private ImageView image;

    @Override
    protected void initData() {
        //创建fragment对象
        fragment_1 = new Fragment_Login();
        fragment_2 = new Fragment_Login();
        fragment_3 = new Fragment_Login();
        fragment_4 = new Fragment_Login();
        fragment_5 = new Fragment_Login();
        fragment_6 = new Fragment_Login();
        //创建集合
        fragments = new ArrayList<>();
        strings = new ArrayList<>();
        //存入集合
        fragments.add(fragment_1);
        fragments.add(fragment_2);
        fragments.add(fragment_3);
        fragments.add(fragment_4);
        fragments.add(fragment_5);
        fragments.add(fragment_6);
        strings.add("时事");
        strings.add("推荐");
        strings.add("视频");
        strings.add("要闻");
        strings.add("财经");
        strings.add("澎湃");
        //创建适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return strings.get(position);
            }
        });
        tab.setupWithViewPager(vp);

    }

    @Override
    protected void initView() {
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
        image = findViewById(R.id.image);
        //点击事件
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建跳转对象
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //设置类型
                intent.setType("image/*");
                //跳转
                startActivityForResult(intent,1000);
            }
        });
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //符合条件
        if(requestCode==1000&&resultCode==-1){
            Uri data1 = data.getData();
            try {
                //获取图片
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
                //给图片控件赋值
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
