package com.interview.myfirebaseapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;
    private TextView textViewUser;
    private LoginButton loginButton;

    private ImageView imageView;
    private static final String TAG ="FacebookAuthentication";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     mFirebaseAuth=FirebaseAuth.getInstance();
     FacebookSdk.sdkInitialize(getApplicationContext());
     textViewUser=findViewById(R.id.textView);
     imageView=findViewById(R.id.imageView);
     loginButton=findViewById(R.id.login_button);
     mCallbackManager=CallbackManager.Factory.create();
     loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
         @Override
         public void onSuccess(LoginResult loginResult) {
             Log.d(TAG,msg:);
         }

         @Override
         public void onCancel() {

         }

         @Override
         public void onError(FacebookException error) {

         }
     });

    }

}                                                                                                                                                                                                                                                                                                                                                                                                                                                            