package com.firstapp.joel.walgreens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by joel on 11/13/2017.
 */

public class MyAdapter extends BaseAdapter {
    String[] myitems;
    int[] myimages;
    Context context;

    LayoutInflater myInflater;

    public MyAdapter(String[] myitems, int[] myimages, Context context) {
        this.myitems = myitems;
        this.myimages = myimages;
        this.context = context;
        myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public int getCount() {
        return myitems.length;
    }

    @Override
    public Object getItem(int postion) {
        return postion;
    }

    @Override
    public long getItemId(int id) {
        return id;
    }


    public static class MyViewHolder {
        ImageView iv;
        TextView tv;
    }


    @Override
    public View getView(final int postion, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = new MyViewHolder();
        if (view == null) {
            view = myInflater.inflate(R.layout.item_layout, null);
            myViewHolder.iv = (ImageView) view.findViewById(R.id.imageViewItem);
            myViewHolder.tv = (TextView) view.findViewById(R.id.textViewItem);
            view.setTag(myViewHolder);
        } else {

            myViewHolder = (MyViewHolder) view.getTag();
        }
        myViewHolder.tv.setText(myitems[postion]);
        myViewHolder.iv.setImageResource(myimages[postion]);

        myViewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = myitems[postion];
                Toast.makeText(context, "item clicked " + item, Toast.LENGTH_SHORT).show();

            }
        });
        myViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = myitems[postion];
                //Intent intent = new Intent(context, SecondActivity.class);
                Toast.makeText(context, "item clicked " + item, Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }
}