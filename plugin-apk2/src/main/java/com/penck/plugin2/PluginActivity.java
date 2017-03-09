package com.penck.plugin2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by peng on 2017/3/8.
 */

public class PluginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PluginActivity.this, SecondActivity.class));
            }
        });
    }

//    public void onViewClick(View view) {
////        startActivity(new Intent(this, SecondActivity.class));
//    }
}
