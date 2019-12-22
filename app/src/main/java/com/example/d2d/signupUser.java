package com.example.d2d;

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

public class signupUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user);
    }
    public void loginUser(View view) {
        Intent loginUser = new Intent(this,MainActivity.class);
        startActivity(loginUser);
        finish();
    }
    public void userDashboard(View view){
        final Intent userDashsboard = new Intent(this,UserDashboard.class);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final String fullName = ((EditText)findViewById(R.id.editText2)).getText().toString();
        final String email = ((EditText)findViewById(R.id.editText5)).getText().toString().trim();
        final String password = ((EditText)findViewById(R.id.editText6)).getText().toString().trim();
        final String dob = ((EditText)findViewById(R.id.editText7)).getText().toString().trim();
        final String country = ((EditText)findViewById(R.id.editText8)).getText().toString().trim();
        final String state = ((EditText)findViewById(R.id.editText9)).getText().toString().trim();
        final String city = ((EditText)findViewById(R.id.editText10)).getText().toString().trim();
        final String street = ((EditText)findViewById(R.id.editText11)).getText().toString().trim();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User.addUser(email,fullName,dob,country,state,city,street);
                            startActivity(userDashsboard);
                            finish();
                        }
                        else{
                            Toast.makeText(signupUser.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }else{
            Toast.makeText(this, "Enter the Credentials", Toast.LENGTH_SHORT).show();
        }

    }
}
