package com.example.mujuckyoo.a180309_homework;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int ITEM_SIZE = 30;
    List<Item> items;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        items = getContactList();

/*
        Item[] item = new Item[ITEM_SIZE];

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
        }
*/

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));


        String tel = "tel:" + getIntent().getStringExtra("phone_number");
        Log.d("calltest", "good");
        if (getIntent().getStringExtra("phone_number") != null) {
            startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
        }
    }

    protected void callPhone(String num) {

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String tel = "tel:" + getIntent().getStringExtra("phone_number");
        Log.d("calltest", "good");
        if (getIntent().getStringExtra("phone_number") != null) {
            startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
        }

    }

    private ArrayList<Item> getContactList() {


        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID, // 연락처 ID -> 사진 정보 가져오는데 사용
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,        // 연락처
                ContactsContract.CommonDataKinds.Phone.NUMBER }; // 연락처 이름.

        String[] selectionArgs = null;

        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC";

        Cursor contactCursor = getContentResolver().query(uri, projection,null,selectionArgs, sortOrder);
        /*
        Cursor contactCursor = managedQuery(uri, projection, null,
                selectionArgs, sortOrder);
*/
        ArrayList<Item> contactlist = new ArrayList<>();

/*
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        }
        phones.close();
        */
        if (contactCursor.moveToFirst()) {
            do {
                String phonenumber = contactCursor.getString(1).replaceAll("-",
                        "");
                Log.d("Test_Length", String.valueOf(phonenumber.length()));

                if (phonenumber.length() == 10) {
                    phonenumber = phonenumber.substring(0, 3) + "-"
                            + phonenumber.substring(3, 6) + "-"
                            + phonenumber.substring(6);
                } else if (phonenumber.length() > 8) {
                    phonenumber = phonenumber.substring(0, 3) + "-"
                            + phonenumber.substring(3, 7) + "-"
                            + phonenumber.substring(7);
                }

                Item acontact = new Item();
                acontact.setImage(contactCursor.getLong(0));
                acontact.setNum(phonenumber);
                Log.d("Test3",phonenumber);
                acontact.setName(contactCursor.getString(2));

                contactlist.add(acontact);
            } while (contactCursor.moveToNext());
        }

        return contactlist;

    }

}
