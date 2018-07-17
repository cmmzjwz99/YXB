package com.cmm.zjwz.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * Created by cmm on 2016/7/7.
 */
public final class Utils {

    public static int getColor(Context context, int id) {
        int color;
        color = ContextCompat.getColor(context, id);
        return color;
    }

    public static HashMap<String, String> getMapByLink(String url) {
        HashMap<String, String> map = new HashMap<String, String>(10);

        if (url.indexOf("?") != -1) {
            String param = url.substring(url.indexOf("?") + 1);

            String[] ps = param.split("&");
            for (int i = 0; i < ps.length; i++) {
                String[] kv = ps[i].split("=");
                String val = "";
                if (kv.length > 1) {
                    val = kv[1];
                }
                map.put(kv[0], val);
            }
        }
        return map;
    }

    /**
     * 获取当前时间戳
     */
    public static long getNowTimeTemp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取MD5值
     */
    public static String getMD5(String str) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobile(String mobiles) {
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    public static String getVersion(Context context) {
        String versionName = "";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = pInfo.versionName;
        } catch (Exception e) {
//            Global.errorLog(e);
        }
        return versionName;
    }

    //验证手机号是否正确ֻ
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //验证 密码由字母和数字组成
    public static final boolean isRightPwd(String pwd) {
        Pattern p = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$)[0-9a-zA-Z]{8,16}$");
        Matcher m = p.matcher(pwd);
        return m.matches();
    }

    //验证身份证号码
    public static boolean idCardNumber(String number) {
        String rgx = "^\\d{15}|^\\d{17}([0-9]|X|x)$";

        return isCorrect(rgx, number);
    }

    //验证身份证号码
    public boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

    //正则验证
    public static boolean isCorrect(String rgx, String res) {
        Pattern p = Pattern.compile(rgx);

        Matcher m = p.matcher(res);

        return m.matches();
    }

    /**
     * 隐藏键盘
     */
    public static void dismissKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
    /**
     * 设置土司
     */
    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 切割字符串 String[] strs=str.split(",");
     */
    public static String getSplitStr(String str,int i){
        String[] strs = str.split(",");
        return strs[i];
    }

    /**
     * toast 被点击了之后不可重复多次点击
     */
    public static void showToast(Context context, String content) {
        Toast toast = null;
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 设置toast 时间
     */
    public static void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }




    public static final String hexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0)
            return "";
        StringBuffer hs = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            String s = Integer.toHexString(bytes[i] & 0xFF);
            if (s.length() < 2)
                hs.append(0);
            hs.append(s);
        }
        return hs.toString();
    }

    public static final String toSHA1(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(value.getBytes());
            return hexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用md5的算法进行加密
     */
    public static final String getMD5Value(String value) {
        try {
            byte[] b = MessageDigest.getInstance("MD5").digest(value.getBytes("UTF-8"));
            return hexString(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Base64加密
    public static String getBase64(String str) {
        String result = "";
        if( str != null) {
            try {
                result = new String(Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Base64解密
    public static String getFromBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.decode(str, Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }

    /**
     * json转对象
     */
    public static <T extends Object> T jsonToClass(String str, Type type) {
        T result = null;
        if (null != gson) {
            result = gson.fromJson(str, type);
        }
        return result;
    }

    /**
     * 显示对话框
     * @param context
     * @param message
     * @param button
     * @param onClickListener
     */
    public static void showMyDialog(Context context,String message,String button, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setPositiveButton(button, onClickListener);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();
    }



//    /**
//     * 自定义对话框
//     * @param context
//     * @param message
//     * @param button
//     * @param onClickListener
//     */
//    public static void showCustomDialog(Context context,String massage,String button,View.OnClickListener onClickListener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        View view = View.inflate(context, R.layout.dialog_base, null);
//        builder.setView(view);
//        final AlertDialog dialog = builder.create();
//        dialog.show();
//
//        TextView tvTitle = (TextView) view.findViewById(R.id.massage);
//        tvTitle.setText(massage);
//
//        Button btConfirm = (Button) view.findViewById(R.id.positiveButton);
//        Button btCancel = (Button) view.findViewById(R.id.negativeButton);
//
//        btConfirm.setText(button);
//        btConfirm.setOnClickListener(onClickListener);
//
//        btCancel.setText("取消");
//        btCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.dismiss();
//    }


//    /**
//     * 分享
//     * @param mContext
//     * @param title
//     * @param imageUrl
//     * @param content
//     * @param url
//     * @param parentView
//     */
//    public static void ShareWX(Context mContext,String title, String imageUrl,String content,  String url, View parentView) {
//        SharePop mSharePop;
//        mSharePop = SharePop.newInstance(mContext);
//        if (!StringUtils.isEmpty(title)) {
//            mSharePop.setTitle(title);
//        }
//        if (!StringUtils.isEmpty(content)) {
//            mSharePop.setShareContent(content);
//        }
//        if (!StringUtils.isEmpty(imageUrl)) {
//            mSharePop.setSharePic(imageUrl);
//        }
//        mSharePop.setShareUrl(url);
//        mSharePop.showAtBottom(parentView);
//    }
}
