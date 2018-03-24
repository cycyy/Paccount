package com.example.administrator.paccount.Application;

import android.app.Application;
import org.xutils.x;

/**
 * Created by Administrator on 2018/3/18.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
    }
}
