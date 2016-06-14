package com.white.comporting.heyletsmeet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Select_Data extends AppCompatActivity {

    ListView_Peaple_Adapter peapleAdapter;
    ListView_Kind_Adapter kindAdapter;
    ArrayList<Location_Data> Data= new ArrayList<Location_Data>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_data);
        Data.add(new Location_Data());
        Data.add(new Location_Data());
        Data.add(new Location_Data());
        Data.add(new Location_Data());
        Data.add(new Location_Data());


        // Tab 01 세팅 & 등록


        ListView listViewPeaple = (ListView)findViewById(R.id.listviewPeaple); // 탭1 리스트뷰 추가

        ArrayList<ListView_Peaple_Data> ArrayListPeaple = new ArrayList<ListView_Peaple_Data>();
        ListView_Peaple_Data p1 = new ListView_Peaple_Data();// 리스트에 추가할 객체입니다.
        ArrayListPeaple.add(p1);// 리스트에 객체를 추가합니다.
        peapleAdapter = new  ListView_Peaple_Adapter(this, this, R.layout.listview_peaple, ArrayListPeaple);// 어댑터를 생성합니다.
        listViewPeaple.setAdapter(peapleAdapter);
        findViewById(R.id.btnAddPeaple).setOnClickListener (mClickListener);




    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.broad:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity =
                            new Intent(Select_Data.this, Select_Middle_Position.class);
                    startActivity(intentSubActivity);
                    break;
                case R.id.btnAddPeaple:
                    peapleAdapter.addItem();
                    break;

            }


        }
    };
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences edit = getSharedPreferences("Location", MODE_PRIVATE);
        double Long = edit.getFloat("Long",0);
        double Lat = edit.getFloat("Lat",0);
        String strAdd = edit.getString("strAdd","");
        int pos = edit.getInt("pos",-1);
        SharedPreferences.Editor editor = edit.edit();
        editor.clear();
        editor.commit();

        if(pos != -1)
        {
            Data.get(pos).Long = Long;
            Data.get(pos).Lat = Lat;
            Data.get(pos).strAdd = strAdd;
            Data.get(pos).position = pos;
            peapleAdapter.SetItem(pos,strAdd);
        }




    }



}

class Location_Data
{
    public double Long;
    public double Lat;
    public String strAdd;
    public int position;
    Location_Data( )
    {
        this.Long = 0;
        this.Lat = 0;
        this.strAdd = "";
        position = -1;
    }
    void RemoveData()
    {
        this.Long = 0;
        this.Lat = 0;
        this.strAdd = "";
        position = -1;
    }
}
