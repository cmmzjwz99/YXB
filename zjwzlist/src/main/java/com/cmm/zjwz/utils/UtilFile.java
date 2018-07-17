package com.cmm.zjwz.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public final class UtilFile {

    /**
     * @说明： 保存图片到本地
     */
    public static String rootDir = Environment.getExternalStorageDirectory() .getAbsolutePath()+ File.separator;

    public static File savePicture(Bitmap bitmap, String imageName, String fileName, boolean needReturn){
        String pathName = rootDir  + fileName + File.separator;
        String imgName = imageName+".png";
        File myDir = new File(pathName);//目录
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        File image = new File(myDir,imgName);//文件

        Log.i("TAG", "savePicture: ");
        FileOutputStream out;
        if (image.exists()) {
            image.delete();
        }
        try
        {
            image.createNewFile();
            out = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            if (!needReturn) {
                return null;
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            Log.i("qqqq", "savePicture: ");
            e.printStackTrace();
        }
        return image;
    }
    /**
     * 建立HTTP请求，并获取Bitmap对象。
     *
     * @param urlString
     *            图片的URL地址
     * @return 解析后的Bitmap对象
     */
    public static boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(),
                    8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     *_______________#########_______________________
     *______________############_____________________
     *______________#############____________________
     *_____________##__###########___________________
     *____________###__######_#####__________________
     *____________###_#######___####_________________
     *___________###__##########_####________________
     *__________####__###########_####_______________
     *________#####___###########__#####_____________
     *_______######___###_########___#####___________
     *_______#####___###___########___######_________
     *______######___###__###########___######_______
     *_____######___####_##############__######______
     *____#######__#####################_#######_____
     *____#######__##############################____
     *___#######__######_#################_#######___
     *___#######__######_######_#########___######___
     *___#######____##__######___######_____######___
     *___#######________######____#####_____#####____
     *____######________#####_____#####_____####_____
     *_____#####________####______#####_____###______
     *______#####______;###________###______#________
     *________##_______####________####______________
     */


    /**
     * 注释:  写文件
     */
    public static File writeLog(String str){


        File file = null;
        Log.i("file", "__________________________________________________________________写入成功: "+str);
        try
        {
            String path=Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                    + "crash_lzhy_moneyhll.txt";
            file=new File(path);
            if(!file.exists()){
                file.createNewFile();
                // Toast.makeText(UtilActivityManager.getInstance().getCurrentActivity(), "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            }

            FileOutputStream out=new FileOutputStream(file,false); //如果追加方式用true,此处覆盖
            StringBuffer sb=new StringBuffer();
            byte[] bytes = str.getBytes();
            out.write(bytes);
            //out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
            out.close();
            if (file.exists()) {
                Log.i("file", "writeLog: "+file.length());
                Log.i("file", "writeLog: "+file);
                Log.i("file", "__________________________________________________________________写入成功: "+file);
                return file;
            }else {
                Log.i("file", "__________________________________________________________________写入失败: "+file);
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex.getStackTrace());
        }
        return null;
    }

//    /**
//     * 注释: 上传文件
//     */
//    public static void uploadFile(){
//        File file = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
//                + "crash_lzhy_moneyhll.txt");
//        if (!file.exists())
//        {
////            Log.i("file", "uploadFile: 文件不存在，请修改文件路径");
//            return;
//        }
////        Log.i("file", "uploadFile: 正在上传");
//        OkHttpUtils.post()//
//                .addFile("file", "crash_lzhy_moneyhll.txt", file)//
//                .url("")//
//                .build()//
//                .execute(new MyStringCallback( file));
//    }

    /**
     * 将字符串写入文件
     * @param content
     */
    public static void writeStr2Log( String content){
        try {

//            content = "This is the content to write into file";

            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                    + "crash_lzhy_moneyhll.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
