package com.example.samuli.bankapplication;


import java.util.ArrayList;

public abstract class Account {
    public String acconumber;
    public double money;
    public int userID;
    public int seq = 88888888;
    //Here is list for transactions:
    ArrayList <Transaction> taList = new ArrayList<>();


    public Account(int ID, double a) {
        acconumber="FI"+seq;
        seq=-1;
        money = a;
        userID=ID;

        System.out.println("Account created.");
    }

    public String getNumber() {
        return acconumber;
    }

    public double getMoney(){ return this.money; }

    public void print() {
        System.out.println("Account number: " + acconumber + " Money: " + money);
    }

    public void setBalance(double z) {
        if(money + z > 0) {
            money += z;
        }
    }



    public int getUserID(){
        return this.userID;
    }
}

class NormalAccount extends Account {

    public NormalAccount(int userID,  double money) {
        super(userID, money);
    }
}

class CreditAccount extends Account {
    private double credit;

    public CreditAccount(int userID, String acconumber, double money, double c) {
        super(userID, money);
        this.credit = c;
    }

    public void print() {
        System.out.println("Accountnumber: " + acconumber + " Money: " + money + " Credit limit: " + credit);
    }

    public void setBalance(double x) {
        if(money + x > -credit) {
            money += x;
        }
    }
}