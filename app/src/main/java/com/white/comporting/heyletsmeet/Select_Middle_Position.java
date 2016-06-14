package com.white.comporting.heyletsmeet;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * Created by Baek on 2016-04-11.
 */
public class Select_Middle_Position extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    GoogleMap mGoogleMap;
    ArrayList<Location_Data> LocSubwayArray = new ArrayList<Location_Data>();
    ArrayList<Location_Data> LocDataArray = new ArrayList<Location_Data>();
    Location_Data MiddlePos = new Location_Data();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_middle_position);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        UiSettings ui = googleMap.getUiSettings();
        ui.setZoomControlsEnabled(true);
        SharedPreferences edit = getSharedPreferences("LocationArray", MODE_PRIVATE);
        for(int i = 0 ; i < 5 ; i ++)
        {
            double Lat = Location_Data.PrGetDouble(edit,"Lat"+i,0);
            double Long = Location_Data.PrGetDouble(edit,"Long"+i,0);
            String strAdd = edit.getString("strAdd"+i,"");
            int pos = edit.getInt("pos"+i,-1);
            LocDataArray.add(new Location_Data(Lat,Long,strAdd,pos));


        }

        SharedPreferences.Editor editor = edit.edit();
        editor.clear();
        editor.commit();


        LocSubwayArray.add(new Location_Data(37.560952,126.986445,"명동"));
        LocSubwayArray.add(new Location_Data(37.497965,127.027547,"강남"));
        LocSubwayArray.add(new Location_Data(37.556877,126.923751,"홍대입구"));
        LocSubwayArray.add(new Location_Data(37.559775,126.942316,"신촌"));
        LocSubwayArray.add(new Location_Data(37.556744,126.945855,"이대"));
        LocSubwayArray.add(new Location_Data(37.570422,126.992129,"종로"));
        LocSubwayArray.add(new Location_Data(37.582054,127.001941,"혜화"));
        LocSubwayArray.add(new Location_Data(37.540412,127.069254,"건대"));
        LocSubwayArray.add(new Location_Data(37.526134,127.028488,"압구정"));
        LocSubwayArray.add(new Location_Data(37.534516,126.994587,"이태원"));
        LocSubwayArray.add(new Location_Data(37.484252,126.929664,"신림"));
        LocSubwayArray.add(new Location_Data(37.513226,127.101057,"잠실"));
        LocSubwayArray.add(new Location_Data(37.656380,127.063352,"노원"));
        LocSubwayArray.add(new Location_Data(37.619045,126.921172,"연신내"));
        LocSubwayArray.add(new Location_Data(37.505981,127.004253,"고속터미널"));
        LocSubwayArray.add(new Location_Data(37.401887,126.922635,"안양"));
        LocSubwayArray.add(new Location_Data(37.266297,126.999515,"수원"));
        LocSubwayArray.add(new Location_Data(37.384970,127.123257,"서현"));
        LocSubwayArray.add(new Location_Data(37.489456,126.724561,"부평"));
        LocSubwayArray.add(new Location_Data(37.484073,126.782805,"부천"));

        mGoogleMap = googleMap;

        CameraUpdate update = CameraUpdateFactory.newLatLng(
                new LatLng(37.266297, 126.999515));
        // 위도,경도
        mGoogleMap.moveCamera(update);

        // 보기 좋게 확대 zoom 하기
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
        mGoogleMap.animateCamera(zoom);

        //지하철 마커 표시하기
        for(int i = 0; i < LocSubwayArray.size() ; i++)
        {
            MarkerOptions Subway = new MarkerOptions();
            Subway.position(new LatLng(LocSubwayArray.get(i).Lat,LocSubwayArray.get(i).Long));
            Subway.title(LocSubwayArray.get(i).strAdd);
            Subway.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            // 마커를 추가하고 말풍선 표시한 것을 보여주도록 호출
            mGoogleMap.addMarker(Subway);
            // 마커 표시하기 : 위치지정, 풍선말 설정
        }

        MiddlePos.position = 0;
        MiddlePos.strAdd = "중간위치";
        //선택 마커 표시하기
        for(int i = 0; i < LocDataArray.size() ; i ++)
        {
            if(LocDataArray.get(i).position != -1) {
                MarkerOptions UserMarker = new MarkerOptions();
                UserMarker.position(new LatLng(LocDataArray.get(i).Lat, LocDataArray.get(i).Long));
                UserMarker.title(LocDataArray.get(i).strAdd);
                // 마커를 추가하고 말풍선 표시한 것을 보여주도록 호출
                mGoogleMap.addMarker(UserMarker);
                // 마커 표시하기 : 위치지정, 풍선말 설정
                MiddlePos.Lat += LocDataArray.get(i).Lat;
                MiddlePos.Long += LocDataArray.get(i).Long;
                MiddlePos.position +=1;
            }
        }

        MiddlePos.Lat /= MiddlePos.position;
        MiddlePos.Long /= MiddlePos.position;
        MarkerOptions MiddleMarker = new MarkerOptions();
        MiddleMarker.position(new LatLng(MiddlePos.Lat, MiddlePos.Long));
        MiddleMarker.title(MiddlePos.strAdd);
        MiddleMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        // 마커를 추가하고 말풍선 표시한 것을 보여주도록 호출
        mGoogleMap.addMarker(MiddleMarker).showInfoWindow();

        //중간위치와 사용자위치를 연결하는 선
        for(int i = 0; i < LocDataArray.size() ; i ++)
        {
            if(LocDataArray.get(i).position != -1) {
                PolylineOptions rectOptions = new PolylineOptions();
                rectOptions.add(new LatLng(MiddlePos.Lat,MiddlePos.Long));
                rectOptions.add(new LatLng(LocDataArray.get(i).Lat,LocDataArray.get(i).Long));
                rectOptions.color(0xFFFF0000); //빨강
                rectOptions.width(10);
                mGoogleMap.addPolyline(rectOptions);
            }
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
