package com.white.comporting.heyletsmeet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Baek on 2016-04-11.
 */
public class Location_Select  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_select);
        findViewById(R.id.btnEndSelect).setOnClickListener(mClickListener);

    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnEndSelect:
                    // 액티비티 실행
                    finish();
                    break;
            }
        }
    };
}
