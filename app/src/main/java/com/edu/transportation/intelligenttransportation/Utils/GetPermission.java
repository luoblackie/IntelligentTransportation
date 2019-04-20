package com.edu.transportation.intelligenttransportation.Utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.edu.transportation.intelligenttransportation.BaseClass.Permission;

import java.util.ArrayList;
import java.util.List;

public class GetPermission {
    private AppCompatActivity context;

    /*要申请的权限*/
    private List<Permission> permissionList;

    /*请求的返回码*/
    private int mRequstCode = 100;

    private Dialog dialog;

    /*还未申请完的权限*/
    private List<Permission> yetPermissionList;

    public GetPermission(AppCompatActivity context, List<Permission> permissionList) {
        this.context = context;
        this.permissionList = permissionList;
    }

    public void checkPermission(){
        yetPermissionList = new ArrayList<>();

        for (int i = 0; i < permissionList.size(); i++) {
            if (ContextCompat.checkSelfPermission(context, permissionList.get(i).getPermission())
                    != PackageManager.PERMISSION_GRANTED){
                yetPermissionList.add(permissionList.get(i));
            }
        }

        if (yetPermissionList.size() > 0){
            showRequstPermissonDialog(yetPermissionList.get(0));
        }
    }

    private void showRequstPermissonDialog(final Permission permission) {
        dialog = new AlertDialog.Builder(context)
                .setTitle(permission.getTitle())
                .setMessage(permission.getReason())
                .setPositiveButton("立刻开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.requestPermissions(new String[]{permission.getPermission()}, mRequstCode);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if (requestCode == mRequstCode){
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
                //判断是否点击了不再提醒（检测该权限是否还可被申请
                if (!context.shouldShowRequestPermissionRationale(permissions[0])){
                    // 提示用户去应用设置界面手动开启权限
                    showTipToAppSettingDialog(yetPermissionList.get(0));
                }
            }else {
                checkPermission();
            }
        }
    }

    private void showTipToAppSettingDialog(Permission permission) {
        dialog = new AlertDialog.Builder(context)
                .setTitle(permission.getTitle())
                .setMessage("请在-应用程序-权限-中，允许智慧交通获取存储权限，" + permission.getReason())
                .setPositiveButton("立刻开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gotoAppSetting();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void gotoAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivityForResult(intent, mRequstCode);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode == mRequstCode){
            if (resultCode != PackageManager.PERMISSION_GRANTED){
                showTipToAppSettingDialog(yetPermissionList.get(0));
            }else {
                if (dialog != null && dialog.isShowing()){
                    dialog.dismiss();
                }
                checkPermission();
                Toast.makeText(context, "权限开启成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
