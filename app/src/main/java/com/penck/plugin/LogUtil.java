package com.penck.plugin;

import android.util.Log;


/**
 * Created by liusp on 2016/4/22.
 */
public class LogUtil {
    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "plugin";

    public static void i(String info) {
        if (DEBUG) Log.i(TAG, info);
    }

    public static void showAll(String info) {
        int LOG_MAXLENGTH = 5000;
        if (DEBUG) {
            int strLength = info.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    i(info.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    i(info.substring(start, strLength));
                    break;
                }
            }
        }
    }
}
