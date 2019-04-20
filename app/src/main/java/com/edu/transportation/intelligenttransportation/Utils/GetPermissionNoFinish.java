package com.edu.transportation.intelligenttransportation.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.edu.transportation.intelligenttransportation.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

/*批量获取权限，一次性*/
public class GetPermissionNoFinish {
    /*要传入的activity*/
    private Activity context;
    /*要申请的权限*/
    private String[] permissionArr;
    /*弹出窗口的标题*/
    private String title;
    /*申请理由*/
    private String reason;

    /*还未申请的权限*/
    private List<String> yetPermission = new ArrayList<>();

    /*请求的返回码*/
    private int mRequstCode = 100;

    public GetPermissionNoFinish(Activity context, String[] permissionArr, String title, String reason) {
        this.context = context;
        this.permissionArr = permissionArr;
        this.title = title;
        this.reason = reason;
    }

    public void checkPermission(){
        if (yetPermission.size() != 0){
            yetPermission.clear();
        }

        for (int i = 0; i < permissionArr.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissionArr[i]) != PackageManager.PERMISSION_GRANTED){
                yetPermission.add(permissionArr[i]);
            }
        }

        if (yetPermission.size() > 0){
            showRequsetPermissionReasonDialog();
        }
    }

    private void showRequsetPermissionReasonDialog() {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(reason)
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] yet = new String[yetPermission.size()];
                        for (int i = 0; i < yetPermission.size(); i++) {
                            yet[i] = yetPermission.get(i);
                        }
                        context.requestPermissions(yet, mRequstCode);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

}
