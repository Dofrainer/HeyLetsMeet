package com.white.comporting.heyletsmeet;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

/**
 * Created by Baek on 2016-04-11.
 */
public class Location_Select  extends NMapActivity

{
    private NMapView mMapView;
    private NMapController mMapController;
    private NMapLocationManager mMapLocationManager;
    String ApiId = "Rl8S6gGJny8y5aRlpCbd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_select);

        mMapView = (NMapView)findViewById(R.id.mapView);
        mMapView.setClientId(ApiId);

        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.setClickable(true);
        mMapView.requestFocus();

        mMapController = mMapView.getMapController();
        NMapView.LayoutParams lp = new NMapView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, NMapView.LayoutParams.BOTTOM_RIGHT);
        mMapView.setBuiltInZoomControls(true, lp);
        NMapLocationManager localManager = new NMapLocationManager(this);
        localManager.enableMyLocation(false);
        mMapController.animateTo(localManager.getMyLocation());
        findViewById(R.id.btnEndSelect).setOnClickListener(mClickListener);

    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEndSelect:
                // 액티비티 실행
                finish();
                break;
        }
        }
    };



}
