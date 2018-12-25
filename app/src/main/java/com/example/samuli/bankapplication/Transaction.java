package com.example.samuli.bankapplication;

import java.util.ArrayList;

public class Transaction {
    int money;
    String transactionType;
    String accountFrom;
    String accountTo;

    public Transaction(int m, String transType, String accFrom, String accTo){
        money=m;
        transactionType=transType;
        accountFrom= accFrom;
        accountTo=accTo;
    }

    public int getMoney(){
        return money;
    }

    public String getTransactionType(){
        return transactionType;
    }

    public String getAccountFrom(){
        return accountFrom;
    }

    public String getAccountTo(){
        return accountTo;
    }
}
