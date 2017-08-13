package com.example.mujuckyoo.a170723_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText et_ID, et_PW;
    Button btn_login, btn_signUp;

    LoginDBHelper loginDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginDBHelper = new LoginDBHelper(getApplicationContext(),"moneybook_user.db", null, 1);

        et_ID = (EditText) findViewById(R.id.et_ID);
        et_PW = (EditText) findViewById(R.id.et_PW);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signUp = (Button) findViewById(R.id.btn_SignUp);



    }

    public void btn_login_clicked(View v) {

        if(!loginDBHelper.login(et_ID.getText().toString(), et_PW.getText().toString())) {

            Toast.makeText(this, "ID/PW를 확인해주세요", Toast.LENGTH_SHORT).show();
            return;

        } else {

            Intent i = new Intent(this, MainActivity.class);

            startActivity(i);

        }
    }

    public void btn_signUp_clicked(View v) {

        if(!loginDBHelper.checkId(et_ID.getText().toString())) {

            Toast.makeText(this, "ID가 존재합니다", Toast.LENGTH_SHORT).show();
            return;
        }


        loginDBHelper.signUp(et_ID.getText().toString(), et_PW.getText().toString());
        Toast.makeText(this, "ID가 생성되었습니다", Toast.LENGTH_SHORT).show();


    }

}
