package com.edu.transportation.intelligenttransportation.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.transportation.intelligenttransportation.R;

import java.util.ArrayList;
import java.util.List;

import com.edu.transportation.intelligenttransportation.zhuYeClass.CityLib;
import com.edu.transportation.intelligenttransportation.zhuYeClass.CityUnit;

public class CitySearchDialog extends DialogFragment {
    private View view;
    private ImageView textCancleBT;
    private EditText searchEdit;
    private Button cancleBT;
    private ListView lv;
    private CityLib cityLib;
    private List<CityUnit> cityUnitList;
    private OnDialogDismiss onDialogDismiss;
    private String cityId = null;
    private LinearLayout locationBT;
    private OnLocationClickListener onLocationClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater
                .inflate(R.layout.city_search_dialog, container, false);

        findId();

        /*初始化变量*/
        init();

        addListener();

        return view;
    }

    private void init() {
        cityLib = new CityLib(getContext());
        cityLib.init();

        cityUnitList = cityLib.getCityUnitList();
    }

    private void addListener() {
        locationBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLocationClickListener.onLocationClickListener();

                cityId = null;

                dismiss();
            }
        });

        textCancleBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEdit.setText("");

                lv.setVisibility(View.GONE);
                textCancleBT.setVisibility(View.GONE);
            }
        });

        cancleBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchStr = searchEdit.getText().toString().trim();

                textCancleBT.setVisibility(View.VISIBLE);

                lv.setVisibility(View.VISIBLE);

                if (searchStr.isEmpty()){
                    initLV(cityUnitList);
                }else {
                    /*获取搜索的数据*/
                    List<CityUnit> cityYouUnitList = new ArrayList<>();

                    for (int i = 0; i < cityUnitList.size(); i++) {
                        CityUnit cityUnit = cityUnitList.get(i);
                        if (cityUnit.getCityZh().startsWith(searchStr)){
                            cityYouUnitList.add(cityUnit);
                        }
                    }

                    initLV(cityYouUnitList);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initLV(final List<CityUnit> cityList) {
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return cityList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(getContext());
                final CityUnit cityUnit = cityList.get(position);

                textView.setText(cityUnit.getProvinceZh() + " " + cityUnit.getLeaderZh() + " " + cityUnit.getCityZh());
                textView.setTextSize(2, 25);
                textView.setPadding(
                        20,
                        20,
                        20,
                        20
                );

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cityId = cityUnit.getId();

                        dismiss();
                    }
                });

                return textView;
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        onDialogDismiss.onDialogDismiss(cityId);
    }

    public void setOnDialogDismiss(OnDialogDismiss onDialogDismiss) {
        this.onDialogDismiss = onDialogDismiss;
    }

    public void setOnLocationClickListener(OnLocationClickListener onLocationClickListener) {
        this.onLocationClickListener = onLocationClickListener;
    }

    public interface OnDialogDismiss{
        void onDialogDismiss(String cityId);
    }

    public interface OnLocationClickListener{
        void onLocationClickListener();
    }

    private void findId() {
        textCancleBT = (ImageView) view.findViewById(R.id.citySearchDialog_editTextCancleBT);
        searchEdit = (EditText) view.findViewById(R.id.citySearchDialog_searchEdit);
        cancleBT = (Button) view.findViewById(R.id.citySearchDialog_cancleBT);
        lv = (ListView) view.findViewById(R.id.citySearchDialog_LV);
        locationBT = (LinearLayout) view.findViewById(R.id.citySearchDialog_locationBT);
    }
}
