package com.firstapp.joel.walgreens.util.checkout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.model.CartList;

import java.util.ArrayList;

/**
 * Created by joel on 11/30/2017.
 */


public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> implements View.OnClickListener {

    private final ArrayList<CartList> cartList;
    Context context;
    private final LayoutInflater layoutInflater;
    Long Position;

//----------------------------------------------------------------------------------------------------------------------
    public MyCartAdapter(ArrayList<CartList> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
//-----------------------------------------------------------------------------------------------------------------------
    @Override
    public void onClick(View view) {

    }
//-----------------------------------------------------------------------------------------------------------------------

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
    }

//----------------------------------------------------------------------------------------------------------------------

    //define interface
    public interface OnRecyclerViewItemClickListener
    {
        void onItemClick(View view, String data);

    }

//-----------------------------------------------------------------------------------------------------------------------
    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView name;
        final TextView quantity;
        final TextView prize;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.cproduct_name);
            this.quantity = itemView.findViewById(R.id.cproduct_quantity);
            this.prize = itemView.findViewById(R.id.cproduct_prize);
            //this.description = itemView.findViewById(R.id.product_discription);
            this.linearLayout = (LinearLayout)itemView.findViewById(R.id.clinearLayout);
          //  this.Item_Id = itemView.findViewById(R.id.item_Id);
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items,null);
        view.setOnClickListener(this);
        return new MyCartAdapter.ViewHolder(view);
    }
//--------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(MyCartAdapter.ViewHolder holder, int position) {
        //  holder._Id.append(cartList.get(position).ID);
        //    holder.Item_Id.append(cartList.get(position).ItemID);
        final CartList myList = cartList.get(position);
        holder.name.setText(cartList.get(position).Name);
        holder.quantity.setText("Quantity: " + cartList.get(position).Quantity);
        holder.prize.setText("CartList: " + cartList.get(position).Price);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_alert);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.textView2CD);
                TextView text1 = (TextView) dialog.findViewById(R.id.textView1CD);

                Button remove = (Button) dialog.findViewById(R.id.removeCD);
                Button checkout = (Button) dialog.findViewById(R.id.checkoutCD);
                Button back = (Button) dialog.findViewById(R.id.backCD);

                // if button is clicked, close the custom dialog
                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                checkout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent inta = new Intent(context, ShopItems.class);
                        context.startActivity(inta);
                    }
                });
                dialog.show();
            }*/
                buy();
            }
        });
    }
//---------------------------------------------------------------------------------------------------------------
    private void buy() {
        Toast.makeText(context,"Checking out",Toast.LENGTH_SHORT).show();
    }
//---------------------------------------------------------------------------------------------------------------
    public void remove(){
        Toast.makeText(context, "Removing from cart",Toast.LENGTH_SHORT).show();

    }
//---------------------------------------------------------------------------------------------------------------
    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
