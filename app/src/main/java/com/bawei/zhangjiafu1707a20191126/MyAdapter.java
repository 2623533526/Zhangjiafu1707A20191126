package com.bawei.zhangjiafu1707a20191126;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    List<Baen.ListdataBean> listdata;
    public MyAdapter(Context context, List<Baen.ListdataBean> listdata) {
        this.context=context;
        this.listdata=listdata;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = listdata.get(position).getItemType();
        if(itemType==1) {
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHloder viewHloder = null;
        if(view==null){
            if(getItemViewType(i)==0) {
                view = View.inflate(context, R.layout.fragment_left, null);
            }else {
                view = View.inflate(context, R.layout.fragment_center, null);
            }
            viewHloder = new ViewHloder();
            viewHloder.imageView=view.findViewById(R.id.lay_image);
            viewHloder.textView=view.findViewById(R.id.lay_text);
            view.setTag(viewHloder);
        }else {
            viewHloder=(ViewHloder)view.getTag();
        }
        Baen.ListdataBean listdataBean = listdata.get(i);
        viewHloder.textView.setText(listdataBean.getContent());
        String imageurl = listdataBean.getImageurl();
        NetUtils.getInstance().getBitmap(imageurl,viewHloder.imageView);
        return view;
    }
    class ViewHloder{
        TextView textView;
        ImageView imageView;
    }
}
