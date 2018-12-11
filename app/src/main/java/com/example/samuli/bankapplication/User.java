package com.example.samuli.bankapplication;

import java.util.ArrayList;
import java.util.List;

public class User {
ArrayList<User> users = new ArrayList<>();
private List<Account> userAccos;
protected String name;
protected String phonenumber;
protected int id=9999999;

public User(){
    userAccos=new ArrayList<>();
    phonenumber="000 000 0000";
    name = "Matti Meikäläinen";
}

public User(String n, int ID, String phone) {
    name = n;
    id = ID;
    phonenumber = phone;
}

public void setName(String n){ name=n;}

public String getName() { System.out.println("User name: "+name);return name; }

public String getPhonenumber(){System.out.println("Phone number: "+ phonenumber);return phonenumber; }

public void setPhonenumber(String p){phonenumber=p;}

public int getId(){ System.out.println("User id: "+id);return id; }


public String getUserAccos(int userID) {
    for(User user : users) {
        if (user.id == userID){
            return (user.name+" account(s): "+userAccos);
        }
    }return "User doesn't have any accounts";
    }
}
