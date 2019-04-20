package com.edu.transportation.intelligenttransportation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.dialog.MyDialog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Activity01 extends AppCompatActivity {
    private Toolbar toolBar;
    private ImageView iv02;
    private ImageView iv01;
    private Button btn;
    String url = "";
    String req = "";
    private Spinner spinner;
    private String[] sf = {"京", "沪", "苏", "皖", "浙", "其他城市会陆续开放"};
    private EditText et3;
    private EditText et2;
    private EditText et1;
    String REG1 = "^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Za-z](([0-9]{5}[DFdf])|([DF]([A-HJ-NP-Za-hj-np-z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Za-z][A-HJ-NP-Za-hj-np-z0-9]{4}[A-HJ-NP-Za-hj-np-z0-9挂学警港澳使领]))$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity01);

        //初始化控件
        initView();

        //初始化顶部标题
        initToolBar();

        initData();

        //添加监听事件
        initListener();

    }

    private void initData() {
        List<String> abbrList = new ArrayList<>();
        for (int i = 0; i < sf.length; i++) {
            abbrList.add(sf[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity01.this, android.R.layout.simple_spinner_dropdown_item, abbrList);
        spinner.setAdapter(adapter);
    }

    private void initListener() {
        iv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.setImg(R.drawable.help1);
                dialog.show(getSupportFragmentManager(), "");
            }
        });
        iv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.setImg(R.drawable.help2);
                dialog.show(getSupportFragmentManager(), "");
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItemPosition() == 5) {
                    Toast.makeText(Activity01.this, "请选择正确的省份", Toast.LENGTH_SHORT).show();
                } else {
                    String sEt1 = sf[spinner.getSelectedItemPosition()] + et1.getText().toString();
                    if (sEt1.matches(REG1)) {
                        if (et2.getText().toString().equals("123456") && et3.getText().toString().equals("123456")) {
                            Intent intent = new Intent(Activity01.this, Activity01_01.class);
                            intent.putExtra("values", sEt1);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Activity01.this, "车架号或发动机号错误已自动更正", Toast.LENGTH_SHORT).show();
                            et2.setText("123456");
                            et3.setText("123456");
                        }

                    } else {
                        Toast.makeText(Activity01.this, "车牌号格式错误已自动的更正", Toast.LENGTH_SHORT).show();
                        et1.setText("A12345");
                    }
                }

            }
        });

    }

    private void initView() {
        toolBar = findViewById(R.id.activityToolBar);
        setSupportActionBar(toolBar);
        iv01 = findViewById(R.id.ac01_iv01);
        iv02 = findViewById(R.id.ac01_iv02);
        btn = findViewById(R.id.ac01_btn);
        et1 = findViewById(R.id.ac01_et01);
        et2 = findViewById(R.id.ac01_et02);
        et3 = findViewById(R.id.ac01_et03);
        spinner = findViewById(R.id.ac01_sp02);

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
