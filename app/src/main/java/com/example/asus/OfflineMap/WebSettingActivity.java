package com.example.asus.OfflineMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by asus on 2016/11/7.
 */
public class WebSettingActivity extends Activity {
    private ImageView Back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_setting);
        Back=(ImageView)findViewById(R.id.Back);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
