package com.qizhenkeji.yxb.custom;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/10/22 0022.
 */

public final class Constant {

    /**
     * 获取应用程序的根目录
     */
    public static final String LZhyPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "LZhy" + File.separator + "LZYhll" + File.separator;
    /**
     * 获取应用程序的缓存目录
     */
    public static final String Diskcache = LZhyPath + "diskcache";

    /**
     * 用于判断是否需要登录，使用这个值得时候表示需要登录
     */
    public static final String need_login = "need_login";

    public static final String MD5_KEY = "lzhyapp.hll.html";

    //    支付code
    public static final String GATEWAY_ALIPAY = "100400";
    public static final String GATEWAY_WX = "100500";

    //    微信
    public static final String MERCHANTID_ALIPAY = "15120571300000";
    public static final String MERCHANTID_WX = "wx7de8b9ab1b056006";


    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    public static final int CROP_SMALL_PICTURE = 2;

    /**
     * 权限
     */
    public static final int READ_PHONE_STATE = 0X100;
    public static final int LOCATION_CODE = 0X101;
    public static final int CALL_PHONE_CODE = 0X102;
    public static final int CAMERA_CODE = 0X103;
    public static final int STORAGE_CODE = 0X104;

}
