package com.example.javamobileapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class ApprovalDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public ApprovalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* Inner class that defines the table contents */
    public class Approval implements BaseColumns {
        public static final String TABLE_NAME = "approval";
        public static final String COLUMN_NAME_FARMER_FIRST_NAME = "farmer_first_name";
        public static final String COLUMN_NAME_FARMER_lAST_NAME = "farmer_last_name";
        public static final String COLUMN_NAME_DISTRICT = "district";
        public static final String COLUMN_NAME_REGION = "region";
        public static final String COLUMN_NAME_SUBMITTED_DATE = "submitedDate";
        public static final String COLUMN_NAME_HARVEST_AMOUNT = "harvestedAmount";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Approval.TABLE_NAME + " (" +
                    Approval._ID + " INTEGER PRIMARY KEY," +
                    Approval.COLUMN_NAME_FARMER_FIRST_NAME + " TEXT," +
                    Approval.COLUMN_NAME_FARMER_lAST_NAME + " TEXT," +
                    Approval.COLUMN_NAME_DISTRICT + " TEXT," +
                    Approval.COLUMN_NAME_REGION + " TEXT," +
                    Approval.COLUMN_NAME_SUBMITTED_DATE + " TEXT," +
                    Approval.COLUMN_NAME_HARVEST_AMOUNT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Approval.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
