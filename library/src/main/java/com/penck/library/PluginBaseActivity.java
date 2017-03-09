package com.penck.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by peng on 2017/3/8.
 */

public class PluginBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Class clazz = Class.forName("com.penck.plugin_apk.PluginActivity");
            if (clazz != null) {
                startActivity(new Intent(this, clazz));
            }else{
                Toast.makeText(this, "not find PluginActivity", Toast.LENGTH_LONG).show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
