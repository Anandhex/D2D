package com.example.d2d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);
        FirebaseFirestore.getInstance().collection("services").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Service service = documentSnapshot.toObject(Service.class);
                ((TextView)findViewById(R.id.serviceemail)).setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                ((EditText)findViewById(R.id.sericename)).setText(service.getFullName());
                ((EditText)findViewById(R.id.sericecity)).setText(service.getCity());
                ((EditText)findViewById(R.id.sericedob)).setText(service.getDob());
                ((EditText)findViewById(R.id.sericestreet)).setText(service.getStreet());
                ((EditText)findViewById(R.id.sericeph)).setText(service.getPhoneNo());
                ((EditText)findViewById(R.id.sericestate)).setText(service.getState());
                ((EditText)findViewById(R.id.sericecountry)).setText(service.getCountry());
            }
        });
    }

    public void updateService(View view) {
        String name = ((EditText)findViewById(R.id.sericename)).getText().toString().trim();
        String city= ((EditText)findViewById(R.id.sericecity)).getText().toString().trim();
        String dob= ((EditText)findViewById(R.id.sericedob)).getText().toString().trim();
        String street=  ((EditText)findViewById(R.id.sericestreet)).getText().toString().trim();
        String phno=((EditText)findViewById(R.id.sericeph)).getText().toString().trim();
        String state=((EditText)findViewById(R.id.sericestate)).getText().toString().trim();
        String country=((EditText)findViewById(R.id.sericecountry)).getText().toString().trim();
        Service service = new Service(FirebaseAuth.getInstance().getCurrentUser().getEmail(),name,dob,country,state,city,street,phno);
        FirebaseFirestore.getInstance().collection("services").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(service).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
