package com.firstapp.joel.walgreens.util.model;

/**
 * Created by joel on 11/29/2017.
 */

public class CartList {

  //  public final String ID;
    public final String Name;
    public final String Quantity;
    public final String Price;

    public CartList(String productname, String quantity, String price) {
        Name = productname;
        Quantity = quantity;
        Price = price;
    }
}
