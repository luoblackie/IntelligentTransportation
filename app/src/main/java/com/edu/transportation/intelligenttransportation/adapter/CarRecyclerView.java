package com.edu.transportation.intelligenttransportation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edu.transportation.intelligenttransportation.R;

import java.util.List;

public class CarRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CardInfo> cardInfos;
    private Context context;
    //声明方法
    public OnItemClickListener onItemClickListener;

    //开放方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CarRecyclerView(List<CardInfo> list) {
        this.cardInfos = list;
    }

    //写ViewHolder类
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public ViewHolder(final View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.item_car_tv);
            iv = itemView.findViewById(R.id.item_card_img);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int i) {
        CardInfo ci = cardInfos.get(i);
        ((ViewHolder) holder).tv.setText(ci.getTitle());
        if (((ViewHolder) holder).iv.getDrawable() != null) {
            //如果图片存在就啥也不干
        } else {
            //用Glide压缩添加图片
            Glide.with(context).load(ci.getImgId()).into(((ViewHolder) holder).iv);
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onItemLongClick(holder.itemView,holder.getLayoutPosition());
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardInfos.size();
    }


    //自定义监听回调
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
}
