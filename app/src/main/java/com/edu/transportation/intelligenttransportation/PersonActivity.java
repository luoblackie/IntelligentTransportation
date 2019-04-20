package com.edu.transportation.intelligenttransportation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.edu.transportation.intelligenttransportation.adapter.MyListViewAdapter;
import com.edu.transportation.intelligenttransportation.object.ListView_Object;
import com.edu.transportation.intelligenttransportation.perInfo.person_activity1;
import com.edu.transportation.intelligenttransportation.perInfo.person_activity2;
import com.edu.transportation.intelligenttransportation.perInfo.person_activity3;
import com.edu.transportation.intelligenttransportation.perInfo.person_activity4;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {


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
    private View main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getBaseContext();
        initView();
        setAdapter();

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
        Intent intent = new Intent(PersonActivity.this, target);
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
        person_image = (ImageView) findViewById(R.id.person_image);
        person_text = (TextView) findViewById(R.id.person_text);
        share = (LinearLayout) findViewById(R.id.share);
        back = (LinearLayout) findViewById(R.id.back);
        info = (LinearLayout) findViewById(R.id.info);
        pwd_setting = (LinearLayout) findViewById(R.id.pwd_setting);
        listview = (ListView) findViewById(R.id.listview);
        exit = (Button) findViewById(R.id.exit);

        info.setOnClickListener(this);
        exit.setOnClickListener(this);
//        setImgCycle();
    }

//    private void setImgCycle(){
//        Glide.with(context)
//                .load(R.drawable.person)
//                .asBitmap()
//                .centerCrop()
//                .into(new BitmapImageViewTarget(person_image){
//                    @Override
//                    protected void setResource(Bitmap resource) {
//                        super.setResource(resource);
//                        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),resource);
//                        roundedBitmapDrawable.setCircular(true);
//                        person_image.setImageDrawable(roundedBitmapDrawable);
//                    }
//                });
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                saveData("boolean","false");
                Intent intent = new Intent(this,Login_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.info:

                break;
        }
    }

    public void saveData(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("test", MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String loadData(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("test", MODE_PRIVATE);
        return sharedPreferences.getString(key, value);
    }
}
