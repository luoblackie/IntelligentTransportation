package com.edu.transportation.intelligenttransportation.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.transportation.intelligenttransportation.R;
import com.edu.transportation.intelligenttransportation.Utils.ConvertTools;
import com.edu.transportation.intelligenttransportation.dialog.LoadingDialog;
import com.edu.transportation.intelligenttransportation.fragment.FragmentZhuYe;
import com.edu.transportation.intelligenttransportation.zhuYeClass.News;
import com.edu.transportation.intelligenttransportation.zhuYeClass.NewsReply;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class ActivityNewsDetail extends AppCompatActivity {
    private Toolbar toolBar;
    private ImageView headPic;
    private TextView newsTitle;
    private TextView newsContent;
    private ConvertTools convertTools;
    private Gson gson = new Gson();
    private News.DataBean newsData;
    private TextView newsSourse;
    private ListView lv;
    private String aId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ativity_news_details);

        toolBar = (Toolbar) findViewById(R.id.ativity_news_details_toolBar);
        setSupportActionBar(toolBar);

        convertTools = new ConvertTools(this);

        initToolBar();

        findId();

        initData();

        initLV();

    }

    private void initLV() {
        convertTools.getDataFromWeb("http://api.dagoogle.cn/news/nreply?aid=" + aId, new ConvertTools.GetData() {
            @Override
            public void toDo(JSONObject jsonObject) {
                NewsReply newsReply = gson.fromJson(jsonObject.toString(), NewsReply.class);

                final List<NewsReply.DataBean.ListBean> replyList = newsReply.getData().getList();

                lv.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return replyList.size();
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
                            convertView = LayoutInflater.from(ActivityNewsDetail.this)
                                    .inflate(R.layout.ativity_news_details_lv_unit, null);

                            TextView userName = (TextView) convertView.findViewById(R.id.ativity_news_details_lv_unit_userName);
                            TextView replyTV = (TextView) convertView.findViewById(R.id.ativity_news_details_lv_unit_replyTV);

                            NewsReply.DataBean.ListBean bean = replyList.get(position);

                            userName.setText("用户：" + bean.getReplier());
                            replyTV.setText(bean.getReply_cnt());

                        }
                        return convertView;
                    }
                });

                ConvertTools.setListViewHeighBaseOnChild(lv);
            }
        });
    }

    private void initData() {
        LoadingDialog.showDialog(this);

        aId = getIntent().getStringExtra(FragmentZhuYe.NEWS_ID);

        if (aId != null){
            convertTools.getDataFromWeb("http://api.dagoogle.cn/news/ndetail?aid=" + aId, new ConvertTools.GetData() {
                @Override
                public void toDo(JSONObject jsonObject) {
                    News news = gson.fromJson(jsonObject.toString(), News.class);
                    newsData = news.getData();

                    initWin();
                }
            });
        }
    }

    private void initWin() {
        convertTools.getBitmapFromWeb(newsData.getHeadpic(), new ConvertTools.GetBitmap() {
            @Override
            public void toDo(Bitmap bitmap) {
                headPic.setImageBitmap(bitmap);

                LoadingDialog.disDialog();
            }
        });

        newsSourse.setText(newsData.getSource());

        newsTitle.setText(newsData.getTitle());

        Spanned spanned = Html.fromHtml(newsData.getContent(), new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                final LevelListDrawable drawable = new LevelListDrawable();

                convertTools.getBitmapFromWeb(source, new ConvertTools.GetBitmap() {
                    @Override
                    public void toDo(Bitmap bitmap) {
                        drawable.addLevel(1, 1, new BitmapDrawable(bitmap));
                        drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                        drawable.setLevel(1);
                        CharSequence text = newsContent.getText();
                        newsContent.setText(text);
                        newsContent.refreshDrawableState();
                    }
                });

                return drawable;
            }
        }, null);

        newsContent.setText(spanned);
    }

    private void findId() {
        headPic = (ImageView) findViewById(R.id.ativity_news_details_toolBar_headPic);
        newsTitle = (TextView) findViewById(R.id.ativity_news_details_toolBar_newsTitle);
        newsContent = (TextView) findViewById(R.id.ativity_news_details_newsContent);
        newsSourse = (TextView) findViewById(R.id.ativity_news_details_newsSourse);
        lv = (ListView) findViewById(R.id.ativity_news_details_LV);
    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }
}
