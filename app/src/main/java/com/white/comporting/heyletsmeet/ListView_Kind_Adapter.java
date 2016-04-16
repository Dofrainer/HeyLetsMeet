package com.white.comporting.heyletsmeet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Baek on 2016-04-17.
 */
public class ListView_Kind_Adapter extends ArrayAdapter<ListView_Kind_Data> {
    private Select_Data select_data;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListView_Kind_Data> items;

    public ListView_Kind_Adapter(Select_Data select_data, Context context, int textViewResourceId, ArrayList<ListView_Kind_Data> items) {
        super(context, textViewResourceId, items);
        this.select_data = select_data;
        this.items = items;
    }

    // 아이템 추가
    public void addItem() {
        ListView_Kind_Data Data = new ListView_Kind_Data();
        items.add(Data);
        this.notifyDataSetChanged();
    }

    //아이템 삭제
    public void removeItem(int position) {
        items.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) select_data.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listview_kind, null);
        }
        ListView_Kind_Data p = items.get(position);

        if (p != null) {

            Spinner Kind = (Spinner)v.findViewById(R.id.spinSelectKind);
            Kind.setSelection(items.get(position).getKind());
            Kind.setTag(position);
            Kind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int ListPosition = (int)parent.getTag();
                    items.get(ListPosition).setKind(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });

            Button BtnRemove = (Button) v.findViewById(R.id.btnDelKind);
            BtnRemove.setTag(position);
            BtnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = (int) view.getTag();
                    select_data.kindAdapter.removeItem(position);
                    select_data.kindAdapter.notifyDataSetChanged();
                }
            });


        }
        return v;
    }

}
