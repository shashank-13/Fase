package com.faseapp.faseapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpClient extends AppCompatActivity {
    Button button;
    @BindView(R.id.editTextPhoneNo)
    TextInputLayout phnno;

    @BindView(R.id.editTextPassword)
    TextInputLayout password;
    @BindView(R.id.editTextPhone)
    EditText phn;
    @BindView(R.id.editTextPassword_signup)
    EditText pass;
    TextWatcher watch=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.toString().trim().length()>0){
                phnno.setError(null);
                password.setError(null);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up_client);
        ButterKnife.bind(this);
        phn.addTextChangedListener(watch);
        pass.addTextChangedListener(watch);
        button= (Button) findViewById(R.id.buttonContinue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=true;
                if(phnno.getEditText().getText().toString().replaceAll("\\s+","").length()!=10) {
                    phnno.setError("Invalid phone no should be 10 digits");
                    flag = false;
                }
                 if(password.getEditText().getText().toString().replaceAll("\\s+","").length()<6) {
                     password.setError("Invalid password length should be atleast 6");
                     flag = false;
                 }
              if(flag){
                    startActivity(new Intent(SignUpClient.this, OtpClient.class));
                    finish();
                }
            }
        });
    }
}
