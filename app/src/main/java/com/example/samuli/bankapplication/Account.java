package com.example.samuli.bankapplication;


import java.util.ArrayList;

public abstract class Account {
    public String acconumber;
    public int money;
    public int userID;
    public int seq = 88888888;
    //Here is list for transactions:
    ArrayList <Transaction> taList = new ArrayList<>();


    public Account(int ID, int a) {
        acconumber="FI"+seq;
        seq=-1;
        money = a;
        userID=ID;

        System.out.println("Account created.");
    }

    public String getNumber() {
        return acconumber;
    }

    public int getMoney(){ return this.money; }

    public void print() {
        System.out.println("Account number: " + acconumber + " Money: " + money);
    }

    public void setBalance(int z) {
        if(money + z > 0) {
            money += z;
        }
    }



    public int getUserID(){
        return this.userID;
    }
}

class NormalAccount extends Account {

    public NormalAccount(int userID,  int money) {
        super(userID, money);
    }
}

class CreditAccount extends Account {
    private int credit;

    public CreditAccount(int userID, String acconumber, int money, int c) {
        super(userID, money);
        this.credit = c;
    }

    public void print() {
        System.out.println("Accountnumber: " + acconumber + " Money: " + money + " Credit limit: " + credit);
    }

    public void setBalance(int x) {
        if(money + x > -credit) {
            money += x;
        }
    }
}