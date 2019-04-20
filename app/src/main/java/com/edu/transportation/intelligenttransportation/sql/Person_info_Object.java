package com.edu.transportation.intelligenttransportation.sql;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "person_info")
public class Person_info_Object {
    @DatabaseField(id = true)
    String ID;
    @DatabaseField
    String UserName;
    @DatabaseField
    String UserPwd;
    @DatabaseField
    String phone;
    @DatabaseField
    String Person_Id;
    @DatabaseField
    String sex;
    @DatabaseField
    String self_description;

    public String getID() {
        return ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
        this.ID = UserName;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String userPwd) {
        UserPwd = userPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPerson_Id() {
        return Person_Id;
    }

    public void setPerson_Id(String person_Id) {
        Person_Id = person_Id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSelf_description() {
        return self_description;
    }

    public void setSelf_description(String self_description) {
        this.self_description = self_description;
    }
}
