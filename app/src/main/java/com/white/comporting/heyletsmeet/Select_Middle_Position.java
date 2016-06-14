package com.white.comporting.heyletsmeet;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Baek on 2016-04-11.
 */
public class Select_Middle_Position extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    GoogleMap mGoogleMap;
    ArrayList<Location_Data> LocDataArray = new ArrayList<Location_Data>();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_middle_position);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        SharedPreferences edit = getSharedPreferences("LocationArray", MODE_PRIVATE);
        for(int i = 0 ; i < 5 ; i ++)
        {
            double Long = Location_Data.PrGetDouble(edit,"Long"+i,0);
            double Lat = Location_Data.PrGetDouble(edit,"Lat"+i,0);
            String strAdd = edit.getString("strAdd"+i,"");
            int pos = edit.getInt("pos"+i,-1);



        }

        SharedPreferences.Editor editor = edit.edit();
        editor.clear();
        editor.commit();


        LocDataArray.add(new Location_Data(37.560952,126.986445,"명동"));
        LocDataArray.add(new Location_Data(37.497965,127.027547,"강남"));
        LocDataArray.add(new Location_Data(37.556877,126.923751,"홍대입구"));
        LocDataArray.add(new Location_Data(37.559775,126.942316,"신촌"));
        LocDataArray.add(new Location_Data(37.556744,126.945855,"이대"));
        LocDataArray.add(new Location_Data(37.570422,126.992129,"종로"));
        LocDataArray.add(new Location_Data(37.582054,127.001941,"혜화"));
        LocDataArray.add(new Location_Data(37.540412,127.069254,"건대"));
        LocDataArray.add(new Location_Data(37.526134,127.028488,"압구정"));
        LocDataArray.add(new Location_Data(37.534516,126.994587,"이태원"));
        LocDataArray.add(new Location_Data(37.484252,126.929664,"신림"));
        LocDataArray.add(new Location_Data(37.513226,127.101057,"잠실"));
        LocDataArray.add(new Location_Data(37.656380,127.063352,"노원"));
        LocDataArray.add(new Location_Data(37.619045,126.921172,"연신내"));
        LocDataArray.add(new Location_Data(37.505981,127.004253,"고속터미널"));
        LocDataArray.add(new Location_Data(37.401887,126.922635,"안양"));
        LocDataArray.add(new Location_Data(37.266297,126.999515,"수원"));
        LocDataArray.add(new Location_Data(37.384970,127.123257,"서현"));
        LocDataArray.add(new Location_Data(37.489456,126.724561,"부평"));
        LocDataArray.add(new Location_Data(37.484073,126.782805,"부천"));

        mGoogleMap = googleMap;

        CameraUpdate update = CameraUpdateFactory.newLatLng(
                new LatLng(37.266297, 126.999515));
        // 위도,경도
        mGoogleMap.moveCamera(update);

        // 보기 좋게 확대 zoom 하기
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
        mGoogleMap.animateCamera(zoom);

        for(int i =0; i < LocDataArray.size() ; i++)
        {
            MarkerOptions Subway = new MarkerOptions();
            Subway.position(new LatLng(LocDataArray.get(i).Long,LocDataArray.get(i).Lat));
            Subway.title(LocDataArray.get(i).strAdd);
            // 마커를 추가하고 말풍선 표시한 것을 보여주도록 호출
            mGoogleMap.addMarker(Subway).showInfoWindow();
            // 마커 표시하기 : 위치지정, 풍선말 설정
        }

        // 마커 클릭했을 떄 처리 : 리스너 달기
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                // TODO Auto-generated method stub
                marker.getTitle();

                return false;
            }
        });
    }
}
