package com.example.d2d;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class notifyJobUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_job_user);
    }

    public void addJob(View view) {
        String fullName = ((EditText)findViewById(R.id.username)).getText().toString().trim();
        String price = ((EditText)findViewById(R.id.userprice)).getText().toString().trim();
        String address = ((EditText)findViewById(R.id.useraddress)).getText().toString().trim();
        String job  = ((EditText)findViewById(R.id.userjob)).getText().toString().trim();
        String spec = ((EditText)findViewById(R.id.userspec)).getText().toString().trim();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().trim();
        if(TextUtils.isEmpty(fullName)&&TextUtils.isEmpty(price)&&TextUtils.isEmpty(address)&&TextUtils.isEmpty(job)&&TextUtils.isEmpty(spec)){
            Toast.makeText(getApplicationContext(),"Enter the required values",Toast.LENGTH_SHORT).show();
        }else {
            jobNotified.createJob(fullName, price, address, job, spec, email);
            Intent i = new Intent(this, UserDashboard.class);
            startActivity(i);
            finish();
        }
    }
}
