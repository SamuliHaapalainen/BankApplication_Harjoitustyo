package com.example.samuli.bankapplication;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accos;
    private ArrayList<User> users;
    private ArrayList<Account> userAccos;



    public Bank() {
        accos = new ArrayList<Account>();
        users = new ArrayList<User>();
        userAccos = new ArrayList<Account>();
    }



    public void addUser(String n, String phone){
        User u = new User();//TODO onko tämän sijainti tässä vai ylempänä ja pitääkö addNormalAcco metodissa käyttää useria
        u.id -= 1;
        users.add(new User(n, u.id, phone));
        System.out.println("New user added");
    }



    public void addNormalAcco(int userID,  int money) {
        User user;
        Account acco = new NormalAccount(userID, money);
        accos.add(acco);
        userAccos.add(acco);
        System.out.println("Normal account created");

        }


    public void addCreditAcco(int userID, String acconumber, int money, int c) {
        accos.add(new CreditAccount(userID, acconumber, money, c));
    }

    public void removeAcco(String acconumber) {
        Account temp = null;
        for (Account account : accos) {
            if(account.getNumber().equals(acconumber)) {
                temp = account;
                System.out.println("Account removed.");
            }
        }
        accos.remove(temp);
    }

    public void search(String acconumber) {
        for (Account account : accos) {
            if(account.getNumber().equals(acconumber)) {
                account.print();
            }
        }
    }

    /*public void printAll() {
        System.out.println("All accounts:");
        for(Account account : accos) {
            account.print();
        }
    }*/

    public void deposit(String acconumber, int money) {
        for (Account account : accos) {
            if(account.getNumber().equals(acconumber)) {
                account.setBalance(money);
            }
        }
        System.out.println("Deposit succeeded");
    }
    public void accountTransfer(String acconumberFrom, String acconumberTo, int money){
        for(Account account : accos){
            if(account.getNumber().equals(acconumberFrom)){
                withdraw(acconumberFrom, money);
                deposit(acconumberTo,money);
            }
        }
        System.out.println("Money transfered "+money+"€ from "+acconumberFrom+"\nto "+acconumberTo);
    }

    public void withdraw(String acconumber, int money) {
        for (Account account : accos) {
            if(account.getNumber().equals(acconumber)) {
                account.setBalance(-money);
            }
        }
        System.out.println("Withdraw succeeded");
    }

    
}