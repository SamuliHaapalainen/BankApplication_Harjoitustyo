package com.example.samuli.bankapplication;

import java.util.ArrayList;
import java.util.List;

public class User {

    public ArrayList<Account> userAccos= new ArrayList<>();

    protected String name;
    protected String phonenumber;
    public int id;
    public ArrayList<String> userAccoNums = new ArrayList<String>();
    int adminID;

    public User() {
        userAccos = new ArrayList<>();
        phonenumber = "000 000 0000";
        name = "Name";
    }

    public User(String n, String phone, int userID) {
        name = n;
        phonenumber = phone;
        id = userID;
    }


    public void setName(String n) {
        name = n;
    }

    public String getName() {
        System.out.println("getName method used, name is: " + name);
        return name;
    }

    public String getPhonenumber() {
        System.out.println("Phone number: " + phonenumber);
        return phonenumber;
    }

    public void setPhonenumber(String p) {
        phonenumber = p;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public ArrayList getAccounts() {
        System.out.println("---------User accounts: "+userAccos);
    return userAccos;
    }
}



