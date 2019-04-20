package com.edu.transportation.intelligenttransportation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.edu.transportation.intelligenttransportation.MainActivity;
import com.edu.transportation.intelligenttransportation.R;
import com.zyao89.view.zloading.ZLoadingDialog;

import static com.zyao89.view.zloading.Z_TYPE.CIRCLE_CLOCK;

public class LoadingDialog {
    public static ZLoadingDialog dialog;

    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showDialog(Context context){
        //加载动画
        dialog = new ZLoadingDialog(context);
        dialog.setLoadingBuilder(CIRCLE_CLOCK)//设置类型
                .setCanceledOnTouchOutside(false)
                .setLoadingColor(Color.GRAY)//颜色
                .setHintText("loading...")

                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.GRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CC111111")) // 设置背景色，默认白色
                .show();
    }

    public static void disDialog(){
        if (dialog != null){
            dialog.dismiss();
        }
    }
}
