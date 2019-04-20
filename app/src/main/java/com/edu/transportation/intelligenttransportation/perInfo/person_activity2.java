package com.edu.transportation.intelligenttransportation.perInfo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.Utils.SetTitle;

public class person_activity2 extends AppCompatActivity {

    private ImageView image_back;
    private TextView title;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_activity2);
        toolBar = (Toolbar) findViewById(R.id.activityToolBar);
        setSupportActionBar(toolBar);
        initView();
        setTitle();
        //初始化顶部标题
        initToolBar();
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
    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.ac01_query);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
