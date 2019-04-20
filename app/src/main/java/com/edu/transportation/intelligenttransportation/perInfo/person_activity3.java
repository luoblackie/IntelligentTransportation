package com.edu.transportation.intelligenttransportation.perInfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.Utils.SetTitle;

public class person_activity3 extends AppCompatActivity {

    private ImageView image_back;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_activity3);
        initView();
        setTitle();
    }

    public void setTitle(){
        new SetTitle(image_back,title,getIntent()) {
            @Override
            public void a() {
                finish();
            }
        };
    }

    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        title = (TextView) findViewById(R.id.title);
    }
}
