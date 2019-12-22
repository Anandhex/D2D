package com.example.d2d;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class jobNotified {
    private String fullName;
    private String price;
    private String address;
    private String job;
    private String specification;
    private  String email;
    public jobNotified(){

    }
    public jobNotified(String fullName, String price, String address, String job,String specification,String email) {
        this.fullName = fullName;
        this.price = price;
        this.address = address;
        this.job = job;
        this.specification=specification;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecification() {
        return specification;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public static  void createJob(String fullName, String price, String address, String job,String spec,String email){
        jobNotified jobs = new jobNotified(fullName,price,address,job,spec,email);
       CollectionReference doc = FirebaseFirestore.getInstance().collection("JobRequested");
        doc.add(jobs);
    }
}
