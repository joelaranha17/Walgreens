package com.firstapp.joel.walgreens.util.model;

/**
 * Created by joel on 11/24/2017.
 */

public class ProductsList {
    public final String Id;
    public final String ProductName;
    public final String Quantity;
    public final String Prize;
    public final String Discription;
    public final String Image;

    public ProductsList(String Id, String ProductName, String Quantity, String Prize, String Discription,String Image){
        this.Id = Id;
        this.ProductName = ProductName;
        this.Quantity =  Quantity;
        this.Prize = Prize;
        this.Discription = Discription;
        this.Image = Image;
    }
}
