package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "AIMS.db";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String QUERY1 = "CREATE TABLE " + TableHandler.Users.TABLE_NAME + " ( " +
                TableHandler.Users.COL_1 + " VARCHAR(20) PRIMARY KEY, " +
                TableHandler.Users.COL_2+ " VARCHAR(30) , " +
                TableHandler.Users.COL_3+ " VARCHAR(20) ) ";

        sqLiteDatabase.execSQL(QUERY1);

        String QUERY1a = "INSERT INTO " +TableHandler.Users.TABLE_NAME+" VALUES('Rishi','password','admin')";
        String QUERY1b = "INSERT INTO " +TableHandler.Users.TABLE_NAME+" VALUES('Nelaka','password','FO')";

        sqLiteDatabase.execSQL(QUERY1a);
        sqLiteDatabase.execSQL(QUERY1b);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
