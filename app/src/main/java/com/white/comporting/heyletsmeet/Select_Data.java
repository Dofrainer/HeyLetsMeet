package com.white.comporting.heyletsmeet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class Select_Data extends Activity {

    ListView_Peaple_Adapter peapleAdapter;
    ListView_Kind_Adapter kindAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_data);
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        // findViewById를 이용해 TabHost인스턴스를 얻은경우 꼭 호출 필요
        tabHost.setup();
        // Tab builder 객체
        TabHost.TabSpec spec;


        // Tab 01 세팅 & 등록
        spec = tabHost.newTabSpec("Tab 00"); // Tab Builder 객체 생성
        spec.setIndicator("인원 선택");         // Tab 제목
        spec.setContent(R.id.Tab1);       // Tab 내용
        tabHost.addTab(spec);               // Tab 등록

        ListView listViewPeaple = (ListView)findViewById(R.id.listviewPeaple); // 탭1 리스트뷰 추가

        ArrayList<ListView_Peaple_Data> ArrayListPeaple = new ArrayList<ListView_Peaple_Data>();
        ListView_Peaple_Data p1 = new ListView_Peaple_Data();// 리스트에 추가할 객체입니다.
        ArrayListPeaple.add(p1);// 리스트에 객체를 추가합니다.
        peapleAdapter = new  ListView_Peaple_Adapter(this, this, R.layout.listview_peaple, ArrayListPeaple);// 어댑터를 생성합니다.
        listViewPeaple.setAdapter(peapleAdapter);
        findViewById(R.id.btnAddPeaple).setOnClickListener (mClickListener);




        // Tab 02 세팅 & 등록
        spec = tabHost.newTabSpec("Tab 01"); // Tab Builder 객체 생성
        spec.setIndicator("업종 선택");        // Tab 제목
        spec.setContent(R.id.Tab2);    // Tab 내용

        ListView listViewKind = (ListView)findViewById(R.id.listviewKind); // 탭1 리스트뷰 추가

        ArrayList<ListView_Kind_Data> ArrayListKind = new ArrayList<ListView_Kind_Data>();
        ListView_Kind_Data k1 = new ListView_Kind_Data();// 리스트에 추가할 객체입니다.
        ArrayListKind.add(k1);// 리스트에 객체를 추가합니다.
        kindAdapter = new  ListView_Kind_Adapter(this, this, R.layout.listview_kind, ArrayListKind);// 어댑터를 생성합니다.
        listViewKind.setAdapter(kindAdapter);
        findViewById(R.id.btnAddKind).setOnClickListener (mClickListener);




        tabHost.addTab(spec);  // Tab 등록

        tabHost.setCurrentTab(0);  // 처음 등록된 Tab을 보여줌.


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
                case R.id.btnAddKind:
                    kindAdapter.addItem();
                    break;

            }
        }
    };
}
