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
import  com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.sub_category.SubCategoryItems;
import com.firstapp.joel.walgreens.util.four.Fourth;
import com.firstapp.joel.walgreens.util.login.LoginActivity;
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
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walgreens.com/pharmacy/rxlanding.jsp"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
                    context.startActivity(browserChooserIntent);

                    /*Intent intent1 = new Intent(context, LoginActivity.class);
                    context.startActivity(intent1);*/
                }
                else if(item.equals("Shop Products")) {
                    Intent intent2 = new Intent(context, ShopItems.class);
                    context.startActivity(intent2);
                }
                else if(item.equals("Photo")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://photo.walgreens.com/store/welcome"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
                    context.startActivity(browserChooserIntent);
                }
                else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walgreens.com/topic/offers/weeklyad-and-offers.jsp"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
                    context.startActivity(browserChooserIntent);
                }
                    Toast.makeText(context, "item clicked " + item, Toast.LENGTH_SHORT).show();
                }
        });

        myViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = myitems[postion];
                if(item.equals("Prescriptions & Health")) {
                    Intent intent1 = new Intent(context, LoginActivity.class);
                    context.startActivity(intent1);
                }
                else if(item.equals("Shop Products")) {
                    Intent intent2 = new Intent(context, ShopItems.class);
                    context.startActivity(intent2);
                }
                else if(item.equals("Photo")) {
                    Intent intent3 = new Intent(context, Fourth.class);
                    context.startActivity(intent3);
                }
                else {
                    Intent intent4 = new Intent(context, SubCategoryItems.class);
                    context.startActivity(intent4);
                }
                Toast.makeText(context, "item clicked " + item, Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }
}