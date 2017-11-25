package com.firstapp.joel.walgreens.util.model;

/**
 * Created by joel on 11/24/2017.
 */

public class ProductsList {
    public final String ID;
    public final String ProductName;
    public final String Quantity;
    public final String Price;
    public final String Description;
    public final String Image;

    public ProductsList(String ID, String ProductName, String Quantity, String Price, String Description,String Image){
        this.ID = ID;
        this.ProductName = ProductName;
        this.Quantity =  Quantity;
        this.Price = Price;
        this.Description = "Description: " + Description;
        this.Image = Image;
    }
}
