package com.qizhenkeji.yxb.text;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cmm.zjwz.custom.BaseActivity;
import com.cmm.zjwz.dialog.ChoosePicPop;
import com.cmm.zjwz.utils.PrintLog;
import com.cmm.zjwz.utils.SharePrefenceUtils;
import com.cmm.zjwz.utils.UtilFile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qizhenkeji.yxb.R;
import com.qizhenkeji.yxb.api.UrlAPI;
import com.qizhenkeji.yxb.custom.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.Call;

import static com.qizhenkeji.yxb.custom.Constant.CHOOSE_PICTURE;
import static com.qizhenkeji.yxb.custom.Constant.CROP_SMALL_PICTURE;
import static com.qizhenkeji.yxb.custom.Constant.TAKE_PICTURE;

public class UploadPhotosActivity extends BaseActivity implements View.OnClickListener {

    private ChoosePicPop mPicPop;
    private TextView tvUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);

        initView();
    }

    private void initView() {
        tvUpload = (TextView) findViewById(R.id.tv_upload);
        tvUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        View.OnClickListener itemsOnClick = new View.OnClickListener() {

            public void onClick(View v) {
                if (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    switch (v.getId()) {
                        case R.id.photograph:
                            if (hasPermission(Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                                onPhotographClick();
                            } else {
                                repuestPermission(Constant.CAMERA_CODE, android.Manifest.permission.CAMERA);
                                toast( "请开启相机权限");
                                mPicPop.dismiss();
                            }
                            break;
                        case R.id.photo_gallery:
                            onPhotoGalleryClick();
                            break;
                        case R.id.cancel:
                            mPicPop.dismiss();
                            break;
                        default:
                            break;
                    }
                    mPicPop.dismiss();
                } else {
                    repuestPermission(Constant.STORAGE_CODE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    toast( "请开启读写权限");
                }
            }

        };

        mPicPop = new ChoosePicPop(this, itemsOnClick);
//        mPicPop.showAtBottom((View) tvUpload.getParent());
        mPicPop.showAtCenter(v);  //showAtLocation
    }

    //照相
    public void onPhotographClick() {

        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //下面这句指定调用相机拍照后的照片存储的路径
        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp_image.jpg")));
        startActivityForResult(takeIntent, TAKE_PICTURE);
    }

    //图库
    public void onPhotoGalleryClick() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pickIntent, CHOOSE_PICTURE);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_PICTURE:// 直接从相册获取
                    try {
                        startPhotoZoom(data.getData());
                    } catch (NullPointerException e) {
                        e.printStackTrace();// 用户点击取消操作
                    }
                    break;
                case TAKE_PICTURE:// 调用相机拍照
                    File temp = new File(Environment.getExternalStorageDirectory() + "/" + "temp_image.jpg");
                    startPhotoZoom(Uri.fromFile(temp));
                    break;
                case CROP_SMALL_PICTURE:// 取得裁剪后的图片
                    if (data != null) {
                        setPicToView(data);
                    }
                    break;
            }
        }
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");

            File file = UtilFile.savePicture(photo, "haed", "111", true);
            UrlAPI.getPostFormBuilder(this,"orders/buy.json")
                    .addParams("id",123+"")
                    .addFile("car[image]","haed.png",file)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String[] response, int id) {
                            String str = response[0];
                            PrintLog.d("str===112====" + str);
                        }
                    });
//                @Override
//                public void onError(Call call, Exception e, int id) {
//                    PrintLog.e("上传头像onError:" + e);
//                    Toast.makeText(getContext(), "上传头像失败", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onResponse(String response, int id) {
//                    PrintLog.e("上传头像onResponse:" + response);
//                    Type type = new TypeToken<Response2>() {
//                    }.getType();
//                    Gson gson = new Gson();
//                    Response2 response2 = gson.fromJson(response, type);
//                    if ("200".equals(response2.getErrCode())) {
//                        Toast.makeText(getContext(), "上传头像成功", Toast.LENGTH_SHORT).show();
//                        ivHead.setImageURI(response2.getData());
//                    } else {
//                        Toast.makeText(getContext(), response2.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }
    }

}
