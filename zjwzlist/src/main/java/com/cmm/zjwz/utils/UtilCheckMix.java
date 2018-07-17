package com.cmm.zjwz.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created cmm on 2017/1/7.
 * 多种校验功能
 */

public final class UtilCheckMix {
    /**
     *  判断手机格式是否正确
     *  第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    public static boolean isMobileNO(String mobiles) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][34578]\\d{9}" ;
        if (TextUtils.isEmpty(mobiles)) return false ;
        else return mobiles.matches( telRegex ) ;
    }
    /**
     * 通过正则表达式来判断。只允许显示字母、数字和汉字。
     */

    public static String stringFilter(String str,String reg) throws PatternSyntaxException {
        // 只允许字母、数字和 汉字
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        if (reg.contains("地址")) {
            regEx = "[^a-zA-Z0-9\u4E00-\u9FA5\\-#\\_]";
        }else if (reg.contains("身份证")) {
            Log.i("stringFilter", "stringFilter: ");
            regEx = "[^a-zA-Z0-9]";
        }
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 对 编辑框 文本类型检查
     */
    public static boolean editTextCheck(@NonNull String editText, @NonNull Context context, String msg) {
        String strAddress = stringFilter(editText,msg);
        if (!editText.equals(strAddress)) {
//            UtilToast.getInstance().showDragonInfo(msg);
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断是否安装了微信
     *
     * @param context 上下文
     * @return true/false
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 获取网络状态
     */

    public static boolean networkIsAvailable(Context c) {
        Context context = c.getApplicationContext();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
