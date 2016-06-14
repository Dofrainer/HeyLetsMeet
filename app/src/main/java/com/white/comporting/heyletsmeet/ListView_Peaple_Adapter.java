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
    private Select_Data select_data;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListView_Peaple_Data> items;

    public ListView_Peaple_Adapter(Select_Data select_data, Context context, int textViewResourceId, ArrayList<ListView_Peaple_Data> items) {
        super(context, textViewResourceId, items);
        this.select_data = select_data;
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

    //아이템 문자값 추가
    public void SetItem(int position,String strAdd) {
        items.get(position).setAddress(strAdd);
        this.notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) select_data.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listview_peaple, null);
        }
        ListView_Peaple_Data p = items.get(position);

        if (p != null) {
            TextView Adress = (TextView) v.findViewById(R.id.TextLocation);
            Adress.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = select_data.getSharedPreferences("Location",select_data.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("pos", position);
                    editor.commit();
                    select_data.startActivity(new Intent(select_data.getApplicationContext(),Location_Select.class));

                }
            });




            Button BtnRemove = (Button) v.findViewById(R.id.BtnDelPeople);
            BtnRemove.setTag(position);
            BtnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) view.getTag();
                    select_data.peapleAdapter.removeItem(position);
                    select_data.Data.get(position).RemoveData();
                }
            });

            if (Adress != null) {
                Adress.setText(p.getAddress());
            }
        }
        return v;
    }



}
