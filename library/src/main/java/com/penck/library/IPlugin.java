package com.penck.library;

import android.content.Context;

/**
 * Created by peng on 2017/3/8.
 * 定义插件apk和宿主app共享接口
 */

public interface IPlugin {
    String getPluginVersion();

    String getPluginMessage();

    void startPluginActivity(Context context);
}
