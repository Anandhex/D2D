package com.example.d2d;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class JobCreated {

    private String fullName;
    private String job;
    private String desc;
    private String price;
    private String email;
    public JobCreated(){

    }
    public JobCreated( String fullName, String job, String desc, String price,String email) {

        this.fullName = fullName;
        this.job = job;
        this.desc = desc;
        this.price = price;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDesc() {
        return desc;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "JobCreated{" +
                "fullName='" + fullName + '\'' +
                ", job='" + job + '\'' +
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public static  void createJob(String fullName, String job, String desc, String price,String email){
        JobCreated jobs = new JobCreated(fullName,job,desc,price,email);
        CollectionReference col = FirebaseFirestore.getInstance().collection("JobCreated");
        col.add(jobs);
    }
}
