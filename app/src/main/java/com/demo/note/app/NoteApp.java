package com.demo.note.app;

import android.app.Application;
import android.content.Context;

/**
 * @author haoyupeng
 * @description：
 * @time 2021/5/19
 */

public class NoteApp extends Application {

    public static final String IP = "http://192.168.3.29:8080/";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
