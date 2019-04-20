package com.edu.transportation.intelligenttransportation.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.classChaXun.wzInfo;
import com.edu.transportation.intelligenttransportation.dialog.LoadingDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity01_01 extends AppCompatActivity {
    private Toolbar toolBar;
    private com.edu.transportation.intelligenttransportation.classChaXun.wzInfo wzInfo;
    private String cphm;
    private List<String> list;
    private ListView lv;
    private List<List<String>> lists;
    private List<Map<String, String>> contentList;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity01_01);
        LoadingDialog.showDialog(Activity01_01.this);
        toolBar = (Toolbar) findViewById(R.id.activityToolBar);
        setSupportActionBar(toolBar);

        initToolBar();

        initView();

        initData();

    }

    private void initView() {
        lv = findViewById(R.id.ac01_01_lv);
        tv = findViewById(R.id.ac01_01_tv);
    }

    private void initData() {
        cphm = getIntent().getStringExtra("values");
        Gson gson = new Gson();
        try {
            wzInfo = gson.fromJson(new JSONObject(formatString("wz.json")).toString(), wzInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int wzjl = wzInfo.getWzjl().size();
        for (int i = 0; i < wzjl; i++) {
            int wzcount = wzInfo.getWzjl().get(i).getList().size();
            if (wzInfo.getWzjl().get(i).getCphm().equals(cphm) && wzcount != 0) {
                lists = new ArrayList<>();
                for (int j = 0; j < wzcount; j++) {
                    list = new ArrayList<>();
                    String wfsj = wzInfo.getWzjl().get(i).getList().get(j).getWfsj();
                    String wfdd = wzInfo.getWzjl().get(i).getList().get(j).getWfdd();
                    String wfxw = wzInfo.getWzjl().get(i).getList().get(j).getWfxw();
                    list.add(wfsj);
                    list.add(wfdd);
                    list.add(wfxw);
                    lists.add(list);
//                    if (j + 1 == wzInfo.getWzjl().get(i).getList().size()) {
//
//                    }
                }
                initListView();
            } else {
                tv.setText("暂无此车辆违章记录");
                tv.setTextSize(20f);
                LoadingDialog.disDialog();
            }
        }
    }

    private void initListView() {
        Map<String, String> map;
        contentList = new ArrayList<>();
        Log.d("***************", "initListView: " + lists.size());

        tv.setVisibility(View.GONE);
        for (int i = 0; i < lists.size(); i++) {
            map = new HashMap<>();
            for (int j = 0; j < lists.get(i).size(); j++) {
                map.put("wzsj", lists.get(i).get(0));
                map.put("wzdd", lists.get(i).get(1));
                map.put("wzxw", lists.get(i).get(2));
            }
            contentList.add(map);
        }
        String[] from = {"wzsj", "wzdd", "wzxw"};
        int[] to = {R.id.item_wz_tv01, R.id.item_wz_tv02, R.id.item_wz_tv03};
        SimpleAdapter adapter = new SimpleAdapter(getApplication(), contentList, R.layout.item_wzcard, from, to);
        lv.setAdapter(adapter);
        LoadingDialog.disDialog();
    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("查询结果");
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

    //读JSON（数据量较大）返回一个字符串
    public String formatString(String fileName) throws Exception {
        InputStreamReader in = new InputStreamReader(getAssets().open(fileName), "utf-8");
        BufferedReader reader = new BufferedReader(in);

        String line;
        StringBuilder builder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}
