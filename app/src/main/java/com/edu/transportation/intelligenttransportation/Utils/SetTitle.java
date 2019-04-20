package com.edu.transportation.intelligenttransportation.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class SetTitle {
    public SetTitle(ImageView image_back, TextView title, Intent intent) {
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a();
            }
        });
        title.setText(intent.getStringExtra("title"));
    }
    public abstract void a();
}
