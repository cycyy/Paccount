package com.example.administrator.paccount.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.administrator.paccount.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import Tools.PreferencesUtil;

public class RecordActivity extends AppCompatActivity {
    private OptionsPickerView pvOptions;
    private OptionsPickerView pvOptions1;
    private EditText record_money_editText;
    private TextView record_kind_textView;
    private EditText message_editText;
    private TextView in_or_out_textView;
    private String choose_position;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        record_money_editText=(EditText) findViewById(R.id.record_money);
        record_kind_textView=(TextView)findViewById(R.id.record_kind);
        message_editText=(EditText) findViewById(R.id.message);
        in_or_out_textView=(TextView)findViewById(R.id.in_or_out);
        button=(Button)findViewById(R.id.record_ok);
        in_or_out_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker1();
            }
        });
        record_kind_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker2();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in_or_out_textView.getText().toString()=="支出"){
                    pay();
                }
                else income();
            }
        });
    }

    private void income() {
        RequestParams params = new RequestParams("http://183.175.12.164:8080/income");
    }

    private void pay() {
        PreferencesUtil preferencesUtil=new PreferencesUtil(getApplicationContext());
        RequestParams params = new RequestParams("http://183.175.12.164:8080/pay");
        params.addParameter("money",record_money_editText.getText().toString());
        params.addParameter("kind",record_kind_textView.getText().toString());
        params.addParameter("usermail",preferencesUtil.getUseremail());
        params.addParameter("userid",preferencesUtil.getUserId());
        params.addParameter("remark",message_editText.getText().toString());
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(RecordActivity.this,FragmentActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
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

    private void initPicker1() {
        final ArrayList<String>  optionsItems1 = new ArrayList<>();
        optionsItems1.add("收入");
        optionsItems1.add("支出");
        pvOptions1 = new  OptionsPickerView.Builder(RecordActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                choose_position = optionsItems1.get(options1);
                in_or_out_textView.setText(choose_position);
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        pvOptions1.setPicker(optionsItems1);
        pvOptions1.show();
    }

    public void initPicker2(){
        final ArrayList<String>  optionsItems = new ArrayList<>();
        optionsItems.add("衣服饰品");
        optionsItems.add("食品酒水");
        optionsItems.add("居家物业");
        optionsItems.add("行车交通");
        optionsItems.add("休闲娱乐");
        optionsItems.add("医疗保健");
        optionsItems.add("其他杂项");
        pvOptions = new  OptionsPickerView.Builder(RecordActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                choose_position = optionsItems.get(options1);
                record_kind_textView.setText(choose_position);
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
        .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        pvOptions.setPicker(optionsItems);
        pvOptions.show();



    }
}
