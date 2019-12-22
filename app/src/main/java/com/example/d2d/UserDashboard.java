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

public class UserDashboard extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        Query query = FirebaseFirestore.getInstance()
                .collection("JobCreated")
                .limit(50);

                 fetch(query);


    }

    private void fetch(Query query) {
       
        FirestoreRecyclerOptions<JobCreated> options = new FirestoreRecyclerOptions.Builder<JobCreated>()
                .setQuery(query,JobCreated.class)
                .build();
        Toast.makeText(getApplicationContext(),query.toString(),Toast.LENGTH_SHORT).show();
        adapter = new FirestoreRecyclerAdapter<JobCreated, JobCreatedAdapter.JobViewHolder>(options){


            @NonNull
            @Override
            public JobCreatedAdapter.JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_job,parent,false);
                return new JobCreatedAdapter.JobViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull JobCreatedAdapter.JobViewHolder holder, final int position, @NonNull final JobCreated model) {
                holder.fullName.setText(model.getFullName());
                holder.price.setText(String.valueOf( model.getPrice()));
                holder.desc.setText(model.getDesc());
                holder.job.setText(model.getJob());

                holder.check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), JobCreatedSpecific.class);
                        intent.putExtra("fullname", model.getFullName());
                        intent.putExtra("price",String.valueOf(model.getPrice()));
                        intent.putExtra("desc", model.getDesc());
                        intent.putExtra("job", model.getJob());
                        intent.putExtra("email", model.getEmail());
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

    public void addWork(View view) {
        Intent i = new Intent(this,notifyJobUser.class);
        startActivity(i);
    }

    public void updateUser(View view) {
        Intent i = new Intent(this,EditUser.class);
        startActivity(i);
    }

    public void findValue(View view) {

        String serachKey = ((EditText)findViewById(R.id.search)).getText().toString();
        Toast.makeText(getApplicationContext(),serachKey,Toast.LENGTH_SHORT).show();
        if(TextUtils.isEmpty(serachKey)){
            Query query = FirebaseFirestore.getInstance()
                    .collection("JobCreated")
                    .limit(50);
            fetch(query);

        }
        else{
            Query query = FirebaseFirestore.getInstance()
                    .collection("JobCreated")
                    .whereEqualTo("job",serachKey)
                    .limit(50);

            fetch(query);
        }
    }
}
