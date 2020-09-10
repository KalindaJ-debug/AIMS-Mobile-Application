package com.example.javamobileapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Variable Declarations
//    Database
    public static final String DATABASE_NAME = "temp_aims.db";

//    Tables
    public static final String TABLE_DATA_ENTRY = "data_entry_table"; //data entry table
    public static final String TABLE_CROP_CATEGORY = "crop_category_table"; //crop category
    public static final String TABLE_CROP_NAME = "crop_table"; //crop name
    public static final String TABLE_CROP_VARIETY = "crop_variety_table"; //crop variety
    public static final String TABLE_LAND_TYPE = "land_type_table"; //land type
    public static final String TABLE_LAND_ADDRESS = "land_address_table"; //land address table

//    Columns
    //Table - Data Entry
    public static final String COL_1 = "id";
    public static final String COL_2 = "land_id";
    public static final String COL_3 = "crop_variety_id";
    public static final String COL_4 = "cultivated_land_extent";
    public static final String COL_5 = "land_type";

    //Table - Crop Category
    public static final String CC_COL_1 = "id";
    public static final String CC_COL_2 = "name";

    //Table - Crop Name
    public static final String C_COL_1 = "id";
    public static final String C_COL_2 = "name";
    public static final String C_COL_3 = "crop_category_id";

    //Table - Crop Variety
    public static final String CV_COL_1 = "id";
    public static final String CV_COL_2 = "name";
    public static final String CV_COL_3 = "crop_id";

    //Table - Land Type
    public static final String LT_COL_1 = "id";
    public static final String LT_COL_2 = "name";

    //Table - Land Address
    public static final String LA_COL_1 = "id";
    public static final String LA_COL_2 = "addressNo";
    public static final String LA_COL_3 = "street";
    public static final String LA_COL_4 = "lane";
    public static final String LA_COL_5 = "district";
    public static final String LA_COL_6 = "province";
    public static final String LA_COL_7 = "land_extent";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);//create database

    } //end of constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create land data entry table
        sqLiteDatabase.execSQL("create table " + TABLE_DATA_ENTRY + " (id INTEGER PRIMARY KEY AUTOINCREMENT, land_id INTEGER NOT NULL, crop_variety_id INTEGER NOT NULL, cultivated_land_extent DOUBLE NOT NULL, land_type TEXT)");//end of execSQL
        //create crop category table
        sqLiteDatabase.execSQL("create table " + TABLE_CROP_CATEGORY + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)");
        //create crop table
        sqLiteDatabase.execSQL("create table " + TABLE_CROP_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, crop_category_id INTEGER NOT NULL, FOREIGN KEY (crop_category_id) REFERENCES " + TABLE_CROP_CATEGORY + "(id))");
        //create crop variety table
        sqLiteDatabase.execSQL("create table " + TABLE_CROP_VARIETY + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, crop_id INTEGER NOT NULL, FOREIGN KEY (crop_id) REFERENCES " + TABLE_CROP_NAME + "(id))");
        //create land type table
        sqLiteDatabase.execSQL("create table " + TABLE_LAND_TYPE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)");
        //create land address table
        sqLiteDatabase.execSQL("create table " + TABLE_LAND_ADDRESS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, addressNo TEXT NOT NULL, street TEXT, lane TEXT NOT NULL, district TEXT NOT NULL, province TEXT NOT NULL, land_extent DOUBLE NOT NULL)");

    } //end of on create method

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA_ENTRY);
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CROP_CATEGORY);
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CROP_NAME);
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CROP_VARIETY);
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LAND_TYPE);
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LAND_ADDRESS);

     onCreate(sqLiteDatabase);

    } //end of on upgrade method

    //data seeder methods
    //crop category table - data seeder method
    public boolean seedCropCategory(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); //write to database

        ArrayList<String> crop_categories = new ArrayList<String>();
        crop_categories.add("Vegetables");
        crop_categories.add("Fruits");
        crop_categories.add("Other Field Crops");
        crop_categories.add("Leafy Vegetables");
        crop_categories.add("Roots and Tubers");

        boolean endResult = true;

        for (String item: crop_categories ) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CC_COL_2, item);
            long result = sqLiteDatabase.insert(TABLE_CROP_CATEGORY, null, contentValues);
            if(result == -1){
                //failed to insert value
                endResult = false;
            }
        } //end of foreach

        return endResult;

    }//end of insert crop category

    //data seeder for crop names
    public boolean seedCrop(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); //write to database

        //array - vegetables
        ArrayList<String> crops_vegetables = new ArrayList<String>();
        crops_vegetables.add("Brinjol");
        crops_vegetables.add("Tomato");
        crops_vegetables.add("Snake Gourd");
        crops_vegetables.add("Capsicum");

        //array - fruits
        ArrayList<String> crops_fruits = new ArrayList<String>();
        crops_fruits.add("Apple");
        crops_fruits.add("Mango");
        crops_fruits.add("Pineapple");

        //array - ofc
        ArrayList<String> crops_ofc = new ArrayList<String>();
        crops_ofc.add("Cow Pea");
        crops_ofc.add("Green Gram");

        //array - roots and tubers
        ArrayList<String> crops_roots = new ArrayList<String>();
        crops_roots.add("Potato");
        crops_roots.add("Sweet Potato");
        crops_roots.add("Turmeric");

        //array - leafy vegetables
        ArrayList<String> crops_leafy = new ArrayList<String>();
        crops_leafy.add("Spinach");
        crops_leafy.add("Lettuce");

        boolean result = true;

        //foreach loop - vegetables
        for (String item: crops_vegetables) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 1);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        //foreach loop - fruits
        for (String item: crops_fruits) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 2);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        //foreach loop - ofc
        for (String item: crops_ofc) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 3);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        //foreach loop - leafy
        for (String item: crops_leafy) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 4);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        //foreach loop - roots
        for (String item: crops_roots) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 5);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        return result; //return boolean value

    }//end of insert crop

    //seeder - crop variety
    public boolean seedCropVariety(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); //write to database
        boolean result = true;

        //array for vegetable varieties

        //brinjol
        ArrayList<String> brinjol = new ArrayList<String>();
        brinjol.add("Anjalee");
        brinjol.add("Amanda");
        brinjol.add("SM-64");

        //tomato
        ArrayList<String> tomato = new ArrayList<String>();
        tomato.add("T-146");
        tomato.add("Maheshi");

        //snakegourd
        ArrayList<String> snakegourd = new ArrayList<String>();
        snakegourd.add("TA-2");
        snakegourd.add("M1-Short");

        //capsicum
        ArrayList<String> capsicum = new ArrayList<String>();
        capsicum.add("Hungarian Yellow Wax");
        capsicum.add("CA-8");

        //array fruit varities
        //apple
        ArrayList<String> apple = new ArrayList<String>();
        apple.add("ANK-1");
        apple.add("ANK-2");

        //mango
        ArrayList<String> mango = new ArrayList<String>();
        mango.add("Willard");
        mango.add("Karutha Columnban");

        //pineapple
        ArrayList<String> pineapple = new ArrayList<String>();
        pineapple.add("Cayenne ");
        pineapple.add("Queen");

        //array for ofc varieties
        //cowpea
        ArrayList<String> cowpea = new ArrayList<String>();
        cowpea.add("M1-35 ");
        cowpea.add("Maize-Bombay");

        //green gram
        ArrayList<String> greengram = new ArrayList<String>();
        greengram.add("M1-1");
        greengram.add("Harsha");

        //array for leafy
        //spinach
        String spinachV = "Malabra Spinach";

        //lettuce
        ArrayList<String> lettuce = new ArrayList<String>();
        lettuce.add("Red Oak Leaf");
        lettuce.add("Blonde Oak Leaf");
        lettuce.add("Red Butterhead Leaf");
        lettuce.add("Little Gem Leaf");

        //array for roots and tubers
        //potato
        ArrayList<String> potato = new ArrayList<String>();
        potato.add("Hillstar");
        potato.add("Granola");
        potato.add("Sita");
        potato.add("Krushi");

        //sweet potato
        ArrayList<String> sweetpotato = new ArrayList<String>();
        sweetpotato.add("CARI-9");
        sweetpotato.add("Wariyapola-white");
        sweetpotato.add("Ranabima");
        sweetpotato.add("BW-21");

        //turmeric
        ArrayList<String> turmeric = new ArrayList<String>();
        turmeric.add("Gunter");
        turmeric.add("Puna");
        turmeric.add("Madurasi Majal");

        //insert to table

        //foreach loops
        //vegetables
        //brinjol
        for (String item: brinjol) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 1); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop

        //tomato
        for (String item: tomato) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 2); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop

        //snakegourd
        for (String item: snakegourd) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 3); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop

        //capsicum
        for (String item: capsicum) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 4); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop

        //fruits
        //apple
        for (String item: apple) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 5); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop
        //mango
        for (String item: mango) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 6); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop
        //pineapple
        for (String item: pineapple) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 7); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop

        //ofc
        //cow pea
        for (String item: cowpea) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 8); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop
        //green gram
        for (String item: greengram) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 9); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop

        //leafy
        //spinach
        ContentValues cv = new ContentValues();
        cv.put(CV_COL_2, spinachV);
        cv.put(CV_COL_3, 10);
        long res = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, cv);
        if(res == -1){
            result = false;
        }
        //lettuce
        for (String item: lettuce) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 11); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop

        //roots
        //potato
        for (String item: potato) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 12); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop
        //sweet potato
        for (String item: sweetpotato) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 13); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop
        //turmeric
        for (String item: turmeric) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CV_COL_2, item); //name
            contentValues.put(CV_COL_3, 14); //crop id
            long r = sqLiteDatabase.insert(TABLE_CROP_VARIETY, null, contentValues);
            if(r == -1){
                result = false;
            }
        } //end of foreach loop

        return result;
    }//end of seed crop variety

    //seed land type table
    public boolean seedLandType(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); //write to database
        boolean result = true;

        //array
        ArrayList<String> landType = new ArrayList<String>();
        landType.add("Reddish Brown Earths");
        landType.add("Low Humic Gley");
        landType.add("Non-Calcic Brown");
        landType.add("Red and Yellow Latasols");
        landType.add("Immature Brown Loams");
        landType.add("Solodized Solonetz");
        landType.add("Grumusols");
        landType.add("Red Yellow Podzolic");
        landType.add("Reddish Brown Latasol");
        landType.add("Alluvials");
        landType.add("Regosols");
        landType.add("Bog and Half Bog");
        landType.add("Lithosols");

        //seed data - foreach loop
        for (String item: landType) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(LT_COL_2, item);
            long q = sqLiteDatabase.insert(TABLE_LAND_TYPE, null, contentValues);
            if (q == -1){
                result =  false;
            }//end of if
        } //end of foreach loop

        return result;
    }//end of seed land type

    //data seeder - land address
    public boolean seedLandAddress(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); //write to database
        boolean result = true;
        //land address 1
        ContentValues contentValues = new ContentValues();
        contentValues.put(LA_COL_2, "17/A");
        contentValues.put(LA_COL_3, "2nd Street");
        contentValues.put(LA_COL_4, "2nd Lane");
        contentValues.put(LA_COL_5, "Colombo");
        contentValues.put(LA_COL_6, "Western");
        contentValues.put(LA_COL_7, 125.21);
        long deck = sqLiteDatabase.insert(TABLE_LAND_ADDRESS, null, contentValues);
        if(deck == -1){
            result = false;
        }
        //land address 2
        ContentValues cv = new ContentValues();
        cv.put(LA_COL_2, "21B");
        cv.put(LA_COL_3, "Araliya Street");
        cv.put(LA_COL_4, "Mahagasthota Lane");
        cv.put(LA_COL_5, "Gampaha");
        cv.put(LA_COL_6, "Western");
        cv.put(LA_COL_7, 300.1);
        long peck = sqLiteDatabase.insert(TABLE_LAND_ADDRESS, null, cv);
        if(peck == -1){
            result = false;
        }
        return result;
    }//end of land address seeder

} //end of database helper class
