package com.example.administrator.paccount.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.paccount.Model.MDetail_Model;
import com.example.administrator.paccount.Model.TDetail_Model;
import com.example.administrator.paccount.Model.WDetail_Model;
import com.example.administrator.paccount.R;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import Tools.ListAdapter1;
import Tools.ListAdapter2;
import Tools.ListAdapter3;
import Tools.PreferencesUtil;

public class DetailActivity extends AppCompatActivity {
    private List<TDetail_Model.ListBean> list1;
    private List<WDetail_Model.ListBean> list2;
    private List<MDetail_Model.ListBean> list3;
    private ListView listView;
    ListAdapter1 listAdapter1;
    ListAdapter2 listAdapter2;
    ListAdapter3 listAdapter3;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
         intent= getIntent();
        listView=(ListView)findViewById(R.id.detail);
        if(intent.getStringExtra("type")=="day"){
            getDay();
        }
        else if(intent.getStringExtra("type")=="week"){
            getWeek();
        }
        else {
            getMonth();
        }
    }
    private void getWeek(){
        RequestParams params1=new RequestParams("http://183.175.12.164:8080/week");
        PreferencesUtil preferencesUtil=new PreferencesUtil(getApplicationContext());
        params1.addQueryStringParameter("usermail",preferencesUtil.getUseremail());
        params1.addParameter("userid",preferencesUtil.getUserId());
        x.http().get(params1, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                WDetail_Model wDetail_model=gson.fromJson(result,WDetail_Model.class);
                list2=wDetail_model.getList();
                listAdapter2=new ListAdapter2(getApplicationContext());
                listAdapter2.setListItems(list2);
                listView.setAdapter(listAdapter2);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }

    private void getMonth() {
        RequestParams params1=new RequestParams("http://183.175.12.164:8080/today");
        PreferencesUtil preferencesUtil=new PreferencesUtil(getApplicationContext());
        params1.addQueryStringParameter("usermail",preferencesUtil.getUseremail());
        params1.addParameter("userid",preferencesUtil.getUserId());
        x.http().get(params1, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                MDetail_Model mDetail_model=gson.fromJson(result,MDetail_Model.class);
                list3=mDetail_model.getList();
                listAdapter3=new ListAdapter3(getApplicationContext());
                listAdapter3.setListItems(list3);
                listView.setAdapter(listAdapter3);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    private void getDay() {
        RequestParams params1=new RequestParams("http://183.175.12.164:8080/today");
        PreferencesUtil preferencesUtil=new PreferencesUtil(getApplicationContext());
        params1.addQueryStringParameter("usermail",preferencesUtil.getUseremail());
        params1.addParameter("userid",preferencesUtil.getUserId());
        x.http().get(params1, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                TDetail_Model tDetail_model=gson.fromJson(result,TDetail_Model.class);
                list1=tDetail_model.getList();
                listAdapter1=new ListAdapter1(getApplicationContext());
                listAdapter1.setListItems(list1);
                listView.setAdapter(listAdapter1);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
