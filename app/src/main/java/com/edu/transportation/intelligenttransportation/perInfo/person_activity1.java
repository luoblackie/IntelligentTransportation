package com.edu.transportation.intelligenttransportation.perInfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.Utils.SetTitle;
import com.edu.transportation.intelligenttransportation.Utils.SettingSharesPreferences;
import com.edu.transportation.intelligenttransportation.sql.MyDataBase;
import com.edu.transportation.intelligenttransportation.sql.Person_info_Object;

import java.util.List;

public class person_activity1 extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_back;
    private TextView title;
    private EditText userName;
    private EditText userPwd;
    private EditText selfDirect;
    private Button save;
    private Context context;
    private List<Person_info_Object> person_info;
    private Person_info_Object person_info_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_activity1);
        context = getBaseContext();

        initToolBar();

        initView();
        setTitle();
        getData();
    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.ac01_query);
        }
    }

    public void getData(){
        person_info = new MyDataBase(context).getDao();
        for (Person_info_Object p :
                person_info) {
            if (p.getUserName().equals(new SettingSharesPreferences(context).loadData("name",""))){
                userName.setText(p.getUserName());
                userPwd.setText(p.getUserPwd());
                selfDirect.setText(p.getSelf_description());
            }
        }
    }

    public void setTitle() {
        new SetTitle(image_back, title, getIntent()) {
            @Override
            public void a() {
                finish();
            }
        };
    }

    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        title = (TextView) findViewById(R.id.title);
        userName = (EditText) findViewById(R.id.userName);
        userName.setOnClickListener(this);
        userPwd = (EditText) findViewById(R.id.userPwd);
        userPwd.setOnClickListener(this);
        selfDirect = (EditText) findViewById(R.id.selfDirect);
        selfDirect.setOnClickListener(this);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                person_info_object = new Person_info_Object();
                person_info_object.setUserName(String.valueOf(userName.getText()));
                person_info_object.setUserPwd(String.valueOf(userPwd.getText()));
                person_info_object.setSelf_description(String.valueOf(selfDirect.getText()));
                new MyDataBase(context).setDao(person_info_object);
                Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }


}
