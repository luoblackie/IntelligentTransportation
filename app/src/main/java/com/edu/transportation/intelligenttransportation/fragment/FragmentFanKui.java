package com.edu.transportation.intelligenttransportation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edu.transportation.intelligenttransportation.MainActivity;
import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.activity.Activity01;
import com.edu.transportation.intelligenttransportation.activity.Activity02;
import com.edu.transportation.intelligenttransportation.activity.Activity03;
import com.edu.transportation.intelligenttransportation.activity.Activity04;
import com.edu.transportation.intelligenttransportation.activity.Activity05;
import com.edu.transportation.intelligenttransportation.adapter.CarRecyclerView;
import com.edu.transportation.intelligenttransportation.adapter.CardInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentFanKui extends Fragment {
    private View view;
    private List<CardInfo> list = new ArrayList();
    private CardInfo[] infos = {
            new CardInfo("违章查询", R.drawable.ad_01),
            new CardInfo("道路预警", R.drawable.ad_03),
            new CardInfo("行驶记录", R.drawable.ad_02),
            new CardInfo("事故处理", R.drawable.ad_04),
            new CardInfo("其他服务", R.drawable.ad_05)
    };
    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fankui, container, false);

        //初始化控件
        initView();

        //初始化卡片里面的数据
        initCard();

        //设置Recycler适配器
        initAdapter();

        return view;
    }


    private void initAdapter() {
        final int spanCount = 1;
        //第一个参数上下文，第二个是一行显示几个card
        GridLayoutManager manager = new GridLayoutManager(getActivity(), spanCount);
        rv.setLayoutManager(manager);
        CarRecyclerView adapter = new CarRecyclerView(list);

        //调用自定义回调方法实现事件监听
        adapter.setOnItemClickListener(new CarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(), Activity01.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), Activity02.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), Activity03.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), Activity04.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), Activity05.class));
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), "再摸人家就要生气了 ٩(๑´0`๑)۶", Toast.LENGTH_SHORT).show();
            }
        });
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    private void initCard() {
        list.clear();
        for (int i = 0; i < 5; i++) {
            list.add(infos[i]);
        }
    }

    private void initView() {
        rv = view.findViewById(R.id.rv_query);
    }
}
