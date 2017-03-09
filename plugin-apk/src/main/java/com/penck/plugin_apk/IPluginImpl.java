package com.penck.plugin_apk;

import android.content.Context;
import android.content.Intent;

import com.penck.library.IPlugin;
import com.penck.library.PluginBaseActivity;

/**
 * Created by peng on 2017/3/8.
 */

public class IPluginImpl implements IPlugin {
    @Override
    public String getPluginVersion() {
        return "version 1.0.0";
    }

    @Override
    public String getPluginMessage() {
        return "this message is from plugin apk";
    }

    @Override
    public void startPluginActivity(Context context) {
        Intent intent = new Intent(context, PluginBaseActivity.class);
        context.startActivity(intent);
    }
}
