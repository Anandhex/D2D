package com.example.d2d;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class JobCreatedAdapter extends RecyclerView.Adapter<JobCreatedAdapter.JobViewHolder>  {


    private List<JobCreated> jobCreatedList;
    private Context mCtx;

    public JobCreatedAdapter(List<JobCreated> jobCreatedList, Context mCtx) {
        this.jobCreatedList = jobCreatedList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_job,parent,false);
        return new JobCreatedAdapter.JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, final int position) {
        final JobCreated jobCreated =  jobCreatedList.get(position);

        holder.fullName.setText(jobCreated.getFullName());
        holder.price.setText(String.valueOf( jobCreated.getPrice()));
        holder.desc.setText(jobCreated.getDesc());
        holder.job.setText(jobCreated.getJob());

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, JobCreatedSpecific.class);
                intent.putExtra("fullname", jobCreated.getFullName());
                intent.putExtra("price",String.valueOf(jobCreated.getPrice()));
                intent.putExtra("desc", jobCreated.getDesc());
                intent.putExtra("job", jobCreated.getJob());
                intent.putExtra("email", jobCreated.getEmail());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobCreatedList.size();
    }


    static class JobViewHolder extends RecyclerView.ViewHolder{
        TextView fullName,job,price,desc;
        LinearLayout parentLayout;
        Button check;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            job = itemView.findViewById(R.id.Job);
            price=itemView.findViewById(R.id.price);
            desc = itemView.findViewById(R.id.desc);
            parentLayout = itemView.findViewById(R.id.parent);
            check =itemView.findViewById(R.id.check);
        }
    }
}
