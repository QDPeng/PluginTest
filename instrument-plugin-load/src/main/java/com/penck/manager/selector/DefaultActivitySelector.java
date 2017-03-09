package com.penck.manager.selector;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.penck.manager.DynamicActivity;


/**
 * @author Lody
 * @version 1.0
 */
public class DefaultActivitySelector implements DynamicActivitySelector {

    private static DynamicActivitySelector DEFAULT = new DefaultActivitySelector();

    @Override
    public Class<? extends Activity> selectDynamicActivity(ActivityInfo pluginActivityInfo) {
        return DynamicActivity.class;
    }

    public static DynamicActivitySelector getDefault() {
        return DEFAULT;
    }
}
