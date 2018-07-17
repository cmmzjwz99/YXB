package com.cmm.zjwz.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wenzhoulianghaodai on 2017/8/14.
 */

public class CookiesUtil {

    private Context context;
    private static HashMap<String,String> CookieContiner=new HashMap<String,String>() ;
    /**
     * 保存Cookie
     * @param resp
     */
    public void SaveCookies(HttpResponse httpResponse) {
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        String headerstr=headers.toString();
        if (headers == null)
            return;

        for(int i=0;i<headers.length;i++)
        {
            String cookie=headers[i].getValue();
            String[]cookievalues=cookie.split(";");
            for(int j=0;j<cookievalues.length;j++)
            {
                String[] keyPair=cookievalues[j].split("=");
                String key=keyPair[0].trim();
                String value=keyPair.length>1?keyPair[1].trim():"";
                CookieContiner.put(key, value);
            }
        }
    }
    /**
     * 增加Cookie
     * @param request
     */
    public void AddCookies(HttpPost request) {
        StringBuilder sb = new StringBuilder();
        Iterator iter = CookieContiner.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = entry.getKey().toString();
            String val = entry.getValue().toString();
            sb.append(key);
            sb.append("=");
            sb.append(val);
            sb.append(";");
        }
        request.addHeader("cookie", sb.toString());
    }


//    try{
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//        HttpGet httpget = new HttpGet("http://www.hlovey.com/");
//        HttpResponse response = httpclient.execute(httpget);
//        HttpEntity entity = response.getEntity();
//        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
//        if (entity != null) {
//            entity.consumeContent();
//        }
//
//        if (cookies.isEmpty()) {
//            Log.i(TAG, "NONE");
//        } else {
//            for (int i = 0; i < cookies.size(); i++) {
//                Log.i(TAG,"- domain " + cookies.get(i).getDomain());
//                Log.i(TAG,"- path " + cookies.get(i).getPath());
//                Log.i(TAG,"- value " + cookies.get(i).getValue());
//                Log.i(TAG,"- name " + cookies.get(i).getName());
//                Log.i(TAG,"- port " + cookies.get(i).getPorts());
//                Log.i(TAG,"- comment " + cookies.get(i).getComment());
//                Log.i(TAG,"- commenturl" + cookies.get(i).getCommentURL());
//                Log.i(TAG,"- all " + cookies.get(i).toString());
//            }
//        }
//        httpclient.getConnectionManager().shutdown();
//
//    }catch(Exception e){
//        //Todo
//    }finally{
//        //Todo
//    }

    /**声明一些数据库操作的常量*/
    private SQLiteDatabase mDatabase = null;
    private static final String DATABASE_FILE = "webview.db";
    private static final String COOKIES_NAME_COL = "name";
    private static final String COOKIES_VALUE_COL = "value";
    private static final String COOKIES_DOMAIN_COL = "domain";
    private static final String COOKIES_PATH_COL = "path";
    private static final String COOKIES_EXPIRES_COL = "expires";
    private static final String COOKIES_SECURE_COL = "secure";

//    mDatabase = context.openOrCreateDatabase(DATABASE_FILE, 0, null);
    //创建cookie数据库

//    if (mDatabase != null) {
//        // cookies
//        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS cookies "
//                + " (_id INTEGER PRIMARY KEY, "
//                + COOKIES_NAME_COL + " TEXT, " + COOKIES_VALUE_COL
//                + " TEXT, " + COOKIES_DOMAIN_COL + " TEXT, "
//                + COOKIES_PATH_COL + " TEXT, " + COOKIES_EXPIRES_COL
//                + " INTEGER, " + COOKIES_SECURE_COL + " INTEGER" + ");");
//        mDatabase.execSQL("CREATE INDEX IF NOT EXISTS cookiesIndex ON "
//                + "cookies" + " (path)");
//    }


    /*写cookie*/
    public void addCookie(Cookie cookie) {
        if (cookie.getDomain() == null || cookie.getPath() == null || cookie.getName() == null
                || mDatabase == null) {
            return;
        }
        String mCookieLock = "asd";
        synchronized (mCookieLock) {
            ContentValues cookieVal = new ContentValues();
            cookieVal.put(COOKIES_DOMAIN_COL, cookie.getDomain());
            cookieVal.put(COOKIES_PATH_COL, cookie.getPath());
            cookieVal.put(COOKIES_NAME_COL, cookie.getName());
            cookieVal.put(COOKIES_VALUE_COL, cookie.getValue());

            mDatabase.insert("cookies", null, cookieVal);

        }
    }
}
