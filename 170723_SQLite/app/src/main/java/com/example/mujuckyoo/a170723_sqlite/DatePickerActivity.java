package com.example.mujuckyoo.a170723_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerActivity extends AppCompatActivity {

    DatePicker datePicker;
    String msg = "";
    Button btn_dateConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        datePicker = (DatePicker)findViewById(R.id.datePicker);
        btn_dateConfirm = (Button)findViewById(R.id.btn_dateConfirm);

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                // TODO Auto-generated method stub



                if(monthOfYear+1<10 && dayOfMonth<10) {

                    msg = String.format("%d-0%d-0%d", year,monthOfYear+1, dayOfMonth);

                } else if(monthOfYear+1<10 && dayOfMonth>=10) {

                    msg = String.format("%d-0%d-%d", year,monthOfYear+1, dayOfMonth);

                } else if(monthOfYear+1>=10 && dayOfMonth<10) {

                    msg = String.format("%d-%d-0%d", year,monthOfYear+1, dayOfMonth);

                }


                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

            }
        });


        btn_dateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultIntent = new Intent();

                resultIntent.putExtra("DATE", msg);

                setResult(1,resultIntent);

                finish();
            }
        });



    }
}
