package com.edu.transportation.intelligenttransportation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.transportation.intelligenttransportation.sql.MyDataBase;
import com.edu.transportation.intelligenttransportation.sql.Person_info_Object;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    private static int[] w = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};

    private ImageView image_back;
    private TextView title;
    private EditText userName;
    private EditText userPwd;
    private EditText userPwd_again;
    private EditText userPhone;
    private Button zc_button;
    private EditText userPersonId;
    private RadioGroup sex;
    private RadioButton sex_value;
    private Context context;
    private Person_info_Object person_info_object;
    private MyDataBase myDataBase;
    private boolean flag;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        context = getBaseContext();
        myDataBase = new MyDataBase(context);

        initToolBar();

        initView();
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.activityToolBar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.ac01_query);
            actionBar.setTitle("注册界面");

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

    private void initView() {
//        image_back = (ImageView) findViewById(R.id.image_back);
//        title = (TextView) findViewById(R.id.title);
        userName = (EditText) findViewById(R.id.userName);
        userPwd = (EditText) findViewById(R.id.userPwd);
        userPwd_again = (EditText) findViewById(R.id.userPwd_again);
        userPhone = (EditText) findViewById(R.id.userPhone);
        zc_button = (Button) findViewById(R.id.zc_button);
        userPersonId = (EditText) findViewById(R.id.userPersonId);
        sex = (RadioGroup) findViewById(R.id.sex);


        zc_button.setOnClickListener(this);
//        image_back.setOnClickListener(this);
    }

    public void intoData() {
        sex_value =  (RadioButton) findViewById(sex.getCheckedRadioButtonId());
        person_info_object = new Person_info_Object();
        verify();



    }

    public void setTip(String Tip){
        Toast.makeText(context,Tip,Toast.LENGTH_SHORT).show();
    }

    public void verify(){
        if ("".equals(String.valueOf(userName.getText()))){
            setTip("用户名不能为空");
        }else{
            person_info_object.setUserName(String.valueOf(userName.getText()));
            if ("".equals(String.valueOf(userPwd.getText()))){
                setTip("密码不能为空");
            }else {
                flag = true;
                if (myDataBase.getDao().size()  == 0){
                    if (String.valueOf(userPwd_again.getText()) == (String.valueOf(userPwd.getText())) && flag){
                        setTip("两次密码不同");
                    }else {
                        person_info_object.setUserPwd(String.valueOf(userPwd.getText()));
                        if ("".equals(String.valueOf(userPersonId.getText()))){
                            setTip("身份证号不能为空");
                        }else {
                            if (!isCard(String.valueOf(userPersonId.getText()))){
                                setTip("身份证不符合要求");
                            }else {
                                person_info_object.setPerson_Id(String.valueOf(userPersonId.getText()));
                                if (!isPhone(String.valueOf(userPhone.getText()))){

                                }else {
                                    person_info_object.setPhone(String.valueOf(userPhone.getText()));
                                    if ("".equals(String.valueOf(sex_value.getText()))){
                                        setTip("请选择性别");
                                    }else {
                                        person_info_object.setSex(String.valueOf(sex_value.getText()));
                                        myDataBase.setDao(person_info_object);
                                        setTip("注册成功");
                                        finish();
                                    }
                                }
                            }
                        }
                    }
                }else {
                    for (Person_info_Object p :
                            myDataBase.getDao()) {
                        if (String.valueOf(p.getUserName()).equals(String.valueOf(userName.getText()))) {
                            flag = false;
                            setTip("用户名已存在");
                            break;
                        }else {
                            if (String.valueOf(userPwd_again.getText()) == (String.valueOf(userPwd.getText())) && flag){
                                setTip("两次密码不同");
                            }else {
                                person_info_object.setUserPwd(String.valueOf(userPwd.getText()));
                                if ("".equals(String.valueOf(userPersonId.getText()))){
                                    setTip("身份证号不能为空");
                                }else {
                                    if (!isCard(String.valueOf(userPersonId.getText()))){
                                        setTip("身份证不符合要求");
                                    }else {
                                        person_info_object.setPerson_Id(String.valueOf(userPersonId.getText()));
                                        if (!isPhone(String.valueOf(userPhone.getText()))){

                                        }else {
                                            person_info_object.setPhone(String.valueOf(userPhone.getText()));
                                            if ("".equals(String.valueOf(sex_value.getText()))){
                                                setTip("请选择性别");
                                            }else {
                                                person_info_object.setSex(String.valueOf(sex_value.getText()));
                                                myDataBase.setDao(person_info_object);
                                                setTip("注册成功");
                                                finish();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    public boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            setTip("手机号应为11位数");
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                setTip("请填入正确的手机号");
            }
            return isMatch;
        }
    }

    public boolean isCard(String id)
    {
        if (id.length() != 18) {
        setTip("身份证号长度不够");
        return false;
        }else {
            char[] c=id.toCharArray();
            int sum=0;
            for (int i = 0; i < w.length; i++) {
                sum+=(c[i]-'0')*w[i];
            }
            System.out.println(sum);
            char[] verifyCode="10X98765432".toCharArray();
            char ch =verifyCode[sum%11];
            System.out.println(ch);
            return c[17]==ch;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zc_button:
                intoData();
                break;
            case R.id.image_back:
                finish();
                break;
        }
    }

}
