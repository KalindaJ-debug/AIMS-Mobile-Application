package Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.javamobileapplication.farmer_login;

import Classes.Users;

import java.util.ArrayList;
import java.util.List;

public class UserHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AIMS";

    public UserHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
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
        String QUERY1c = "INSERT INTO " +TableHandler.Users.TABLE_NAME+" VALUES('Ama','972502430v','farmer')";

        sqLiteDatabase.execSQL(QUERY1a);
        sqLiteDatabase.execSQL(QUERY1b);
        sqLiteDatabase.execSQL(QUERY1c);
        Log.d("STATE", "onCreate: is being executed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List readAllUsers() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                TableHandler.Users.COL_1,
                TableHandler.Users.COL_2,
                TableHandler.Users.COL_3
        };

        String sortOrder = TableHandler.Users.COL_1 + " DESC";


        Cursor cursor = db.query(
                TableHandler.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Users> users = new ArrayList<>();


        while(cursor.moveToNext()){

            String username = cursor.getString( cursor.getColumnIndexOrThrow(TableHandler.Users.COL_1));
            String password = cursor.getString( cursor.getColumnIndexOrThrow(TableHandler.Users.COL_2));

            Users user = new Users();
            user.setUsername(username);
            user.setPassword(password);
            users.add(user);

        }

        cursor.close();
        return users;

    }
}
