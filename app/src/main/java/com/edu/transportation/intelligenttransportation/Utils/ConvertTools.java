package com.edu.transportation.intelligenttransportation.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.GZIPInputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConvertTools {
    public final static String LOGD_TAG = "luo_blaice";
    public final static int S = 1;
    public final static int F = -1;
    public final static int BUFFER = 10240;
    private Context context;

    public ConvertTools(Context context) {
        this.context = context;
    }

    public interface GetData{
        void toDo(JSONObject jsonObject);
    }

    /*根据子控件高度改变listView的布局高度*/
    public static void setListViewHeighBaseOnChild(ListView listView){
        ListAdapter adapter = listView.getAdapter();

        int height = 0;

        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            height += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = height;
        listView.setLayoutParams(layoutParams);
    }

    public interface GetBitmap{
        void toDo(Bitmap bitmap);
    }

    public void getBitmapFromWeb(String url, final GetBitmap getBitmap){
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == S){
                    getBitmap.toDo((Bitmap) msg.obj);
                }
            }
        };

        new OkHttpClient().newCall(new Request.Builder()
                .url(url)
                .get()
                .build()
        ).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                Message message = Message.obtain();

                message.what = S;
                message.obj = bitmap;

                handler.sendMessage(message);
            }
        });
    }

    public void getDataFromWeb(String url, String req, final GetData getData){
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == S){
                    getData.toDo((JSONObject) msg.obj);
                }
            }
        };
        new OkHttpClient().newCall(new Request.Builder()
                .post(RequestBody.create(MediaType.parse("application/json;utf-8"), req))
                .url(url)
                .build()
        ).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                message.what = F;
                try {
                    message.obj = new JSONObject(
                            response.body().string()
                    );
                    message.what = S;
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(ConvertTools.LOGD_TAG, "onResponse: " + e);
                }
                handler.sendMessage(message);
            }
        });
    }

    public void getDataFromWeb(String url, final GetData getData){
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == S){
                    getData.toDo((JSONObject) msg.obj);
                }
            }
        };
        new OkHttpClient().newCall(new Request.Builder()
                .get()
                .url(url)
                .build()
        ).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                message.what = F;
                try {
                    message.obj = new JSONObject(
                            response.body().string()
                    );
                    message.what = S;
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(ConvertTools.LOGD_TAG, "onResponse: " + e);
                }
                handler.sendMessage(message);
            }
        });
    }

    public String jsonFromAssets(String fileName) throws IOException {
        InputStreamReader reader = new InputStreamReader(context.getAssets().open(fileName), "utf-8");
        BufferedReader br = new BufferedReader(reader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = br.readLine()) != null){
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    /*gzip编码转utf-8*/
    /*public String converFromGzip(String string) throws Exception{
        ByteArrayInputStream in = new ByteArrayInputStream(string.getBytes("ISO-8859-1"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        *//*解压缩*//*
        GZIPInputStream gin = new GZIPInputStream(in);

        int counter;

        byte data[] = new byte[BUFFER];
        while ((counter = gin.read(data, 0, BUFFER)) != -1){
            out.write(data, 0, counter);
        }

        gin.close();

        string = out.toString();

        out.flush();
        out.close();

        in.close();

        return string;

    }*/

    public void savePre(String name, String data){
        SharedPreferences sharedPreferences = context.getSharedPreferences("faiohiu", 0);
        sharedPreferences.edit().putString(name, data).apply();
    }

    public String loadPre(String name, String def){
        SharedPreferences sharedPreferences = context.getSharedPreferences("faiohiu", 0);
        return sharedPreferences.getString(name, def);
    }

    public String loadPre(String name){
        SharedPreferences sharedPreferences = context.getSharedPreferences("faiohiu", 0);
        return sharedPreferences.getString(name, "");
    }

    public static void getPermission(Activity context, String[] permissionArr, String title, String reason){
        GetPermissionNoFinish getPermissionNoFinish = new GetPermissionNoFinish(context, permissionArr, title, reason);
        getPermissionNoFinish.checkPermission();
    }

    public static int dp2px(Context context, int dp){
        return (int) (context.getResources().getDisplayMetrics().density * dp + .5f);
    }

    public int dp2px(int dp){
        return (int) (context.getResources().getDisplayMetrics().density * dp + .5f);
    }

}
