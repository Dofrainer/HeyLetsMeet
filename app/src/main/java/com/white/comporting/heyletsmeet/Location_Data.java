package com.white.comporting.heyletsmeet;

import android.content.SharedPreferences;

public class Location_Data {
    public double Long;
    public double Lat;
    public String strAdd;
    public int position;

    public Location_Data( double Lat, double Long , String strAdd) {
        this.Long = Long;
        this.Lat = Lat;
        this.strAdd = strAdd;
        this.position = -1;
    }

    public Location_Data( double Lat, double Long , String strAdd, int position) {
        this.Long = Long;
        this.Lat = Lat;
        this.strAdd = strAdd;
        this.position = position;
    }

    public Location_Data() {
        this.Long = 0;
        this.Lat = 0;
        this.strAdd = "";
        this.position = -1;
    }

    public void RemoveData() {
        this.Long = 0;
        this.Lat = 0;
        this.strAdd = "";
        this.position = -1;
    }

    public static SharedPreferences.Editor PrePutDouble(final SharedPreferences.Editor edit, final String key, final double value) {
        return edit.putLong(key, Double.doubleToRawLongBits(value));
    }

    public static double PrGetDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
        return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
    }
}