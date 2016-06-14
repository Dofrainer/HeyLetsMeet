package com.white.comporting.heyletsmeet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Type_List extends AppCompatActivity {

    ListView_Type_Adapter TypeAdapter;
    String Select_Subway;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_list);

        SharedPreferences edit = getSharedPreferences("FinalData", MODE_PRIVATE);
        Select_Subway = edit.getString("strAdd","");
        SharedPreferences.Editor editor = edit.edit();
        editor.clear();
        editor.commit();

        ListView listViewType = (ListView)findViewById(R.id.listviewType); // 탭1 리스트뷰 추가

        ArrayList<ListView_Type_Data> ArrayListType = new ArrayList<ListView_Type_Data>();
        ListView_Type_Data k1 = new ListView_Type_Data();// 리스트에 추가할 객체입니다.
        ArrayListType.add(k1);// 리스트에 객체를 추가합니다.
        TypeAdapter = new ListView_Type_Adapter(this, this, R.layout.listview_type, ArrayListType);// 어댑터를 생성합니다.
        listViewType.setAdapter(TypeAdapter);
        findViewById(R.id.btnAddType).setOnClickListener (mClickListener);
        findViewById(R.id.broadType).setOnClickListener (mClickListener);

    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.broadType:
                    Log.d("OnClickListener", "click session button");
                    SharedPreferences preferences = getSharedPreferences("FinalData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("strAdd", Select_Subway);
                    editor.putInt("intTypeSize", TypeAdapter.CountItem());
                    ArrayList<String> arrType = new ArrayList<String>();

                    for(int i = 0; i < TypeAdapter.CountItem() ; i++)
                    {
                        editor.putInt("intType"+i, TypeAdapter.getItem(i).getKind());

                    }
                    editor.commit();
                    //임시

                   Intent intentSubActivity = new Intent(Type_List.this, Client.class);
                    startActivity(intentSubActivity);

                    break;
                case R.id.btnAddType:
                    if(TypeAdapter.CountItem() < 5)
                    {
                        TypeAdapter.addItem();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "5개 이상 늘릴수 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    break;


            }
        }
    };

}

