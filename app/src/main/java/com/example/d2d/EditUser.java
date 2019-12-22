package com.example.d2d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);
                        ((TextView)findViewById(R.id.usermail)).setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                        ((EditText)findViewById(R.id.username)).setText(user.getFullName());
                        ((EditText)findViewById(R.id.userdob)).setText(user.getDob());
                        ((EditText)findViewById(R.id.usercountry)).setText(user.getCountry());
                        ((EditText)findViewById(R.id.usercity)).setText(user.getCity());
                        ((EditText)findViewById(R.id.userstate)).setText(user.getState());
                        ((EditText)findViewById(R.id.userstreet)).setText(user.getStreet());
                    }
                });
        ((Button)findViewById(R.id.updateUser)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=((EditText)findViewById(R.id.username)).getText().toString().trim();
                String dob=((EditText)findViewById(R.id.userdob)).getText().toString().trim();
                String country=((EditText)findViewById(R.id.usercountry)).getText().toString().trim();
                String city= ((EditText)findViewById(R.id.usercity)).getText().toString().trim();
                String state=((EditText)findViewById(R.id.userstate)).getText().toString().trim();
                String street=((EditText)findViewById(R.id.userstreet)).getText().toString().trim();
                User user = new User(FirebaseAuth.getInstance().getCurrentUser().getEmail(),name,dob,country,state,city,street);
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
