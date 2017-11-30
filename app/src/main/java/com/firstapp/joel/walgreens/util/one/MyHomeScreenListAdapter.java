package com.firstapp.joel.walgreens.util.one;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.Shopping.ShopItems;

/**
 * Created by joel on 11/13/2017.
 */

public class MyHomeScreenListAdapter extends BaseAdapter {
    String[] myitems;
    int[] myimages;
    Context context;

    LayoutInflater myInflater;

    public MyHomeScreenListAdapter(String[] myitems, int[] myimages, Context context) {
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
                if(item.equals("Prescriptions & Health")) {
                    prescriptionsHealth();
                    /*Intent intent1 = new Intent(context, LoginActivity.class);
                    context.startActivity(intent1);*/
                }
                else if(item.equals("Shop Products")) {
                    shop();
                }
                else if(item.equals("Photo")) {
                    photo();
                }
                else {
                    ads();
                }
                    Toast.makeText(context, "" + item, Toast.LENGTH_SHORT).show();
                }
        });

        myViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = myitems[postion];
                if(item.equals("Prescriptions & Health")) {
                    prescriptionsHealth();
                }
                else if(item.equals("Shop Products")) {
                    shop();
                }
                else if(item.equals("Photo")) {
                    photo();
                }
                else {
                    ads();
                }
                Toast.makeText(context, "" + item, Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }

    public void prescriptionsHealth(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walgreens.com/pharmacy/rxlanding.jsp"));
        Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
        context.startActivity(browserChooserIntent);
    }
    public void photo(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walgreens.com/store/welcome"));
        Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
        context.startActivity(browserChooserIntent);
    }
    public void ads(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walgreens.com/topic/offers/weeklyad-and-offers.jsp"));
        Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
        context.startActivity(browserChooserIntent);
    }
    public void shop() {
        Intent intent2 = new Intent(context, ShopItems.class);
        context.startActivity(intent2);
    }
}