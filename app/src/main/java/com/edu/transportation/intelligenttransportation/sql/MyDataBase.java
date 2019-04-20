package com.edu.transportation.intelligenttransportation.sql;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MyDataBase {
    private final Context context;
    Dao dao;

    public MyDataBase(Context context) {
        this.context = context;
        MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(context);
        try {

            dao = myDataBaseHelper.getDao(Person_info_Object.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person_info_Object> getDao() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDao(Person_info_Object a) {
        try {
            dao.createOrUpdate(a);
            Toast.makeText(context,"创建成功",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
