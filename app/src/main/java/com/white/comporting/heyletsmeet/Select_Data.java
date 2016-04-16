package com.white.comporting.heyletsmeet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class Select_Data extends Activity {
    ArrayAdapter<CharSequence> adspin;
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
        // Tab 02 세팅 & 등록
        spec = tabHost.newTabSpec("Tab 01"); // Tab Builder 객체 생성
        spec.setIndicator("업종 선택");        // Tab 제목
        spec.setContent(R.id.Tab2);    // Tab 내용
        tabHost.addTab(spec);               // Tab 등록
        // 처음 등록된 Tab을 보여줌.
        tabHost.setCurrentTab(0);

//        findViewById(R.id.TextLocation).setOnClickListener(mClickListener1);
        findViewById(R.id.broad).setOnClickListener(mClickListener2);

        Spinner spnKind = (Spinner)findViewById(R.id.spinSelectKind);
        spnKind.setPrompt("업종을 선택하세요");
        adspin = ArrayAdapter.createFromResource(this,R.array.kind,android.R.layout.simple_spinner_item);
        spnKind.setAdapter(adspin);


        ListView listview ;
        ListView_Peaple_Adapter adapter;

        // Adapter 생성
        adapter = new ListView_Peaple_Adapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.ListView_Peaple);
        listview.setAdapter(adapter);


    }
    /*
    TextView.OnClickListener mClickListener1 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.TextLocation:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity =
                            new Intent(Select_Data.this, Location_Select.class);
                    startActivity(intentSubActivity);
                    break;
            }
        }
    };
    */
    Button.OnClickListener mClickListener2 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.broad:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity =
                            new Intent(Select_Data.this, Select_Middle_Position.class);
                    startActivity(intentSubActivity);
                    break;
            }
        }
    };

}
