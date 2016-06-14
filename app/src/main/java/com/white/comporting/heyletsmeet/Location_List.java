package com.white.comporting.heyletsmeet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Location_List extends AppCompatActivity {

    ListView_Peaple_Adapter peapleAdapter;
    ListView_Type_Adapter kindAdapter;
    ArrayList<Location_Data> LocDataArray = new ArrayList<Location_Data>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);
        LocDataArray.add(new Location_Data());
        LocDataArray.add(new Location_Data());
        LocDataArray.add(new Location_Data());
        LocDataArray.add(new Location_Data());
        LocDataArray.add(new Location_Data());


        // Tab 01 세팅 & 등록


        ListView listViewPeaple = (ListView)findViewById(R.id.listviewPeaple);

        ArrayList<ListView_Peaple_Data> ArrayListPeaple = new ArrayList<ListView_Peaple_Data>();
        ListView_Peaple_Data p1 = new ListView_Peaple_Data();// 리스트에 추가할 객체입니다.
        ArrayListPeaple.add(p1);// 리스트에 객체를 추가합니다.
        peapleAdapter = new  ListView_Peaple_Adapter(this, this, R.layout.listview_peaple, ArrayListPeaple);// 어댑터를 생성합니다.
        listViewPeaple.setAdapter(peapleAdapter);
        findViewById(R.id.btnAddPeaple).setOnClickListener (mClickListener);
        findViewById(R.id.broadPeaple).setOnClickListener (mClickListener);



    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.broadPeaple: // 완료 버튼 클릭시
                    SharedPreferences edit = getSharedPreferences("LocationArray", MODE_PRIVATE);
                    SharedPreferences.Editor editor = edit.edit();
                    for(int i = 0 ; i < 5 ; i ++)
                    {
                        Location_Data.PrePutDouble(editor,"Long"+i,LocDataArray.get(i).Long);
                        Location_Data.PrePutDouble(editor,"Lat"+i,LocDataArray.get(i).Lat);
                        editor.putString("strAdd"+i, LocDataArray.get(i).strAdd);
                        editor.putInt("pos"+i, LocDataArray.get(i).position);
                    }
                    editor.commit();

                    // 액티비티 실행
                    Intent intentSubActivity =
                            new Intent(Location_List.this, Select_Middle_Position.class);
                    startActivity(intentSubActivity);
                    break;
                case R.id.btnAddPeaple:
                     if(peapleAdapter.CountItem() < 5)
                     {
                         peapleAdapter.addItem();
                     }
                    else
                     {
                         Toast.makeText(getApplicationContext(), "5명 이상 늘릴수 없습니다.", Toast.LENGTH_SHORT).show();
                     }
                    break;

            }


        }
    };
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences edit = getSharedPreferences("Location", MODE_PRIVATE);
        double Long = Location_Data.PrGetDouble(edit,"Long",0);
        double Lat = Location_Data.PrGetDouble(edit,"Lat",0);
        String strAdd = edit.getString("strAdd","");
        int pos = edit.getInt("pos",-1);
        SharedPreferences.Editor editor = edit.edit();
        editor.clear();
        editor.commit();

        if(pos != -1)
        {
            LocDataArray.get(pos).Long = Long;
            LocDataArray.get(pos).Lat = Lat;
            LocDataArray.get(pos).strAdd = strAdd;
            LocDataArray.get(pos).position = pos;
            peapleAdapter.SetItem(pos,strAdd);
        }




    }



}

