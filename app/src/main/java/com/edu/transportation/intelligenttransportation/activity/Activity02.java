package com.edu.transportation.intelligenttransportation.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.edu.transportation.intelligenttransportation.R;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity02 extends AppCompatActivity implements AMapLocationListener {
    private Toolbar toolBar;
    private MapView mapView;
    private AMap aMap;
    public AMapLocationClient locationClient;
    public AMapLocationClientOption locationClientOption;
    private MyLocationStyle locationStyle;
    public AMapUtils mapUtils;
    private double latitude;
    private double longitude;
    private double lastLongitude = 0;
    private double lastLatitude = 0;
    private UiSettings uiSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity02);

        mapView = findViewById(R.id.ml);
        mapView.onCreate(savedInstanceState);

        toolBar = (Toolbar) findViewById(R.id.activityToolBar);
        setSupportActionBar(toolBar);

        //高德
        initCreateMap();
        location();
        //设置定位图标
        setlocationStyle();

        initToolBar();


    }

    private void initCreateMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            uiSettings = aMap.getUiSettings();
        }
        aMap.setTrafficEnabled(true);// 显示实时交通状况
//        地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
        uiSettings.setScaleControlsEnabled(true);
    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    //高德
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    //连接加连接选项
    private void location() {
        locationClient = new AMapLocationClient(getApplicationContext());
        locationClientOption = new AMapLocationClientOption();

        locationClient.setLocationListener(this);

        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClientOption.setInterval(2 * 1000);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 17));
        locationClient.setLocationOption(locationClientOption);
        locationClient.startLocation();

    }

    //设置定位蓝点
    private void setlocationStyle() {
        locationStyle = new MyLocationStyle();
//        locationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        locationStyle.interval(2 * 1000);
        aMap.setMyLocationStyle(locationStyle);
        aMap.setMyLocationEnabled(true);
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                latitude = amapLocation.getLatitude(); //获取经度
                longitude = amapLocation.getLongitude();//获取维度
                if (lastLatitude != lastLatitude || lastLongitude != lastLongitude) {
                    lastLatitude = latitude;
                    lastLongitude = longitude;
                    //设置定位图标
                    setlocationStyle();

                }
                amapLocation.getAccuracy();//获取精度信息
//                定义一个焦点
//                LatLng latLng = new  LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());
//                按比例聚焦到焦点
//                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }
}
