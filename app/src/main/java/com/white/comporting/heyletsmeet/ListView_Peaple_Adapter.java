package com.white.comporting.heyletsmeet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Baek on 2016-04-17.
 */
public class ListView_Peaple_Adapter extends ArrayAdapter<ListView_Peaple_Data> {
    private Location_List locationList;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListView_Peaple_Data> items;

    public ListView_Peaple_Adapter(Location_List locationList, Context context, int textViewResourceId, ArrayList<ListView_Peaple_Data> items) {
        super(context, textViewResourceId, items);
        this.locationList = locationList;
        this.items = items;
    }

    // 아이템 추가
    public void addItem() {
        ListView_Peaple_Data Data = new ListView_Peaple_Data();
        items.add(Data);
        this.notifyDataSetChanged();
    }

    //아이템 삭제
    public void removeItem(int position) {
        items.remove(position);
        this.notifyDataSetChanged();
    }

    //아이템 주소값 바꾸기
    public void SetItem(int position,String strAdd) {
        items.get(position).setAddress(strAdd);
        this.notifyDataSetChanged();
    }

    //아이템 수 반환
    public  int CountItem()
    {
        return items.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) locationList.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listview_peaple, null);
        }
        ListView_Peaple_Data p = items.get(position);

        if (p != null) {
            TextView Adress = (TextView) v.findViewById(R.id.TextLocation);
            Adress.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = locationList.getSharedPreferences("Location", locationList.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("pos", position);
                    editor.commit();
                    locationList.startActivity(new Intent(locationList.getApplicationContext(),Location_Select.class));

                }
            });




            Button BtnRemove = (Button) v.findViewById(R.id.BtnDelPeople);
            BtnRemove.setTag(position);
            BtnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) view.getTag();
                    locationList.peapleAdapter.removeItem(position);
                    locationList.LocDataArray.get(position).RemoveData();
                }
            });

            if (Adress != null) {
                Adress.setText(p.getAddress());
            }
        }
        return v;
    }



}
