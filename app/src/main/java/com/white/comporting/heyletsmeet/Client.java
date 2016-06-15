package com.white.comporting.heyletsmeet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class Client extends AppCompatActivity {
    String Select_Subway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sending);
        ArrayList<String> arrType = new ArrayList<String>();
        SharedPreferences edit = getSharedPreferences("FinalData", MODE_PRIVATE);
        Select_Subway = edit.getString("strAdd","");
        int TypeSize = edit.getInt("intTypeSize",-1);
        SharedPreferences.Editor editor = edit.edit();

        for(int i = 0; i < TypeSize ; i++) {
            int Type = edit.getInt("intType"+i, -1);
            switch (Type) {
                case 0:
                    arrType.add("전체");
                    break;
                case 1:
                    arrType.add("술집");
                    break;
                case 2:
                    arrType.add("카페");
                    break;
                case 3:
                    arrType.add("맛집");
                    break;
                case 4:
                    arrType.add("PC방");
                    arrType.add("노래방");
                    break;
            }
        }
        editor.clear();
        editor.commit();
        Handler mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                switch(inputMessage.what){
                    case Client_SendData.MessageTypeClass.SIMSOCK_DATA :
                        String msg = (String) inputMessage.obj;
                        Log.d("OUT",  msg);
                        // do something with UI
                        break;

                    case  Client_SendData.MessageTypeClass.SIMSOCK_CONNECTED :
                        // do something with UI
                        break;

                    case  Client_SendData.MessageTypeClass.SIMSOCK_DISCONNECTED :
                        // do something with UI
                        break;

                }
            }
        };

        Client_SendData ssocket = new Client_SendData("211.115.230.74", mHandler,Select_Subway, arrType,Client.this);
        ssocket.start();

        ImageView Logo = (ImageView) findViewById(R.id.imgLogo);
        Logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSubActivity =
                        new Intent(Client.this, DevInfo.class);
                startActivity(intentSubActivity);
            }
        });


    }
}
/**
 * Created by Baek on 2016-06-15.
 */

