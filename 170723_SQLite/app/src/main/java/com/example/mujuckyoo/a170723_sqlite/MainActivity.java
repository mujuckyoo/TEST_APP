package com.example.mujuckyoo.a170723_sqlite;

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

public class MainActivity extends AppCompatActivity {


    //Edit Text대신 DatePicker로 해보기
    //context 는 관용어로 getApplication 을 쓴다는 것만 알아주다

    MyDBHelper dbHelper;
    DatePicker datepicker;
    EditText et_item;
    EditText et_date;
    EditText et_price;
    Button btn_add, btn_del, btn_mod, btn_list, btn_date;
    TextView txt_list;
    LinearLayout ll_all, ll_voucher;
    ScrollView sv_show;

    public static CheckBox cb_desc;



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
        datepicker = (DatePicker)findViewById(R.id.datePicker);
        ll_all = (LinearLayout)findViewById(R.id.ll_all);
        sv_show = (ScrollView)findViewById(R.id.sv_show);
        ll_voucher = (LinearLayout) View.inflate(MainActivity.this, R.layout.activity_voucher, null);



        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datepicker.setVisibility(View.VISIBLE);
                ll_all.setVisibility(View.INVISIBLE);
                datepicker.init(datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth(),
                        new DatePicker.OnDateChangedListener() {

                            @Override
                            public void onDateChanged(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                // TODO Auto-generated method stub

                                String msg = "";

                                if(monthOfYear+1<10 && dayOfMonth<10) {

                                    msg = String.format("%d-0%d-0%d", year,monthOfYear+1, dayOfMonth);

                                } else if(monthOfYear+1<10 && dayOfMonth>=10) {

                                    msg = String.format("%d-0%d-%d", year,monthOfYear+1, dayOfMonth);

                                } else if(monthOfYear+1>=10 && dayOfMonth<10) {

                                    msg = String.format("%d-%d-0%d", year,monthOfYear+1, dayOfMonth);

                                }


                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                                final String finalMsg = msg;

                                btn_date.setVisibility(View.VISIBLE);
                                btn_date.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        et_date.setText(finalMsg);
                                        datepicker.setVisibility(View.INVISIBLE);
                                        ll_all.setVisibility(View.VISIBLE);
                                        btn_date.setVisibility(View.INVISIBLE);

                                    }
                                });
                            }

                        });

            }
        });



    }

    public void btn_clicked(View v) {

        if(et_date.getText().length()!=0 && et_item.getText().length()!=0 && et_price.getText().length()!=0) {

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
                    dbHelper.update_item(item,price);
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
