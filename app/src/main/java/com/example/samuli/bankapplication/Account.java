package com.example.samuli.bankapplication;


public abstract class Account {
    protected String acconumber;
    protected int money;
    protected int userID;
    protected int seq = 88888888;


    public Account(int ID, int a) {
        seq=-1;
        acconumber="FI"+seq;
        money = a;
        userID=ID;

        System.out.println("Account created.");
    }

    public String getNumber() {
        return acconumber;
    }

    public void print() {
        System.out.println("Account number: " + acconumber + " Money: " + money);
    }

    public void setBalance(int z) {
        if(money + z > 0) {
            money += z;
        }
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