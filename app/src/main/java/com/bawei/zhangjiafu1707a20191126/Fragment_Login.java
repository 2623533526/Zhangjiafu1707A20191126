package com.bawei.zhangjiafu1707a20191126;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.bawei.zhangjiafu1707a20191126.Base.BaseFragment;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Login extends BaseFragment {

    private XListView xList;
    private ImageView vis_image;
    int page=1;
    List<Baen.ListdataBean> list = new ArrayList<>();

    @Override
    protected void initData() {
     getData();
    }

    @Override
    protected void initView(View inflate) {
        //找控件
        xList = inflate.findViewById(R.id.xlist);
        vis_image = inflate.findViewById(R.id.vis_image);
        //设置下拉下拉
        xList.setPullLoadEnable(true);
        xList.setPullRefreshEnable(true);
        xList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                //page赋值为1
                page=1;
                //list的列表全部清空
                list.clear();
                //执行封装方法
                getData();
                //停止刷新
                xList.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                //page在原来的基础上加一
                page++;
                //执行封装方法
                getData();
                //停止加载更多
               xList.stopLoadMore();
            }
        });
    }
    @Override
    protected int layoutid() {
        return R.layout.fragment_login;
    }


    public void getData() {
          if(NetUtils.getInstance().has(getContext())){
              //网络正常时隐藏图片，显示列表
              xList.setVisibility(View.VISIBLE);
              vis_image.setVisibility(View.INVISIBLE);
              String ur="";
              //根据page的值，执行不同的json
              if(page==1){
                   ur="http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
              }else if(page==2) {
                  ur="http://blog.zhaoliang5156.cn/api/pengpainews/pengpai2.json";
              }else {
                  ur="http://blog.zhaoliang5156.cn/api/pengpainews/pengpai3.json";
              }
              NetUtils.getInstance().getString(ur, new NetUtils.Datat() {
                  @Override
                  public void get(String str) {
                      //Log打印
                      Log.i("json==",str);
                      //创建json对象
                      Gson gson = new Gson();
                      //开始解析
                      Baen baen = gson.fromJson(str, Baen.class);
                      //取出集合
                      List<Baen.ListdataBean> listdata = baen.getListdata();
                      //将集合存入大集合中
                      list.addAll(listdata);
                      //创建适配器对象
                      MyAdapter myAdapter = new MyAdapter(getActivity(), list);
                      //添加适配器
                      xList.setAdapter(myAdapter);
                      //点击监听
                      xList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                          @Override
                          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                              //创建intent对象
                              Intent intent = new Intent(getContext(), SeorActivity.class);
                              //存入网址
                              intent.putExtra("key","https://www.thepaper.cn/newsDetail_forward_4923534");
                              //完成跳转
                              startActivity(intent);
                              //关闭本界面
                              getActivity().finish();
                          }
                      });
                  }
              });
          }else {
              //网络正常时显示图片，隐藏列表
              vis_image.setVisibility(View.VISIBLE);
              xList.setVisibility(View.INVISIBLE);
          }
    }
}
