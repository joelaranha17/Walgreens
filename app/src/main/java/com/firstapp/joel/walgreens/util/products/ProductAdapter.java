package com.firstapp.joel.walgreens.util.products;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.model.ProductsList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements View.OnClickListener{

    private final ArrayList<ProductsList> productsLists;
    Context context;
    private final LayoutInflater layoutInflater;
    Long Position;

    public ProductAdapter(ArrayList<ProductsList> productsLists, Context context) {
        this.productsLists = productsLists;
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
        final TextView quantity;
        final TextView prize;
        final ImageView image;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this._Id = itemView.findViewById(R.id.product_id);
            this.name = itemView.findViewById(R.id.product_name);
            this.image = itemView.findViewById(R.id.product_image);
            this.quantity = itemView.findViewById(R.id.product_quantity);
            this.prize = itemView.findViewById(R.id.product_prize);
            this.description = itemView.findViewById(R.id.product_discription);
            this.linearLayout = (LinearLayout)itemView.findViewById(R.id.lllinearLayout);
        }
    }
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout,null);
        view.setOnClickListener(this);
        return new ProductAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder holder, final int position) {
//        final SubCategoryList myList = subCategoryList.get(position);
        holder._Id.setText(productsLists.get(position).Id);
        holder.name.setText(productsLists.get(position).ProductName);
        holder.quantity.setText(productsLists.get(position).Quantity);
        holder.prize.setText(productsLists.get(position).Prize);
        holder.description.setText(productsLists.get(position).Discription);
        holder.itemView.setTag(productsLists.get(position).Id);
        Picasso.with(context)
                .load(productsLists.get(position).Image)
                .into(holder.image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String A = productsLists.get(position).ProductName;
                Log.d("YOYO","WORK:"+A);
                if(A.contains("Laptop")){

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return productsLists.size();
    }

}

