package com.interview.myfirebaseapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmailSignUpForm extends AppCompatActivity {

    EditText et1;
    EditText et2;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sign_up_form);



    }


    public void RegisterUser(View view)
    {
        et1=findViewById(R.id.editTextTextPersonName);
        et2=findViewById(R.id.editTextTextPersonName2);
        String txt_email=et1.getText().toString();
        String txt_password=et2.getText().toString();
        if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
            Toast.makeText(this, "Invalid Credential", Toast.LENGTH_SHORT).show();}
            else if(txt_password.length()<6)
            {
                Toast.makeText(this, "password tooo short", Toast.LENGTH_SHORT).show();

            }
            else
        {
            resgisterUser(txt_email,txt_password);
        }



    }

    private void resgisterUser(String email, String password)
    {
        auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(EmailSignUpForm.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(EmailSignUpForm.this, "Register SucessFully", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Toast.makeText(EmailSignUpForm.this, "Some thing went Wrong", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}