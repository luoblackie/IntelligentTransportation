package com.edu.transportation.intelligenttransportation;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.edu.transportation.intelligenttransportation.BaseClass.Permission;
import com.edu.transportation.intelligenttransportation.Utils.ConvertTools;
import com.edu.transportation.intelligenttransportation.Utils.GetPermission;
import com.edu.transportation.intelligenttransportation.activity.Activity01;
import com.edu.transportation.intelligenttransportation.activity.Activity02;
import com.edu.transportation.intelligenttransportation.activity.Activity03;
import com.edu.transportation.intelligenttransportation.activity.Activity04;
import com.edu.transportation.intelligenttransportation.dialog.ThanksDialog;
import com.edu.transportation.intelligenttransportation.fragment.FragmentFanKui;
import com.edu.transportation.intelligenttransportation.fragment.FragmentWo;
import com.edu.transportation.intelligenttransportation.fragment.FragmentZhuYe;
import com.zyao89.view.zloading.ZLoadingDialog;

import java.util.ArrayList;
import java.util.List;

import static com.zyao89.view.zloading.Z_TYPE.CIRCLE_CLOCK;
import static com.zyao89.view.zloading.Z_TYPE.INFECTION_BALL;

public class MainActivity extends AppCompatActivity{

    private ViewPager vp;
    private ThanksDialog thanksDialog;
    private BottomNavigationView bnv;
    private int keyBackCounter = 0;
    private Handler keyBackCounterInitHandle;
    private Runnable keyBackCounterInitRunnable;

    /*要请求的权限*/
    private List<Permission> permissionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(1024, 1024);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*浮动按钮*/
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        /*侧边栏*/
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        //找到控件
        findId();

        //初始化滑动
        initVP();

        //初始化底部导航栏
        initBottomNav();

        /*获取权限*/
        getPermission();

    }

    private void getPermission() {
        /*
        *
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" />
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />

        * */

        String[] permissions = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,

                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
        };

        ConvertTools.getPermission(MainActivity.this, permissions,
                "定位权限", "APP需要定位相关权限，拒绝可能限制部分功能，影响您的体验");
    }


    private void initVP() {
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentZhuYe());
        fragmentList.add(new FragmentFanKui());
        fragmentList.add(new FragmentWo());

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                bnv.setSelectedItemId(bnv.getMenu().getItem(position).getItemId());
            }
        });

        vp.setOffscreenPageLimit(3);
    }

    private void findId() {
        bnv = findViewById(R.id.boot_nav);
        vp = (ViewPager) findViewById(R.id.contentMain_vp);
    }

    /*监听返回键*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                keyBackCounter++;
                if (keyBackCounter == 1) {
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();

                    /*若用户两秒内未点击，counter重置*/
                    initKeyBackCounterIn2Seconds();

                } else if (keyBackCounter == 2) {
                    /*两次后应用退出*/
                    finish();
                }
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void initKeyBackCounterIn2Seconds() {
        keyBackCounterInitHandle = new Handler();
        keyBackCounterInitRunnable = new Runnable() {
            @Override
            public void run() {
                keyBackCounter = 0;
            }
        };
        keyBackCounterInitHandle.postDelayed(keyBackCounterInitRunnable, 2 * 1000);
    }


    private void initBottomNav() {
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.menu_query:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.menu_me:
                        vp.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
        } else if (id == R.id.action_thanks) {
            thanksDialog = new ThanksDialog();
            thanksDialog.show(getSupportFragmentManager(), "");
        }

        return true;
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        switch (item.getItemId()) {
//            case R.id.function1:
//                startActivity(new Intent(MainActivity.this, Activity01.class));
//                break;
//            case R.id.function2:
//                startActivity(new Intent(MainActivity.this, Activity02.class));
//                break;
//              case R.id.function3:
//                startActivity(new Intent(MainActivity.this, Activity03.class));
//                break;
//            case R.id.function4:
//                startActivity(new Intent(MainActivity.this, Activity04.class));
//                break;
//
//        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        keyBackCounterInitHandle.removeCallbacks(keyBackCounterInitRunnable);
    }

/*
    @Override
    public void onClick(View v) {

    }*/
}
