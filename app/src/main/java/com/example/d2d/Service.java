package com.example.d2d;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Service {
    private String email;
    private String fullName;
    private String dob;
    private  String country;
    private String state;
    private String city;
    private String street;
    private String phoneNo;
    public Service(){

    }
    public Service(String email, String fullName, String dob, String country, String state, String city, String street,String phoneNo) {
        this.email = email;
        this.fullName = fullName;
        this.dob = dob;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.phoneNo=phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
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

    public String getDob() {
        return dob;
    }


    public String getCountry() {
        return country;
    }



    public String getState() {
        return state;
    }



    public String getCity() {
        return city;
    }



    public String getStreet() {
        return street;
    }



    @Override
    public String toString() {
        return "Service{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }

    public  static void addUser(String email, String fullName, String dob, String country, String state, String city, String street, String PhoneNo){
        Service service = new Service(email,fullName,dob,country,state,city,street,PhoneNo);
        CollectionReference col = FirebaseFirestore.getInstance().collection("services");
        col.document(service.getEmail()).set(service).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

    }
}
