package com.white.comporting.heyletsmeet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.location.*;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Baek on 2016-04-11.
 */
public class Location_Select  extends AppCompatActivity implements OnMapReadyCallback
{
    SupportMapFragment mapFragment;
    String ApiId = "Rl8S6gGJny8y5aRlpCbd";
    EditText getAdd;
    Button getLocation;
    Button btnEndLocal;
    String strAddress;
    List<Address> listAddress;





    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_select);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getAdd = (EditText) findViewById(R.id.etGetAddress);
        getLocation= (Button) findViewById(R.id.bGetLocation);
        btnEndLocal = (Button) findViewById(R.id.btnlocalend);

        getLocation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.bGetLocation:
                        Geocoder geocoder;
                        InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getLocation .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        strAddress = getAdd.getText().toString();
                        geocoder = new Geocoder(Location_Select.this);
                        try {
                            listAddress = geocoder.getFromLocationName(strAddress,1);
                            LatLng LocalData = new LatLng(listAddress.get(0).getLatitude(),listAddress.get(0).getLongitude());
                            map.clear();
                            map.addMarker(new MarkerOptions().position(LocalData).title(listAddress.get(0).getFeatureName()));
                            map.moveCamera(CameraUpdateFactory.newLatLng(LocalData));



                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;

                }

            }
        });

        btnEndLocal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.btnlocalend:
                        SharedPreferences preferences = getSharedPreferences("Location", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putFloat("Long", (float) listAddress.get(0).getLongitude());
                        editor.putFloat("Lat", (float) listAddress.get(0).getLatitude());
                        editor.putString("strAdd", String.valueOf(listAddress.get(0).getFeatureName()));


                        editor.commit();
                        finish();
                        break;


                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng Korea = new LatLng(35.30, 126.54);
        map = googleMap;
        Geocoder geocoder;
        googleMap.addMarker(new MarkerOptions().position(Korea).title("대한민국"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Korea));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(20),2000,null);
        geocoder = new Geocoder(Location_Select.this);
        try {
            listAddress = geocoder.getFromLocationName("서울",1);
            LatLng LocalData = new LatLng(listAddress.get(0).getLatitude(),listAddress.get(0).getLongitude());
            map.clear();
            map.addMarker(new MarkerOptions().position(LocalData).title(listAddress.get(0).getFeatureName()));
            map.moveCamera(CameraUpdateFactory.newLatLng(LocalData));
            map.animateCamera(CameraUpdateFactory.zoomTo(12),2000,null);
            UiSettings ui = map.getUiSettings();
            ui.setZoomControlsEnabled(true);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }


}

