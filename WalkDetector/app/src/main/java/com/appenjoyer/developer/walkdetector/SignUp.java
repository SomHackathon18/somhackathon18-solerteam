package com.appenjoyer.developer.walkdetector;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Calendar;

public class SignUp extends AppCompatActivity {

    EditText etName,etSurname,etAddress,etTelf,etPassword,etBirthday,etMail;
    RadioButton rbGender;
    RadioGroup rgGender;
    TextView tvLinkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etName = (EditText) findViewById(R.id.input_name);
        etSurname = (EditText) findViewById(R.id.input_surname);
        etAddress = (EditText) findViewById(R.id.input_address);
        etTelf = (EditText) findViewById(R.id.input_telefon);
        etPassword = (EditText) findViewById(R.id.input_name);
        etMail = (EditText) findViewById(R.id.input_email);
        rgGender = (RadioGroup) findViewById(R.id.gender_radiogroup);
        rbGender = (RadioButton)  rgGender.getChildAt(rgGender.getCheckedRadioButtonId());
        etBirthday = (EditText) findViewById(R.id.input_bday);
        tvLinkLogin = (TextView)findViewById(R.id.link_login);
        tvLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),LoginPage.class));
            }
        });




        Button btn = findViewById(R.id.btn_signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiCallsInsertUser(v.getContext(),new UserPOJO(etName.getText().toString(),
                        etSurname.getText().toString(),
                        etAddress.getText().toString(),
                        etTelf.getText().toString(),
                        etMail.getText().toString(),
                        etPassword.getText().toString(),
                        "home",
                        "2017-01-27T10:24")).execute("");


            }
        });
    }

}
