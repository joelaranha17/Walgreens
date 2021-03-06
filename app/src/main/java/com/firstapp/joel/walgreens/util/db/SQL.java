package com.firstapp.joel.walgreens.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joel on 11/24/2017.
 */

public class SQL extends SQLiteOpenHelper {

    private static final String DATABASE="ProductsDatabase";
    public static final String TABLE3="ProductsTable";
    //public static final String KEY_ID="Product_ID";
    public static final String NAME="Product_Name";
    public static final String PRICE="Product_Price";
    public static final String QUANTITY="Product_Quantity";
//    public static final String IMAGE="Product_Image";


    private static final int VERSION =1;

    public SQL (Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE3 + "("
                + NAME + " TEXT," + PRICE + " TEXT," + QUANTITY + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3);
        onCreate(sqLiteDatabase);
    }
}