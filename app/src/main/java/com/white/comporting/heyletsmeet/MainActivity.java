package com.white.comporting.heyletsmeet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                startActivity(new Intent(MainActivity.this, Location_List.class));
                //overridePendingTransition(R.anim.abc_fade_in, 0);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
