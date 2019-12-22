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

public class Service_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_login);
    }
    public void serv(View view) {
       final Intent serviceDashboard = new Intent(this, ServiceDashboard.class);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        final String email =((EditText)findViewById(R.id.editText5)).getText().toString().trim();
        String password =((EditText)findViewById(R.id.editText6)).getText().toString().trim();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(serviceDashboard);
                            finish();
                        }else{
                            Toast.makeText(Service_login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }else{
            Toast.makeText(this, "Enter the Credentials", Toast.LENGTH_SHORT).show();
        }
    }

    public void signinService(View view) {
        Intent signinServ = new Intent(this,singnUpService.class);
        startActivity(signinServ);
        finish();
    }
}
