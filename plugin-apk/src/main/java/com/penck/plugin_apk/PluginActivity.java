package com.penck.plugin_apk;

import android.os.Bundle;
import android.view.View;

import com.penck.dynamic.DLBasePluginActivity;
import com.penck.dynamic.DLBasePluginAppCompatActivity;
import com.penck.dynamic.internal.DLIntent;

/**
 * Created by peng on 2017/3/8.
 */

public class PluginActivity extends DLBasePluginActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(R.layout.activity_main);
        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLIntent dlIntent = new DLIntent(getPackageName(), SecondActivity.class);
                startPluginActivityForResult(dlIntent,0);
            }
        });
    }

//    public void onViewClick(View view) {
//        DLIntent dlIntent = new DLIntent(getPackageName(), SecondActivity.class);
//        startPluginActivityForResult(dlIntent,0);
////        startActivity(new Intent(this, SecondActivity.class));
//    }
}
