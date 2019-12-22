package com.example.d2d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class ServiceDashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_dashboard);
        recyclerView = findViewById(R.id.rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        Query query = FirebaseFirestore.getInstance()
                .collection("JobRequested")
                .limit(50);
        fetch(query);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void addJob(View view) {
        Intent i = new Intent(getApplicationContext(),JobCreate.class);
        startActivity(i);
    }
    private void fetch(Query query) {

        FirestoreRecyclerOptions<jobNotified> options = new FirestoreRecyclerOptions.Builder<jobNotified>()
                .setQuery(query,jobNotified.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<jobNotified, JobsNotifiedAdapter.JobNotifiedViewHolder>(options){


            @NonNull
            @Override
            public JobsNotifiedAdapter.JobNotifiedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_notify,parent,false);
                return new JobsNotifiedAdapter.JobNotifiedViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull JobsNotifiedAdapter.JobNotifiedViewHolder holder, final int position, @NonNull final jobNotified model) {
                holder.fullName.setText(model.getFullName());
                holder.price.setText(String.valueOf( model.getPrice()));
                holder.address.setText(model.getAddress());
                holder.job.setText(model.getJob());
                holder.check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),jobNotifySpecific.class);
                        intent.putExtra("fullname",model.getFullName());
                        intent.putExtra("price",String.valueOf(model.getPrice()));
                        intent.putExtra("desc",model.getSpecification());
                        intent.putExtra("address",model.getAddress());
                        intent.putExtra("job",model.getJob());
                        intent.putExtra("email",model.getEmail());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        adapter.stopListening();
        adapter.startListening();

    }
    public void findJob(View view) {

        String serachKey = ((EditText)findViewById(R.id.searchJob)).getText().toString();
        Toast.makeText(getApplicationContext(),serachKey,Toast.LENGTH_SHORT).show();
        if(TextUtils.isEmpty(serachKey)){
            Query query = FirebaseFirestore.getInstance()
                    .collection("JobRequested")
                    .limit(50);
            fetch(query);
        }
        else{
            Query query = FirebaseFirestore.getInstance()
                    .collection("JobRequested")
                    .whereEqualTo("job",serachKey)
                    .limit(50);

            fetch(query);
        }
    }

    public void updateSeriveHa(View view) {
        Intent i = new Intent(getApplicationContext(),EditService.class);
        startActivity(i);
    }
}
