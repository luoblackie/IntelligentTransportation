package com.edu.transportation.intelligenttransportation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.transportation.intelligenttransportation.sql.MyDataBase;
import com.edu.transportation.intelligenttransportation.sql.Person_info_Object;


import java.util.List;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName;
    private EditText pwd;
    private Button login_button;
    private Context context;
    private List<Person_info_Object> list;
    private Button register_button;
    private MyDataBase myDataBase;
    private boolean flag;
    private CheckBox save_info;
    private CheckBox auto_login;

    /*toolBar*/
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getBaseContext();
        initView();

        /*设置toolBar*/
        initToolBar();

        setAuto_login();
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setAuto_login(){
        if (loadData("boolean","false").equals("true")){
            login_database(loadData("name","1"),loadData("pwd","1"));
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.activityToolBar);

        userName = (EditText) findViewById(R.id.userName);
        pwd = (EditText) findViewById(R.id.pwd);
        login_button = (Button) findViewById(R.id.login_button);
        register_button = (Button) findViewById(R.id.register_button);
        auto_login = (CheckBox) findViewById(R.id.auto_login);
        save_info = (CheckBox) findViewById(R.id.save_info);

        login_button.setOnClickListener(this);
        register_button.setOnClickListener(this);
        setListen();
    }

    public void setListen(){
//        save_info.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    saveData("name", String.valueOf(userName.getText()));
//                    saveData("pwd",String.valueOf(pwd.getText()));
//                }else {
//                    saveData("name","");
//                    saveData("pwd","");
//                }
//            }
//        });
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    save_info.setChecked(true);
                    saveData("boolean", String.valueOf(isChecked));
                }else {
                    save_info.setChecked(false);
                    saveData("boolean",String.valueOf(isChecked));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                if (save_info.isChecked()){
                    saveData("name", String.valueOf(userName.getText()));
                    saveData("pwd",String.valueOf(pwd.getText()));
                }else {
                    saveData("name","");
                    saveData("pwd","");
                }
                login_database(String.valueOf(userName.getText()),String.valueOf(pwd.getText()));
                Log.d(loadData("name","1"), "onClick: name");
                Log.d(loadData("pwd","1"), "onClick: pwd");
                break;
            case R.id.register_button:
                Intent intent = new Intent(this, RegActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void login_database(String name,String pwd){
        list = new MyDataBase(context).getDao();
        for (Person_info_Object person :
                list) {
            if (person.getUserName().equals(name) && person.getUserPwd().equals(pwd)) {
                flag = true;
            } else {
                flag = false;
            }
        }
        if (flag) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_SHORT).show();
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
