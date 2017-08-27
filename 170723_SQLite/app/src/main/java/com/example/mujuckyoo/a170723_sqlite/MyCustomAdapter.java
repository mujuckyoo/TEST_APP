package com.example.mujuckyoo.a170723_sqlite;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mujuckyoo on 2017. 8. 13..
 */

public class MyCustomAdapter extends BaseAdapter {

    Context context;

    ArrayList<Spent_Item> al;
    LayoutInflater inflater;

    public MyCustomAdapter(Context context, ArrayList<Spent_Item> arrays) {
        this.context = context;
        this.al = arrays;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return al.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LinearLayout item_view = (LinearLayout) inflater.inflate(R.layout.item_layout, parent, false);
//인플레이트는 메모리에만 올리는 애임
        TextView time = (TextView) item_view.findViewById(R.id.time);
        TextView title = (TextView) item_view.findViewById(R.id.title);
        TextView writer = (TextView) item_view.findViewById(R.id.writer);

        time.setText(al.get(position).getTime());
        title.setText(al.get(position).getTitle());
        writer.setText(al.get(position).getWirter());

        return item_view;
    }

    public void setArrayList(ArrayList<Spent_Item> arrays){
        this.al = arrays;
    }

    public ArrayList<Spent_Item> getArrayList(){
        return al;
    }


}
