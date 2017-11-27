package com.firstapp.joel.walgreens.util.model;

/**
 * Created by joel on 11/26/2017.
 */

/**
 * Created by joel on 11/24/2017.
 */

public class CategoryList
{
    public final String ID;
    public final String CategoryName;
    public final String CategoryDiscription;
    public final String CatagoryImage;

    public CategoryList(String ID, String categoryName, String categoryDiscription, String catagoryImage) {
        this.ID = ID;
        CategoryName = categoryName;
        CategoryDiscription = categoryDiscription;
        CatagoryImage = catagoryImage;
    }
}
