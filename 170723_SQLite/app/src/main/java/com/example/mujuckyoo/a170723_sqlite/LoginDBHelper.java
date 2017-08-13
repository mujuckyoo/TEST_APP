package com.example.mujuckyoo.a170723_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mujuckyoo on 2017. 7. 30..
 */

public class LoginDBHelper extends SQLiteOpenHelper {
    private static final String TAG = LoginDBHelper.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Login table name
    private static final String TABLE_USER = "USER_INFO";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PASSWORD = "passwd";


    public LoginDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOGIN_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER +
                " (user_id int AUTO_INCREMENT, " +
                KEY_ID + " varchar(15) unique, " +
                KEY_PASSWORD + " varchar(255), PRIMARY KEY(user_id));";

        db.execSQL(CREATE_LOGIN_TABLE);
        /*
        db.execSQL("create table if not exists USER_INFO ( " +
                "user_id int AUTO_INCREMENT," +
                "id varchar(15) unique, " +
                "passwd varchar(255), PRIMARY KEY(user_id)); ");
*/


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    public void signUp(String id, String pw) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id); // ID
        values.put(KEY_PASSWORD, pw); // Email

        long user = db.insert(TABLE_USER, null, values);
        Log.d(TAG, "New user inserted into sqlite: " + user);

    }

    public boolean checkId(String id) {

        SQLiteDatabase db = getReadableDatabase();


        String query ="select count(*) from USER_INFO where id = '" + id + "';";

        db.rawQuery(query, null);

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        Log.d("USER_INFO", cursor.getString(0));

        if(cursor.getInt(0) == 0) {

            return true;
        } else {

            return  false;
        }

    }

    public boolean login(String id, String pw) {

        // select count(*) from USER_INFO where id = 'asdf' and password ='4321';

        SQLiteDatabase db = getReadableDatabase();

        String query ="select count(*) from USER_INFO where id = '" + id + "' and passwd = '" + pw + "';";

        db.rawQuery(query, null);

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        Log.d("USER_INFO", cursor.getString(0));

        if(cursor.getInt(0) == 1) {

            return true;
        } else {

            return false;
        }


    }

}
