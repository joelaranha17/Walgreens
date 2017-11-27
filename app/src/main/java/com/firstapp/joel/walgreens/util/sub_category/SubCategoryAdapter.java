package com.firstapp.joel.walgreens.util.sub_category;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.model.SubCategoryList;
import com.firstapp.joel.walgreens.util.products.ProductItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by joel on 11/27/2017.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> implements View.OnClickListener{

    private final ArrayList<SubCategoryList> subCategoryList;
    Context context;
    private final LayoutInflater layoutInflater;
    Long Position;

    public SubCategoryAdapter(ArrayList<SubCategoryList> subCategoryList, Context context) {
        this.subCategoryList = subCategoryList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onClick(View view) {

    }

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
    }

    //define interface
    public interface OnRecyclerViewItemClickListener
    {
        void onItemClick(View view, String data);

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        final TextView _Id;
        final TextView name;
        final TextView description;
        final ImageView image;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this._Id = itemView.findViewById(R.id.subcategory_id);
            this.name = itemView.findViewById(R.id.subcategory_name);
            this.image = itemView.findViewById(R.id.subcategory_image);
            this.description = itemView.findViewById(R.id.subcategory_discription);
            this.linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategories_layout,null);
        view.setOnClickListener(this);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        final SubCategoryList myList = subCategoryList.get(position);
        holder._Id.setText(subCategoryList.get(position).Id);
        holder.name.setText(subCategoryList.get(position).SubCatagoryName);
        holder.description.setText(subCategoryList.get(position).SubCatagoryDiscription);
        holder.itemView.setTag(subCategoryList.get(position).Id);
        Picasso.with(context)
                .load(subCategoryList.get(position).CategoryImage)
                .into(holder.image);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String A = subCategoryList.get(position).SubCatagoryName;
              Log.d("YOYO","WORK:"+A);
              if(A.contains("Laptop")){
                  Intent gotoproduct = new Intent(context, ProductItems.class);
                  context.startActivity(gotoproduct);
              }

            }
        });
    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

}
