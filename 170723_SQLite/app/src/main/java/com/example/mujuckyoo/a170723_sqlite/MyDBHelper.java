package com.example.mujuckyoo.a170723_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.mujuckyoo.a170723_sqlite.LoginActivity.user_id;
import static com.example.mujuckyoo.a170723_sqlite.MainActivity.al;
import static com.example.mujuckyoo.a170723_sqlite.MainActivity.cb_desc;


/**
 * Created by mujuckyoo on 2017. 7. 23..
 */

public class MyDBHelper extends SQLiteOpenHelper {



    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists MONEYBOOK ( mb_id int AUTO_INCREMENT, user_id varchar(15), date varchar(15), " +
                "item varchar(255), price int, PRIMARY KEY(mb_id)); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String read_all() {

        SQLiteDatabase db = getReadableDatabase();


        String query = "select * from MONEYBOOK where user_id = '" + user_id + "' order by date desc;";


        if(!cb_desc.isChecked()) {

            query = "select * from MONEYBOOK where user_id = '" + user_id + "' order by date asc;";

        }
        //data를 0,0부터 가지고 온다
        Cursor cursor = db.rawQuery(query, null);

        String result = "";
        al.clear();

        while(cursor.moveToNext()) {

            result += String.format("%s : %s | %s\n",cursor.getString(1),cursor.getString(2),cursor.getString(3));

            al.add(new Spent_Item(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }

        return result;
    }

    public void insert_item(String date, String item, int price) {

        //insert into MONEYBOOK(date, item, price) VALUES ("

        SQLiteDatabase db = getWritableDatabase();

        String query = "insert into MONEYBOOK (user_id, date, item, price) VALUES ('" + user_id +  "','" + date +  "', '" + item + "', '" + price + "');";
        db.execSQL(query);

    }

    public void update_item(String item, int price) {


        SQLiteDatabase db = getWritableDatabase();

        String query = "update MONEYBOOK set price = " + price + "where item = '" + item +"' and where user_id = '" + user_id + "';";
        db.execSQL(query);

    }

    public void delete_item(String item) {


        SQLiteDatabase db = getWritableDatabase();

        String query = "delete from MONEYBOOK where item = '" + item + "' and where user_id = '" + user_id + "';";
        db.execSQL(query);


    }


}
