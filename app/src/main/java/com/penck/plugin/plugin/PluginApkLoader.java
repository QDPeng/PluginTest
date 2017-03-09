package com.penck.plugin.plugin;

import android.util.Log;

import com.penck.plugin.App;
import com.penck.plugin.LogUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import dalvik.system.DexClassLoader;

/**
 * Created by peng on 2017/3/8.
 * 插件apk加载器
 */

public class PluginApkLoader {
    private DexClassLoader mDexClassLoader;


    /**
     * 加载插件APK
     *
     * @param pluginName 插件APK的名称
     */
    public void loadPluginApk(String pluginName) {
        mDexClassLoader = createDexClassLoader(pluginName);
        LogUtil.i("loadPlugin:" + pluginName);
    }

    /**
     * 加载指定名称的类
     *
     * @param className 类名(包含包名)
     */
    public Object newInstance(String className) {
        if (mDexClassLoader == null) {
            return null;
        }

        try {
            Class<?> clazz = mDexClassLoader.loadClass(className);
            return clazz.newInstance();
        } catch (Exception e) {
            LogUtil.i("newInstanceError:" + e.toString());
        }

        return null;
    }

    /**
     * 加载指定名称的类
     *
     * @param className 类名(包含包名)
     */
    public Class<?> newClazz(String className) {
        if (mDexClassLoader == null) {
            return null;
        }

        try {
            return mDexClassLoader.loadClass(className);
        } catch (Exception e) {
            LogUtil.i("newInstanceError:" + e.toString());
        }

        return null;
    }


    private String getPluginApkDirectory() {
        File pluginPath = App.getInstance().getDir("apk", 0);
        return pluginPath.getAbsolutePath() + "/";
    }

    /**
     * 将插件APK保存至SD卡
     *
     * @param pluginName 插件APK的名称
     */
    private boolean savePluginApkToStorage(String pluginName) {
        String pluginApkPath = this.getPluginApkDirectory() + pluginName;

        File plugApkFile = new File(pluginApkPath);
        if (plugApkFile.exists()) {
            try {
                plugApkFile.delete();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        BufferedInputStream inStream = null;
        BufferedOutputStream outStream = null;

        try {
            InputStream stream = App.getInstance().getAssets().open("plugin/" + pluginName);
            inStream = new BufferedInputStream(stream);
            outStream = new BufferedOutputStream(new FileOutputStream(pluginApkPath));

            final int BUF_SIZE = 4096;
            byte[] buf = new byte[BUF_SIZE];
            while (true) {
                int readCount = inStream.read(buf, 0, BUF_SIZE);
                if (readCount == -1) {
                    break;
                }
                outStream.write(buf, 0, readCount);
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                }
                inStream = null;
            }

            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                }
                outStream = null;
            }
        }
        return true;
    }

    /**
     * Create Class Loader
     *
     * @param pluginName 插件APK名称
     */
    private DexClassLoader createDexClassLoader(String pluginName) {
        boolean saved = savePluginApkToStorage(pluginName);
        if (!saved) {
            return null;
        }

        DexClassLoader classLoader = null;
        try {
            String apkPath = getPluginApkDirectory() + pluginName;
            File dexOutputDir = App.getInstance().getDir("dex", 0);
            String dexOutputDirPath = dexOutputDir.getAbsolutePath();
            LogUtil.i(" apkPath = " + apkPath + " dexOutputPath = " + dexOutputDirPath);

            ClassLoader cl = App.getInstance().getClassLoader();
            classLoader = new DexClassLoader(apkPath, dexOutputDirPath, null, cl);
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.i("createDexClassLoaderError:" + e.toString());
        }
        return classLoader;
    }

}
