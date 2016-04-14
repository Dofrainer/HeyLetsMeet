package com.white.comporting.heyletsmeet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Baek on 2016-04-11.
 */
public class Location_List  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);
        findViewById(R.id.TextLocation).setOnClickListener(mClickListener1);
        findViewById(R.id.broad).setOnClickListener(mClickListener2);




    }
    TextView.OnClickListener mClickListener1 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.TextLocation:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity =
                            new Intent(Location_List.this, Location_Select.class);
                    startActivity(intentSubActivity);
                    break;
            }
        }
    };
    Button.OnClickListener mClickListener2 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.broad:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity =
                            new Intent(Location_List.this, Select_Middle_Position.class);
                    startActivity(intentSubActivity);
                    break;
            }
        }
    };
}
