package com.edu.transportation.intelligenttransportation.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.transportation.intelligenttransportation.Login_Activity;
import com.edu.transportation.intelligenttransportation.PersonActivity;
import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.Utils.BitmapUtil;
import com.edu.transportation.intelligenttransportation.adapter.MyListViewAdapter;
import com.edu.transportation.intelligenttransportation.object.ListView_Object;
import com.edu.transportation.intelligenttransportation.perInfo.person_activity1;
import com.edu.transportation.intelligenttransportation.perInfo.person_activity2;
import com.edu.transportation.intelligenttransportation.perInfo.person_activity3;
import com.edu.transportation.intelligenttransportation.perInfo.person_activity4;
import com.j256.ormlite.stmt.query.In;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class FragmentWo extends Fragment implements View.OnClickListener {

    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    public static final int CROP_SMALL_PICTURE = 2;


    private View view;
    private ImageView person_image;
    private TextView person_text;
    private LinearLayout share;
    private LinearLayout back;
    private LinearLayout info;
    private LinearLayout pwd_setting;
    private ListView listview;
    private Button exit;
    private List<ListView_Object> list;
    private Context context;
    private Uri tempUri;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_person, container, false);
        context = getContext();
        initView();
        setAdapter();
        return view;
    }

    public void setAdapter() {
        list = new ArrayList<>();
        list.add(setData("我的驾驶证", R.drawable.loding_false));
        list.add(setData("代金券", R.drawable.loding_false));
        list.add(setData("我的订单", R.drawable.loding_false));
        list.add(setData("关于我们", R.drawable.loding_false));
        listview.setAdapter(new MyListViewAdapter(list, context));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    intextSend(person_activity1.class, position);
                if (position == 1)
                    intextSend(person_activity2.class, position);
                if (position == 2)
                    intextSend(person_activity3.class, position);
                if (position == 3)
                    intextSend(person_activity4.class, position);
            }
        });
    }


    public void intextSend(Class target, int position) {
        Intent intent = new Intent(getContext(), target);
        intent.putExtra("title", list.get(position).getText());
        startActivity(intent);
    }

    public ListView_Object setData(String text, int img_src) {
        ListView_Object listView_object = new ListView_Object();
        listView_object.setImg_src(img_src);
        listView_object.setText(text);
        return listView_object;
    }

    private void initView() {
        person_image = (ImageView) view.findViewById(R.id.person_image);
        person_text = (TextView) view.findViewById(R.id.person_text);
        share = (LinearLayout) view.findViewById(R.id.share);
        back = (LinearLayout) view.findViewById(R.id.back);
        info = (LinearLayout) view.findViewById(R.id.info);
        pwd_setting = (LinearLayout) view.findViewById(R.id.pwd_setting);
        listview = (ListView) view.findViewById(R.id.listview);
        exit = (Button) view.findViewById(R.id.exit);

        info.setOnClickListener(this);
        exit.setOnClickListener(this);
        person_image.setImageResource(R.drawable.person);
        person_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                saveData("boolean", "false");
                getActivity().finish();
                Intent intent = new Intent(getContext(), Login_Activity.class);
                startActivity(intent);
                break;
            case R.id.info:

                break;
            case R.id.person_image:
                showChoosePicDialog();
                break;
        }
    }

    public void showChoosePicDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("设置头像");
        String[] items = {"选择本地图片","拍照"};
        builder.setNegativeButton("取消",null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        takePicture();
                        break;

                }
            }
        });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(String.valueOf(requestCode), "onActivityResult: ");
        Log.d(String.valueOf(RESULT_OK), "onActivityResult: ");
//        if (requestCode == RESULT_OK){
            switch (requestCode){
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri);
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData());
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null){
                        setImageToView(data);
                    }
                    break;
            }
//        }
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(context, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(context, "com.lt.uploadpicdemo.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    public void startPhotoZoom(Uri uri){
        if (uri == null){
            Log.i("***********", "startPhotoZoom: ");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,2);
    }

    public void setImageToView(Intent intent){
        Bundle extras = intent.getExtras();
        if (extras != null){
            Bitmap bitmap = extras.getParcelable("data");
            bitmap = BitmapUtil.toRoundBitmap(bitmap);
            person_image.setImageBitmap(bitmap);
        }
    }

    public void saveData(String key, String value) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("test", MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String loadData(String key, String value) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("test", MODE_PRIVATE);
        return sharedPreferences.getString(key, value);
    }

}
