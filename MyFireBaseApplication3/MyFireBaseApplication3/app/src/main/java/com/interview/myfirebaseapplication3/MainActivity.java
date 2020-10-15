package com.interview.myfirebaseapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void SingnUpMethod(View view)
    {
     startActivity(new Intent(MainActivity.this,EmailSignUpForm.class));
    }

    public void EmailMethodlogin(View view)
    {
        et1=findViewById(R.id.UserName);
        et2=findViewById(R.id.UserName2);
        String txt_email=et1.getText().toString();
        String txt_pasword=et2.getText().toString();
        if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_pasword)){
            Toast.makeText(this, "Invalid Credential", Toast.LENGTH_SHORT).show();}
        else if(txt_pasword.length()<6)
        {
            Toast.makeText(this, "password sort", Toast.LENGTH_SHORT).show();

        }
        else
        {
            loginUser(txt_email,txt_pasword);
        }
    }

    private void loginUser(String email, String pasword)
    {
        auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,pasword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override

            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this, "login sucess", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,DashBord.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(MainActivity.this, "Failed :"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }

    public void PhoneNUmberMethod(View view)
    {
        startActivity(new Intent(MainActivity.this,PhoneNumberVarification.class));
    }

    public void GooogleSignIn(View view)
    {
        startActivity(new Intent(MainActivity.this,GooogleSignIn.class));
    }



    public void FBMethodLogin(View view)
    {
        Toast.makeText(this, "fb responce", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,ActiveFacebook.class));
    }
}