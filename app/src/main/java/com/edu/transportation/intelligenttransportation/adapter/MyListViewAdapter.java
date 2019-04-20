package com.edu.transportation.intelligenttransportation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.object.ListView_Object;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {

    private final Context context;
    List<ListView_Object> list;

    public MyListViewAdapter(List<ListView_Object> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.lisview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.img.setImageResource(list.get(position).getImg_src());
        viewHolder.direct.setText(list.get(position).getText());
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView img;
        public TextView direct;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.img = (ImageView) rootView.findViewById(R.id.img);
            this.direct = (TextView) rootView.findViewById(R.id.direct);
        }

    }
}
