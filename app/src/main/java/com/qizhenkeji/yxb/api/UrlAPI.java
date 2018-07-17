package com.qizhenkeji.yxb.api;

import android.content.Context;

import com.cmm.zjwz.utils.SharePrefenceUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public final class UrlAPI {
    private static StringBuilder url;
    private static Context mContext;
    /**
     * 正式
     */
    public static final String HTTP_HOST = "https://xiangqi.qzyunfengkong.com/api/"; //数据库接口


    /**
     * get 请求
     */
    public static GetBuilder getGetBuilder(String str) {
        return OkHttpUtils.get().url(UrlAPI.HTTP_HOST + str)
                .addHeader("Cookie", "xxx")
                .addHeader("Accept", "application/json");
    }

    /**
     * post 请求
     *
     * @param str 接口
     * @return
     */
    public static PostFormBuilder getPostFormBuilder(Context context,String str) {
//        String cookie = SharePrefenceUtils.getString(mContext, "cookie") == "" ? "" : SharePrefenceUtils.getString(mContext, "cookie");
        return OkHttpUtils.post().url(UrlAPI.HTTP_HOST + str)
                .addHeader("Cookie", SharePrefenceUtils.getString(context, "cookie"))
                .addHeader("Accept", "application/json");
    }

    /**
     * get 请求
     *
     * @param urlStr
     * @return
     */
    public static Call getCall(String urlStr) {
        String url = HTTP_HOST + urlStr;
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder()
                .url(url)
                .header("Cookie", "xxx")
                .addHeader("Accept", "application/json")
                .method("GET", null)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        return okHttpClient.newCall(request);
    }

    /**
     * get 请求
     *
     * @param urlStr
     * @return
     */
    public static Call getCall2(String urlStr) {
        String url = HTTP_HOST + urlStr;
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder()
                .url(url)
//                .header("Cookie", "xxx")
                .addHeader("Accept", "application/json")
                .method("GET", null)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        return okHttpClient.newCall(request);
    }

    /**
     * post 请求
     *
     * @param urlStr
     * @return
     */
    public static Call postCall(Context context, String urlStr, String phone, String pwd, String code) {
        String url = HTTP_HOST + urlStr;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("user[login]", phone)
                .add("user[password]", pwd)
                .add("code", code)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .header("Cookie", SharePrefenceUtils.getString(context, "Cookie"))
                .post(body)
                .build();
        return okHttpClient.newCall(request);
    }

    /**
     * 获取验证码url
     *
     * @return
     */
    public static String getCodeUrl(String phone) {
        if (url == null) {
            url = new StringBuilder();
        } else {
            url.delete(0, url.length());
        }

        url.append(HTTP_HOST);
//        url.append("*/*");
        url.append("accounts/generate_phone_code");
        url.append("?phone=" + phone);
        return url.toString();
    }
//

    /**
     * 登录url
     *
     * @return
     */
    public static String getLoginUrl(String phone, String password, String code) {
//        if (url == null) {
//            url = new StringBuilder();
//        } else {
//            url.delete(0, url.length());
//        }
//        url.append(HTTP_HOST);
//        url.append("project/index");
//        url.append("?lat=" + lat);
//        url.append("&lng=" + lng);
        return url.toString();
    }


}
