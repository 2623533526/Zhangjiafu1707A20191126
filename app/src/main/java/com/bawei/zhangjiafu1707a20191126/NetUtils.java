package com.bawei.zhangjiafu1707a20191126;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 张家辅
 * 2019年11月26日
 * 工具类
 */
public class NetUtils {
    //单例模式
    private static NetUtils netUtils =new NetUtils();

    private NetUtils() {
    }

    public static NetUtils getInstance() {
        return netUtils;
    }
    //判断网络是否正常
    public boolean has(Context context){
        //获取网络管理者对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获得网络信息
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //判断网络是否正常
        if(activeNetworkInfo!=null&&activeNetworkInfo.isAvailable()){
            return true;
        }else {
            return false;
        }
    }
    //获取字符串的方法
    @SuppressLint("StaticFieldLeak")
    public void getString(final String strurl, final Datat datat){
        //网络请求
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                //接口回调
                datat.get(s);
                //Log打印
                Log.i("xxx",s);
            }
            @Override
            protected String doInBackground(Void... voids) {
                InputStream inputStream = null;
                String ioString = null;
                try {
                    //获取远程连接对象
                    URL url = new URL(strurl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //设置网络请求方式
                    httpURLConnection.setRequestMethod("GET");
                    //设置连接超时
                    httpURLConnection.setConnectTimeout(5000);
                    //设置读取超时
                    httpURLConnection.setReadTimeout(5000);
                    //开启
                    httpURLConnection.connect();
                    //判断访问网络成功
                    if(httpURLConnection.getResponseCode()==200){
                        //获取流
                        inputStream  = httpURLConnection.getInputStream();
                        //通过封装的方法将io流转成字符串
                     ioString =  getIoString(inputStream);
                    }else {
                        //Log打印
                        Log.i("xxx","网络请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //关流
                    if(inputStream!=null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //返回
                return ioString;
            }
        }.execute();
    }
    //将io流转换成字符串的封装方法
    public String getIoString(InputStream inputStream) throws IOException {
        String s = null;
        //三件套
        int len=-1;
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //循环取出并存入
        while ((len=inputStream.read(bytes))!=-1){
            byteArrayOutputStream.write(bytes,0,len);
        }
        //转换成字节数组
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        //转换成字符串
       s = new String(bytes1);
       //返回字符串
        return s;
    }


    //获取图片的方法
    @SuppressLint("StaticFieldLeak")
    public void getBitmap(final String strurl, final ImageView imageView){
        //网络请求
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                //给控件赋值
                imageView.setImageBitmap(bitmap);
            }
            @Override
            protected Bitmap doInBackground(Void... voids) {
                InputStream inputStream = null;
                Bitmap ioBitmap = null;
                try {
                    //获取远程连接对象
                    URL url = new URL(strurl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //设置网络请求方式
                    httpURLConnection.setRequestMethod("GET");
                    //设置连接超时
                    httpURLConnection.setConnectTimeout(5000);
                    //设置读取超时
                    httpURLConnection.setReadTimeout(5000);
                    //开启
                    httpURLConnection.connect();
                    //判断访问网络成功
                    if(httpURLConnection.getResponseCode()==200){
                        //获取流
                        inputStream  = httpURLConnection.getInputStream();
                        //通过封装的方法将io流转成图片
                     ioBitmap  = getIoBitmap(inputStream);

                    }else {
                        //Log打印
                        Log.i("xxx","网络请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //关流---9999996666656555556655656
                    if(inputStream!=null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //返回
                return ioBitmap;
            }
        }.execute();
    }
    //将io流转换成字符串的封装方法
    public Bitmap getIoBitmap(InputStream inputStream) throws IOException {
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }








  public interface Datat{
        void get(String str);
    }
}
