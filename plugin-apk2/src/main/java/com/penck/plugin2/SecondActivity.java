package com.penck.plugin2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by peng on 2017/3/9.
 */

public class SecondActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "SecondActivity", Toast.LENGTH_LONG).show();
            }
        });

    }


}
