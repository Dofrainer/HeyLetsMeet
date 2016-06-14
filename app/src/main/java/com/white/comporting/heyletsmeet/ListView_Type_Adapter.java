package com.white.comporting.heyletsmeet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Baek on 2016-04-17.
 */
public class ListView_Type_Adapter extends ArrayAdapter<ListView_Type_Data> {
    private Type_List type_list;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListView_Type_Data> items;

    public ListView_Type_Adapter(Type_List type_list, Context context, int textViewResourceId, ArrayList<ListView_Type_Data> items) {
        super(context, textViewResourceId, items);
        this.type_list = type_list;
        this.items = items;
    }

    // 아이템 추가
    public void addItem() {
        ListView_Type_Data Data = new ListView_Type_Data();
        items.add(Data);
        this.notifyDataSetChanged();
    }
    // 아이템 추가
    public ListView_Type_Data getItem(int intItem) {
        return  items.get(intItem);
    }

    //아이템 삭제
    public void removeItem(int position) {
        items.remove(position);
        this.notifyDataSetChanged();
    }
    //아이템 수 반환
    public  int CountItem()
    {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) type_list.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listview_type, null);
        }
        ListView_Type_Data p = items.get(position);

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
                    type_list.TypeAdapter.removeItem(position);
                    type_list.TypeAdapter.notifyDataSetChanged();
                }
            });


        }
        return v;
    }

}
