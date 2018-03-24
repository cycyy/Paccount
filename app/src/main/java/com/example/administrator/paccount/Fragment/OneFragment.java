package com.example.administrator.paccount.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.administrator.paccount.Activity.DetailActivity;
import com.example.administrator.paccount.Model.MDetail_Model;
import com.example.administrator.paccount.Model.RemainMoney;
import com.example.administrator.paccount.Model.TDetail_Model;
import com.example.administrator.paccount.Model.WDetail_Model;
import com.example.administrator.paccount.R;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Tools.PreferencesUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {
    private TextView budget_textView;
    private TextView tcost;
    private TextView tincome;
    private TextView wcost;
    private TextView wincome;
    private TextView mcost;
    private TextView mincome;
    private TextView t;
    private TextView w;
    private TextView m;
    private TextView monthin;
    private TextView monthout;
    private LinearLayout tLinearLayout;
    private LinearLayout wLinearLayout;
    private LinearLayout mLinearLayout;
    RequestParams params,params1,params2,params3;
    PreferencesUtil preferencesUtil;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //listAdapter=new SimpleAdapter()
        init(rootView);
        getnowMoney();
        getdayMoney();
        getweekMoney();
        geetMonthMoney();
        budget_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBudget();
            }
        });
        tLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("type","day");
                intent.setClass(getActivity(), DetailActivity.class);
                startActivity(intent);

            }
        });
        wLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("type","week");
                intent.setClass(getActivity(), DetailActivity.class);
                startActivity(intent);

            }
        });
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("type","month");
                intent.setClass(getActivity(), DetailActivity.class);
                startActivity(intent);

            }
        });
        return rootView;
    }

    private void init(View rootView) {
        preferencesUtil=new PreferencesUtil(getContext());
        budget_textView=(TextView) rootView.findViewById(R.id.budget);
        tcost=(TextView)rootView.findViewById(R.id.toutcome);
        tincome=(TextView)rootView.findViewById(R.id.tincome);
        wcost=(TextView)rootView.findViewById(R.id.woutcome);
        wincome=(TextView)rootView.findViewById(R.id.wincome);
        mcost=(TextView)rootView.findViewById(R.id.moutcome);
        mincome=(TextView)rootView.findViewById(R.id.mincome);
        t=(TextView)rootView.findViewById(R.id.tcost);
        w=(TextView)rootView.findViewById(R.id.tcost);
        m=(TextView)rootView.findViewById(R.id.tcost);
        monthin=(TextView)rootView.findViewById(R.id.income);
        monthout=(TextView)rootView.findViewById(R.id.outcome);
        tLinearLayout=(LinearLayout)rootView.findViewById(R.id.tLinearLayout);
        wLinearLayout=(LinearLayout)rootView.findViewById(R.id.wLinearLayout);
        mLinearLayout=(LinearLayout)rootView.findViewById(R.id.mLinearLayout);
    }

    private void setBudget() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("请设置预算");
        View view=LayoutInflater.from(getContext()).inflate(R.layout.setbudget_dialog,null);
        builder.setView(view);
        final EditText setbudget = (EditText)view.findViewById(R.id.setbudget);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                budget_textView.setText(setbudget.getText().toString());
                RequestParams params = new RequestParams("http://183.175.12.164:8080/setmonthmoney");
                params.addParameter("usermail",preferencesUtil.getUseremail());
                params.addParameter("userid",preferencesUtil.getUserId());
                params.addParameter("money",setbudget.getText().toString());
                x.http().get(params, new Callback.CacheCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getContext().getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
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
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void getnowMoney() {
        params=new RequestParams("http://183.175.12.164:8080/getnowmoney");
        params.addQueryStringParameter("usermail",preferencesUtil.getUseremail());
        params.addParameter("userid",preferencesUtil.getUserId());
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                RemainMoney remainMoney=gson.fromJson(result,RemainMoney.class);
                if(remainMoney.getMoney()!=0){
                    budget_textView.setText(Double.toString(remainMoney.getMoney()));
                }

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

    public void getdayMoney() {
        params1=new RequestParams("http://183.175.12.164:8080/today");
        params1.addQueryStringParameter("usermail",preferencesUtil.getUseremail());
        params1.addParameter("userid",preferencesUtil.getUserId());
        x.http().get(params1, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                TDetail_Model tDetail_model=gson.fromJson(result,TDetail_Model.class);
                tcost.setText(Double.toString(tDetail_model.getAllsub()));
                tincome.setText(Double.toString(tDetail_model.getAlladd()));
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

    private void getweekMoney() {
        params2=new RequestParams("http://183.175.12.164:8080/week");
        params2.addQueryStringParameter("usermail",preferencesUtil.getUseremail());
        params2.addParameter("userid",preferencesUtil.getUserId());
        x.http().get(params2, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                WDetail_Model wDetail_model=gson.fromJson(result, WDetail_Model.class);
                wcost.setText(Double.toString(wDetail_model.getAllsub()));
                wincome.setText(Double.toString(wDetail_model.getAlladd()));
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

    private void geetMonthMoney() {
        params3=new RequestParams("http://183.175.12.164:8080/month");
        params3.addQueryStringParameter("usermail",preferencesUtil.getUseremail());
        params3.addParameter("userid",preferencesUtil.getUserId());
        x.http().get(params3, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                MDetail_Model mDetail_model=gson.fromJson(result, MDetail_Model.class);
                mcost.setText(Double.toString(mDetail_model.getAllsub()));
                mincome.setText(Double.toString(mDetail_model.getAlladd()));
                monthin.setText(Double.toString(mDetail_model.getAlladd()));
                monthout.setText(Double.toString(mDetail_model.getAllsub()));
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
