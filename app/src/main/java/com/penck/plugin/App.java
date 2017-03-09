package com.penck.plugin;

import android.app.Application;
import android.content.Context;

import com.penck.manager.PluginManager;
import com.penck.plugin.plugin.PluginApkManager;

/**
 * Created by peng on 2017/3/8.
 */

public class App extends Application {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mInstance = this;
        //savePluginApkToStorage
        PluginApkManager.getInstance().loadPluginApk();
        //instrument-plugin-load初始化
        PluginManager.init(this);
    }

    public static App getInstance() {
        return mInstance;
    }
}
