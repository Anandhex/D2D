package com.example.d2d;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class JobsNotifiedAdapter extends RecyclerView.Adapter<JobsNotifiedAdapter.JobNotifiedViewHolder> {

    private List<jobNotified> jobList;
    private Context mCtx;

    public JobsNotifiedAdapter(List<jobNotified> jobList, Context mCtx) {
        this.jobList = jobList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public JobsNotifiedAdapter.JobNotifiedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_notify,parent,false);
        return new JobsNotifiedAdapter.JobNotifiedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsNotifiedAdapter.JobNotifiedViewHolder holder, final int position) {
        final jobNotified job =  jobList.get(position);

        holder.fullName.setText(job.getFullName());
        holder.price.setText(String.valueOf( job.getPrice()));
        holder.address.setText(job.getAddress());
        holder.job.setText(job.getJob());


        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx,jobList.get(position)+"You have clicked on the button",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx,jobNotifySpecific.class);
                intent.putExtra("fullname",job.getFullName());
                intent.putExtra("price",String.valueOf(job.getPrice()));
                intent.putExtra("desc",job.getSpecification());
                intent.putExtra("address",job.getAddress());
                intent.putExtra("job",job.getJob());
                intent.putExtra("email",job.getEmail());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    static class JobNotifiedViewHolder extends RecyclerView.ViewHolder{
        TextView fullName,job,price,address;
        LinearLayout parentLayout;
        Button check;



        public JobNotifiedViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.nameService);
            job = itemView.findViewById(R.id.jobService);
            price=itemView.findViewById(R.id.priceService);
            address = itemView.findViewById(R.id.addressService);
            parentLayout = itemView.findViewById(R.id.parentjob);
            check =itemView.findViewById(R.id.checkService);
        }
    }
}
