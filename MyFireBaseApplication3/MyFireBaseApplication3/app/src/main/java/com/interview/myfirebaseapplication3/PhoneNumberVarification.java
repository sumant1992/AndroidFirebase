package com.interview.myfirebaseapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneNumberVarification extends AppCompatActivity {

    EditText et1;
    EditText et2;
    FirebaseAuth auth;
    String mVerificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_varification);
        et2 = findViewById(R.id.editTextTextPersonName4);
    }

    public void LoginViaPHone(View view) {


        if(TextUtils.isEmpty(et2.getText().toString())){
            Toast.makeText(PhoneNumberVarification.this, "Enter Otp", Toast.LENGTH_SHORT).show();
        }
        else if(et2.getText().toString().replace(" ","").length()!=6){
            Toast.makeText(PhoneNumberVarification.this, "Enter right otp", Toast.LENGTH_SHORT).show();
        }
        else {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, et2.getText().toString().replace(" ",""));
            signInWithPhoneAuthCredential(credential);
        }




    }

    public void MobileNuMberVerification(View view) {
        et1 = findViewById(R.id.editTextTextPersonName3);
        String Mobile = et1.getText().toString();

        if (Mobile.length() == 10) {
            Toast.makeText(this, "You are Right Place", Toast.LENGTH_SHORT).show();
            sendVerificationCode(Mobile);
        } else {
            Toast.makeText(this, "Enter Valid Phone nimber", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendVerificationCode(String mobile)
    {
        auth=FirebaseAuth.getInstance();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+mobile,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                        // This callback will be invoked in two situations:
                        // 1 - Instant verification. In some cases the phone number can be instantly
                        //     verified without needing to send or enter a verification code.
                        // 2 - Auto-retrieval. On some devices Google Play services can automatically
                        //     detect the incoming verification SMS and perform verification without
                        //     user action.
                        signInWithPhoneAuthCredential(credential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        // This callback is invoked in an invalid request for verification is made,
                        // for instance if the the phone number format is not valid.

                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid request
                            // ...
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // The SMS quota for the project has been exceeded
                            // ...
                        }

                        // Show a message and update the UI
                        // ...
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId,
                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        // The SMS verification code has been sent to the provided phone number, we
                        // now need to ask the user to enter the code and then construct a credential
                        // by combining the code with a verification ID.


                        // Save verification ID and resending token so we can use them later
                        PhoneNumberVarification.this.mVerificationId = verificationId;

                        // ...
                    }
                }
);        // OnVerificationStateChangedCallbacks
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(PhoneNumberVarification.this, "Login Sucefully", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = task.getResult().getUser();

                            startActivity(new Intent(PhoneNumberVarification.this,DashBord.class));
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(PhoneNumberVarification.this, "Something Went wrong", Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(PhoneNumberVarification.this, "In Excepion", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void ResendPhoneOtp(View view)
    {
     MobileNuMberVerification(view);
    }
}