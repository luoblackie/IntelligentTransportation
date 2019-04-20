package com.edu.transportation.intelligenttransportation.zhuYeClass;

import android.content.Context;

import com.edu.transportation.intelligenttransportation.Utils.ConvertTools;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class CityLib {
    private JSONArray jsonArray;
    private Context context;
    private ConvertTools convertTools;
    private List<CityUnit> cityUnitList = new ArrayList<>();
    private Gson gson = new Gson();

    public CityLib(Context context){
        this.context = context;
        convertTools  = new ConvertTools(context);
    }

    public void init(){
        try {
//            Log.d(ConvertTools.LOGD_TAG, "init: " + convertTools.jsonFromAssets("city.json"));
            jsonArray = new JSONArray(convertTools.jsonFromAssets("city.json"));

//            Log.d(ConvertTools.LOGD_TAG, "init: jsonArray" + jsonArray.toString());

//            Log.d(ConvertTools.LOGD_TAG, "init: jsonArrayLenth" + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {
                cityUnitList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), CityUnit.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CityUnit> getCityUnitList() {
        return cityUnitList;
    }
}
