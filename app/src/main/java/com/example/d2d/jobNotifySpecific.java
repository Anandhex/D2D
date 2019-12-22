package com.example.d2d;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class jobNotifySpecific extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_notify_specific);
        getIncomingIntent();
    }
    private  void getIncomingIntent(){
        if(getIntent().hasExtra("fullname")&&getIntent().hasExtra("price")&&getIntent().hasExtra("desc")&&getIntent().hasExtra("address")){
            String fullName = getIntent().getStringExtra("fullname").trim();
            String price = getIntent().getStringExtra("price").trim();
            String desc = getIntent().getStringExtra("desc").trim();
            String address = getIntent().getStringExtra("address").trim();
            setContent(fullName,price,desc,address);
        }
    }
    private void setContent(String fullname,String price,String desc,String job){
        TextView full = findViewById(R.id.namejob);
        TextView pric = findViewById(R.id.jobprice);
        TextView de = findViewById(R.id.jobdesc);
        TextView jo = findViewById(R.id.jobcat);
        full.setText(fullname);
        pric.setText(price);
        de.setText(desc);
        jo.setText(job);
    }

    public void mail(View view) {
        DocumentReference doc = FirebaseFirestore.getInstance().collection("services").document(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Service user = documentSnapshot.toObject(Service.class);
                Intent i = new Intent(Intent.ACTION_SENDTO);
                Bundle b = getIntent().getExtras();
                String uriText =
                        "mailto:"+b.getString("email")+
                                "?subject="+ Uri.encode("Applying for the job "+b.getString("job"))+
                                "&body="+ Uri.encode(
                                        "Hi Sir/Madam \n\n I, "+user.getFullName()+" applying for the above mentioned. I stay at "+
                                user.getStreet()+", "+user.getCity()+", "+user.getState()+", "+user.getCountry()+". To contact me "+
                                user.getPhoneNo()+"\n\n\nThanks and Regards,\n"+user.getFullName());
                Uri uri = Uri.parse(uriText);
                i.setData(uri);
                startActivity(Intent.createChooser(i,"Send email...."));
            }
        });


    }
}
