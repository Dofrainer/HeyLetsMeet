package com.white.comporting.heyletsmeet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Baek on 2016-04-11.
 */
public class Select_Middle_Position extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_middle_position);
        findViewById(R.id.Btn_Select_Middle).setOnClickListener(mClickListener);

    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Btn_Select_Middle:
                    // 액티비티 실행
                    finish();
                    break;
            }
        }
    };
}
