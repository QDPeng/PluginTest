package com.penck.plugin.plugin;

import com.penck.library.IPlugin;

/**
 * Created by peng on 2017/3/8.
 */

public class PluginApkManager {
    private static final String PLUGIN_NAME = "plugin.apk";
    private PluginApkLoader mPluginLoader;

    private static final class ManagerHolder {
        private static final PluginApkManager INSTANCE = new PluginApkManager();
    }

    public static PluginApkManager getInstance() {
        return ManagerHolder.INSTANCE;
    }

    /**
     * Load theloadDtPluginApk apk
     */
    public void loadPluginApk() {
        if (mPluginLoader == null) {
            mPluginLoader = new PluginApkLoader();
            mPluginLoader.loadPluginApk(PLUGIN_NAME);
        }
    }

    public IPlugin creatIPlugin() {
        if (mPluginLoader == null) return null;
        return (IPlugin) mPluginLoader.newInstance("com.penck.plugin_apk.IPluginImpl");
    }

    public Class<?> getClazz() {
        if (mPluginLoader == null) return null;
        return mPluginLoader.newClazz("com.penck.plugin_apk.PluginActivity");
    }


}
