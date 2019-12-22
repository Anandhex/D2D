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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void serviceLogin(View view) {

        final Intent loginService = new Intent(this,Service_login.class);

        startActivity(loginService);
        finish();
    }

    public void signUpUser(View view) {
        Intent signUpUser = new Intent(this,signupUser.class);
        startActivity(signUpUser);
        finish();
    }

    public void signUpService(View view) {
        Intent signUpService  = new Intent(this,singnUpService.class);
        startActivity(signUpService);
        finish();
    }

    public void userDashboard(View view) {
        final Intent userDashboard = new Intent(this, UserDashboard.class);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String email = ((EditText) findViewById(R.id.editText5)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.editText6)).getText().toString().trim();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(userDashboard);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else{
            Toast.makeText(MainActivity.this, "Enter the Credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
