package com.edu.transportation.intelligenttransportation.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.Utils.ConvertTools;
import com.edu.transportation.intelligenttransportation.activity.ActivityNewsDetail;
import com.edu.transportation.intelligenttransportation.dialog.CitySearchDialog;
import com.edu.transportation.intelligenttransportation.dialog.LoadingDialog;
import com.edu.transportation.intelligenttransportation.zhuYeClass.NewsUnit;
import com.google.gson.Gson;

import org.json.JSONObject;

import com.edu.transportation.intelligenttransportation.zhuYeClass.City;

import java.util.ArrayList;
import java.util.List;

public class FragmentZhuYe extends Fragment {
    public static final String NEWS_ID = "newsId";
    private View view;
    private ConvertTools convertTools;
    private Gson gson = new Gson();
    private City city;
    private TextView temTV;
    private TextView weaTV;
    private TextView winSpeedTV;
    private ImageView refreshBT;
    private TextView cityTV;
    private ImageView weaImgIV;
    private TextView winTV;
    private Button switchBT;
    private CitySearchDialog citySearchDialog = new CitySearchDialog();
    private String cityId01 = null;
    private GridView gvNews;
    private ListView lvNews;

    /*新闻导航栏按钮*/
    private List<Button> buttonList;
    private List<NewsUnit.DataBean.ListBean> newsList;
    private OnGVItemClickListener onGVItemClickListener;

    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zhuye, container, false);

        convertTools = new ConvertTools(getContext());

        findId();

        initData();

        /*利用回调接口添加代码*/
        initOnGVItemClickListener();

        initGV();

        addListener();

        /*设置默认值*/
        setDef();

        return view;
    }

    private void initOnGVItemClickListener() {
        onGVItemClickListener = new OnGVItemClickListener() {
            @Override
            public void onGVItemClickListener(Button button, int newsId) {
                LoadingDialog.showDialog(getContext());

                initLVNews(newsId);
            }
        };
    }

    private void setDef() {
        buttonList.get(6).setBackgroundResource(R.drawable.bottom_line_bg);

        initLVNews(9);
    }

    private void initLVNews(int cid) {

        /*获取news数据*/
        convertTools.getDataFromWeb("http://api.dagoogle.cn/news/nlist?cid=" + cid, new ConvertTools.GetData() {
            @Override
            public void toDo(JSONObject jsonObject) {
                LoadingDialog.disDialog();

                NewsUnit newsUnit = gson.fromJson(jsonObject.toString(), NewsUnit.class);

                newsList = newsUnit.getData().getList();

                if (newsList.size() > 0) {
                    lvNews.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return newsList.size();
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
                        public View getView(int position, View convertView, ViewGroup parent) {
                            if (convertView == null){
                                convertView = LayoutInflater.from(getContext())
                                        .inflate(R.layout.fragment_zhuye_news_lv_unit, null);

                                final ImageView imageView = (ImageView) convertView.findViewById(R.id.fragment_zhuye_news_lv_unit_IV);
                                TextView textView = (TextView) convertView.findViewById(R.id.fragment_zhuye_news_lv_unit_TV);
                                TextView source = (TextView) convertView.findViewById(R.id.fragment_zhuye_news_lv_unit_Source);
                                LinearLayout itemLinear = (LinearLayout) convertView.findViewById(R.id.fragment_zhuye_news_lv_unit_ItemLinear);

                                final NewsUnit.DataBean.ListBean bean = newsList.get(position);

                                convertTools.getBitmapFromWeb(bean.getHeadpic(), new ConvertTools.GetBitmap() {
                                    @Override
                                    public void toDo(Bitmap bitmap) {
                                        imageView.setImageBitmap(bitmap);
                                    }
                                });

                                textView.setText(bean.getTitle());
                                source.setText("来源：" + bean.getSource());

                                itemLinear.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(), ActivityNewsDetail.class);
                                        intent.putExtra(NEWS_ID, bean.getAid());
                                        startActivity(intent);
                                    }
                                });
                            }

                            return convertView;
                        }
                    });

                    ConvertTools.setListViewHeighBaseOnChild(lvNews);
                } else {
                    lvNews.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return 1;
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
                        public View getView(int position, View convertView, ViewGroup parent) {
                            TextView textView = new TextView(getContext());

                            textView.setText("暂无相关新闻ㄟ( ▔, ▔ )ㄏ ");
                            textView.setTextSize(2, 30);

                            return textView;
                        }
                    });
                }

            }
        });
    }

    private void initGV() {
        /*1 =>  娱乐
         *        2 =>  军事
         *        3 =>  汽车
         *        4 =>  财经
         *        5 =>  笑话
         *        6 =>  体育
         *        7 =>  科技
         *        8 =>  感情
         *       9 =>  头条*/
        String[] title = {
                "娱乐",
                "军事",
                "汽车",
                "财经",
                "体育",
                "科技",
                "头条"
        };

        final int[] titleId = {
                1,
                2,
                3,
                4,
                6,
                7,
                9
        };

        /*
            <Button
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="10dp"
                android:layout_marginRight="10dp"
                android:background="@color/colorPrimary"
                android:stateListAnimator="@null"
                android:textSize="25sp" />
*/

        buttonList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Button button = new Button(getContext());

            button.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            button.setStateListAnimator(null);
            button.setTextSize(2, 20);
            button.setText(title[i]);
            button.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

            buttonList.add(button);
        }

        gvNews.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return buttonList.size();
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
                buttonList.get(position).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < buttonList.size(); i++) {
                            buttonList.get(i).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                        }

                        onGVItemClickListener.onGVItemClickListener(buttonList.get(position), titleId[position]);

                        buttonList.get(position).setBackgroundResource(R.drawable.bottom_line_bg);
                    }
                });

                return buttonList.get(position);
            }
        });
    }

    private interface OnGVItemClickListener {
        void onGVItemClickListener(Button button, int newsId);
    }

    private void addListener() {
        citySearchDialog.setOnLocationClickListener(new CitySearchDialog.OnLocationClickListener() {
            @Override
            public void onLocationClickListener() {
                cityId01 = null;
                initData();
            }
        });

        refreshBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();

                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });

        switchBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                citySearchDialog.show(getChildFragmentManager(), "");
            }
        });

        citySearchDialog.setOnDialogDismiss(new CitySearchDialog.OnDialogDismiss() {
            @Override
            public void onDialogDismiss(String cityId) {
                if (cityId != null) {
                    cityId01 = cityId;

                    initData();
                }
            }
        });
    }

    private void initData() {

        LoadingDialog.showDialog(getContext());

        String url;
        if (cityId01 == null) {
            url = "https://www.tianqiapi.com/api/?version=v1";
        } else {
            url = "https://www.tianqiapi.com/api/?version=v1&cityid=" + cityId01;
        }
        convertTools.getDataFromWeb(url, new ConvertTools.GetData() {
            @Override
            public void toDo(JSONObject jsonObject) {
                city = gson.fromJson(jsonObject.toString(), City.class);

                /*今天的天气状况*/
                City.DataBean dataBean = city.getData().get(0);
                temTV.setText(dataBean.getTem().replace("℃", ""));
                weaTV.setText(dataBean.getWea());
                winTV.setText(dataBean.getWin().get(0));
                winSpeedTV.setText(dataBean.getWin_speed());
                cityTV.setText(city.getCity());
                weaImgIV.setImageResource(getResources().getIdentifier(dataBean.getWea_img(), "drawable", getContext().getPackageName()));

                LoadingDialog.disDialog();
            }
        });


    }

    private void findId() {
        temTV = (TextView) view.findViewById(R.id.fragment_zhuye_weather_card_tem);
        weaTV = (TextView) view.findViewById(R.id.fragment_zhuye_weather_card_wea);
        winTV = (TextView) view.findViewById(R.id.fragment_zhuye_weather_card_win);
        winSpeedTV = (TextView) view.findViewById(R.id.fragment_zhuye_weather_card_win_speed);
        cityTV = (TextView) view.findViewById(R.id.fragment_zhuye_weather_card_city);
        refreshBT = (ImageView) view.findViewById(R.id.fragment_zhuye_weather_card_refreshBT);
        weaImgIV = (ImageView) view.findViewById(R.id.fragment_zhuye_weather_card_wea_img);
        switchBT = (Button) view.findViewById(R.id.fragment_zhuye_weather_card_switchBT);

        /*news区*/
        gvNews = (GridView) view.findViewById(R.id.fragment_zhuye_news_GV);
        lvNews = (ListView) view.findViewById(R.id.fragment_zhuye_news_LV);
    }
}
