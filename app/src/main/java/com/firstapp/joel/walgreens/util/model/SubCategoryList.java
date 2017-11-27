package com.firstapp.joel.walgreens.util.model;

/**
 * Created by joel on 11/27/2017.
 */

public class SubCategoryList {
    public final String Id;
    public final String SubCatagoryName;
    public final String SubCatagoryDiscription;
    public final String CategoryImage;

    public SubCategoryList(String id, String subCatagoryName, String subCatagoryDiscription, String categoryImage) {
        Id = id;
        SubCatagoryName = subCatagoryName;
        SubCatagoryDiscription = subCatagoryDiscription;
        CategoryImage = categoryImage;
    }
}
