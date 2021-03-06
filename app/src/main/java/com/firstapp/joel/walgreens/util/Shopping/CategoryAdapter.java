package com.firstapp.joel.walgreens.util.Shopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.model.CategoryList;
import com.firstapp.joel.walgreens.util.sub_category.SubCategoryItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by joel on 11/26/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements View.OnClickListener{

    private final ArrayList<CategoryList> categoryList;
    private final Context context;
    private final LayoutInflater layoutInflater;
    Long Position;


    public CategoryAdapter(Context context, ArrayList<CategoryList> categoryList){
        layoutInflater = LayoutInflater.from(context);
        this.context=context;
        this.categoryList = categoryList;
    }

    //define interface
    public interface OnRecyclerViewItemClickListener
    {
        void onItemClick(View view, String data);

    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view,String.valueOf(view.getTag()));
        }
        else{
            Log.e("CLICK", "ERROR");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        final TextView _ID;
        final TextView name;
        final TextView description;
        final ImageView image;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this._ID = itemView.findViewById(R.id.category_id);
            this.name = itemView.findViewById(R.id.category_name);
            this.image = itemView.findViewById(R.id.category_image);
            this.description = itemView.findViewById(R.id.category_discription);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.llinearLayout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout,null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder._ID.setText(categoryList.get(position).ID);
        holder.name.setText(categoryList.get(position).CategoryName);
        holder.description.setText(categoryList.get(position).CategoryDiscription);
        holder.itemView.setTag(categoryList.get(position).ID);
        Picasso.with(context)
                .load(categoryList.get(position).CatagoryImage)
                .into(holder.image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String A = categoryList.get(position).CategoryName;
                String IDsC = categoryList.get(position).ID;
/*//                intent.putExtra("myKey", AnyValue);
  //              startActivity(intent);
                Log.d("YOYO","WORK:"+Cart);
                if(Cart.contains("Electronics")){*/
                Bundle b = new Bundle();

                b.putString("CategoryID", IDsC);
                Intent gotosub = new Intent(context, SubCategoryItems.class);
                gotosub.putExtras(b);
                context.startActivity(gotosub);
            }
        });
    }
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

}
