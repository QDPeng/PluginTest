package com.penck.plugin;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.penck.dynamic.internal.DLIntent;
import com.penck.dynamic.internal.DLPluginManager;
import com.penck.dynamic.utils.DLUtils;
import com.penck.library.IPlugin;
import com.penck.manager.PluginManager;
import com.penck.manager.environment.PlugInfo;
import com.penck.plugin.plugin.PluginApkManager;

import java.io.File;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    private final String path = "/data/data/com.penck.plugin/app_apk/plugin.apk";
    PluginItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadApkPlugin();
    }

    /**
     * 使用dynamic-plugin-load加载apk
     */
    private void loadApkPlugin() {
        item = new PluginItem();
        item.pluginPath = path;
        item.packageInfo = DLUtils.getPackageInfo(this, item.pluginPath);
        DLPluginManager.getInstance(this).loadApk(item.pluginPath);
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.getPlugin:
                //测试插件通信
                IPlugin plugin = PluginApkManager.getInstance().creatIPlugin();
                if (plugin != null) {
                    String version = plugin.getPluginVersion();
                    String info = plugin.getPluginMessage();
                    Toast.makeText(this, version + "," + info, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "is null", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.startPluginActivity:
                startApk();
                break;
            case R.id.startPluginActivity2:
                startApk2();
                break;
        }
    }

    /**
     * 使用dynamic-plugin-load启动apk插件
     * 可以跳转多个activity
     */
    private void startApk() {
        DLPluginManager pluginManager = DLPluginManager.getInstance(this);
        pluginManager.startPluginActivity(this, new DLIntent(item.packageInfo.packageName));
    }

    /**
     * 使用instrument-plugin-load启动apk插件
     * 只能启动一个activity 不能跳转其他activity，因为没有在manifest中声明
     */
    private void startApk2() {
        try {
            Collection<PlugInfo> collection = PluginManager.getSingleton().loadPlugin(new File(path));
            if (collection != null && !collection.isEmpty()) {
                PluginManager.getSingleton().dump();
                for (PlugInfo info : collection) {
                    PluginManager.getSingleton().startMainActivity(this, info);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class PluginItem {
        public PackageInfo packageInfo;
        public String pluginPath;

        public PluginItem() {
        }
    }
}
