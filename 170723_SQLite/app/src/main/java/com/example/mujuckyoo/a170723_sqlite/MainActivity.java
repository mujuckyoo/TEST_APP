package com.example.mujuckyoo.a170723_sqlite;

import android.content.Intent;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    //Edit Text대신 DatePicker로 해보기
    //context 는 관용어로 getApplication 을 쓴다는 것만 알아주다

    MyDBHelper dbHelper;
    EditText et_item;
    EditText et_date;
    EditText et_price;
    Button btn_add, btn_del, btn_mod, btn_list, btn_date;
    TextView txt_list;
    LinearLayout ll_all, ll_voucher;
    ScrollView sv_show;
    CheckBox cb_desc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDBHelper(getApplicationContext(),"moneybook.db", null, 1);

        et_item = (EditText) findViewById(R.id.et_item);
        et_price = (EditText) findViewById(R.id.et_price);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_mod = (Button) findViewById(R.id.btn_mod);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_date = (Button) findViewById(R.id.btn_date);
        et_date = (EditText) findViewById(R.id.et_date);
        txt_list = (TextView)findViewById(R.id.txt_list);
        txt_list.setText("");
        cb_desc = (CheckBox) findViewById(R.id.checkBox);
        ll_all = (LinearLayout)findViewById(R.id.ll_all);
        sv_show = (ScrollView)findViewById(R.id.sv_show);
        ll_voucher = (LinearLayout) View.inflate(MainActivity.this, R.layout.activity_voucher, null);



        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), DatePickerActivity.class);

                startActivityForResult(i, 1);


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        et_date.setText(String.valueOf(data.getExtras().getString("DATE")));
    }

    public void btn_clicked(View v) {

                if (et_date.getText().length() != 0 && et_item.getText().length() != 0 && et_price.getText().length() != 0) {

                    String date = et_date.getText().toString();
                    String item = et_item.getText().toString();
                    int price = Integer.parseInt(et_price.getText().toString());

                    switch (v.getId()) {
                        case R.id.btn_add:

                            dbHelper.insert_item(date, item, price);

                            sv_show.addView(ll_voucher);

                            txt_list.setText(dbHelper.read_all());
                            et_date.setText("");
                            et_item.setText("");
                            et_price.setText("");

                            break;
                        case R.id.btn_del:
                            dbHelper.delete_item(item);
                            txt_list.setText(dbHelper.read_all());

                            break;
                        case R.id.btn_mod:
                            dbHelper.update_item(item, price);
                            txt_list.setText(dbHelper.read_all());

                            break;
                        case R.id.btn_list:

                            txt_list.setText(dbHelper.read_all());
                            break;

                    }

                } else {

                    Toast.makeText(MainActivity.this, "정보가 부족합니다.", Toast.LENGTH_SHORT).show();
                }

            }

        }
