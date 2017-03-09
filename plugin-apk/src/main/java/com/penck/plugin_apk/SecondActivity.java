package com.penck.plugin_apk;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.penck.dynamic.DLBasePluginActivity;

/**
 * Created by peng on 2017/3/9.
 */

public class SecondActivity extends DLBasePluginActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(R.layout.activity_second);
        that.findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(that, "SecondActivity", Toast.LENGTH_LONG).show();
            }
        });

    }


}
