package com.example.d2d;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class JobCreatedSpecific extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_specific);
        getIncomingIntent();
    }
    private  void getIncomingIntent(){
        if(getIntent().hasExtra("fullname")&&getIntent().hasExtra("price")&&getIntent().hasExtra("desc")&&getIntent().hasExtra("job")){
            String fullName = getIntent().getStringExtra("fullname").trim();
            String price = getIntent().getStringExtra("price").trim();
            String desc = getIntent().getStringExtra("desc").trim();
            String job = getIntent().getStringExtra("job").trim();


            setContent(fullName,price,desc,job);
        }
    }
    private void setContent(String fullname,String price,String desc,String job){

        TextView full = findViewById(R.id.textView11);
        TextView pric = findViewById(R.id.textView14);
        TextView de = findViewById(R.id.textView15);
        TextView jo = findViewById(R.id.textView13);
        full.setText(fullname);
        pric.setText(price);
        de.setText(desc);
        jo.setText(job);
    }
    public void call(View view){
        String email = getIntent().getStringExtra("email").trim();
        DocumentReference doc = FirebaseFirestore.getInstance().collection("services").document(email);
        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Service service = documentSnapshot.toObject(Service.class);
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+service.getPhoneNo()));
                startActivity(i);
            }
        });

    }
}
