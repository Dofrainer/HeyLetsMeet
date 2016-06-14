package com.white.comporting.heyletsmeet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class Client extends AppCompatActivity {
    String Select_Subway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
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
        String URL = Client_SendData.SendToServer(Select_Subway,arrType,Client.this);
        Intent myIntent;
        myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nvaer.com"));
        startActivity(myIntent);
    }
}
/**
 * Created by Baek on 2016-06-15.
 */

