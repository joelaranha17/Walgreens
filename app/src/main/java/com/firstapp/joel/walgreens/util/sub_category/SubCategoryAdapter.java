package com.firstapp.joel.walgreens.util.sub_category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.model.SubCategoryList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by joel on 11/27/2017.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> implements View.OnClickListener{

    private final ArrayList<SubCategoryList> subCategoryList;
    Context context;
    private final LayoutInflater layoutInflater;

    public SubCategoryAdapter(ArrayList<SubCategoryList> subCategoryList, Context context) {
        this.subCategoryList = subCategoryList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    //define interface
    public interface OnRecyclerViewItemClickListener
    {
        void onItemClick(View view, String data);
    }

    private com.firstapp.joel.walgreens.util.Shopping.CategoryAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(com.firstapp.joel.walgreens.util.Shopping.CategoryAdapter.OnRecyclerViewItemClickListener listener) {
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

        final TextView _Id;
        final TextView name;
        final TextView description;
        final ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            this._Id = itemView.findViewById(R.id.subcategory_id);
            this.name = itemView.findViewById(R.id.subcategory_name);
            this.image = itemView.findViewById(R.id.subcategory_image);
            this.description = itemView.findViewById(R.id.subcategory_discription);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategories_layout,null);
        view.setOnClickListener(this);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder._Id.setText(subCategoryList.get(position).Id);
        holder.name.setText(subCategoryList.get(position).SubCatagoryName);
        holder.description.setText(subCategoryList.get(position).SubCatagoryDiscription);
//        holder.itemView.setTag(subCategoryList.get(position).Id);
        Picasso.with(context)
                .load(subCategoryList.get(position).CategoryImage)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

}
