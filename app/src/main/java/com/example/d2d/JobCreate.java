package com.example.d2d;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class JobCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_create);
    }

    public void createJob(View view) {

       String fullName = ((EditText)findViewById(R.id.editText)).getText().toString().trim();
       String price = ((EditText)findViewById(R.id.editText3)).getText().toString().trim();
       String desc = ((EditText)findViewById(R.id.editText4)).getText().toString().trim();
       String job = ((EditText)findViewById(R.id.editText12)).getText().toString().trim();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        if(TextUtils.isEmpty(fullName)&&TextUtils.isEmpty(price)&&TextUtils.isEmpty(desc)&&TextUtils.isEmpty(job)){
            Toast.makeText(getApplicationContext(),"Enter the required values..",Toast.LENGTH_SHORT).show();
        }else {
            JobCreated.createJob(fullName, job, desc, price, email);
            Toast.makeText(this, "Job Successfully Created", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ServiceDashboard.class);
            startActivity(i);
            finish();
        }
    }
}
