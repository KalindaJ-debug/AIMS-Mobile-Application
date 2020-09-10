package Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import Classes.Users;

import java.util.ArrayList;
import java.util.List;

public class UserHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AIMS.db";

    public UserHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

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
