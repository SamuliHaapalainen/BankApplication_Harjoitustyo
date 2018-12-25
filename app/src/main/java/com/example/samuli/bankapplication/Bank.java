package com.example.samuli.bankapplication;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Bank {
    //bank's list for all the accounts
    public ArrayList<Account> accos;
    //bank's list for all the users
    public ArrayList<User> users;
    //list for acooSP spinner
    public ArrayList<String> userAccoNums;
    protected int bankid=9999999;
    public int seq = 88888888;
    String x;
    String y="Users accounts:\n";
    int i;
    String s;
    public String acconumber;
    private static Bank bk = new Bank();
    public static Bank getInstance(){
        return bk;
    }
    int money=0;
    //list for accoToSP spinner
    ArrayList accoNumsBank;
    User currentUser=new User();




    public Bank() {
        accos = new ArrayList<Account>();
        users =new ArrayList<User>();
        userAccoNums = new ArrayList<String>();
        accoNumsBank=new ArrayList<String>();

        users.add(0,new User("Petri Pekkanen", "0500055550",9999999));
        users.add(1,new User("Kalle Palander", "0455400054",9999998));
        users.add(2,new User("Jorma Ollila", "0505556666",9999997));
        users.add(3,new User("Jaska Jokunen", "0449987894",9999996));
    }

    public void setUser(int id){
        for(User u : bk.users){
            if (u.getId()==id){
                this.currentUser = u;
            }
        }
    }



    public void addUser(String n, String phone, int userID){
        bankid -= 1;
        User u = new User(n,phone,userID);
        u.id = bankid;
        users.add(u);
        System.out.println("New user added");
    }




    public String addNormalAcco(int userID) {
        NormalAccount nacco = new NormalAccount(userID, money);
        nacco.acconumber = "FI" + seq;
        seq -= 1;
        accoNumsBank.add(nacco);
        accos.add(nacco);
        for (User u : users) {
            System.out.println("***********get ID***********" + u.getId());
            if (u.getId()==userID) {
                u.userAccos.add(nacco);
                u.userAccoNums.add(nacco.getNumber());
                System.out.println("Tulostus getID:" + u.getId());
            }
        }return "Account created.\nAccountnumber is: " + nacco.getNumber()+" Money: "+nacco.getMoney();
    }

        public String getUserName(int id){
        for(User u : users){
            if(u.getId()==id){
                x = u.getName();
            }
        }return x;
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

    public void printAllAccos() {
        System.out.println("All accounts:");
        for(Account account : accos) {
            account.print();
        }
    }

    public String deposit(String acconumber, int money) {
        for (Account account : accos) {
            if(account.getNumber().equals(acconumber)) {
                account.setBalance(money);
            }
            Transaction ta = new Transaction(money, "Deposit", "-",acconumber);
            account.taList.add(ta);
        }
        System.out.println("Deposit succeeded");
        return "Deposit succeeded. "+money +" € deposited into account: "+acconumber;
    }

    public String accountTransfer(String acconumberFrom, String acconumberTo, int money){
        for(Account account : accos){
            if(account.getNumber().equals(acconumberFrom)){
                withdraw(acconumberFrom, money);
                deposit(acconumberTo,money);
            }
            Transaction ta = new Transaction(money, "Transfer", acconumberFrom, acconumberTo);
            account.taList.add(ta);
        }
        System.out.println("Money transfered "+money+"€ from "+acconumberFrom+"\nto "+acconumberTo);
        return "Money transfered "+money+"€ from "+acconumberFrom+"\nto "+acconumberTo;
    }

    public String withdraw(String acconumber, int money) {
        for (Account account : accos) {
            if(account.getNumber().equals(acconumber)) {
                account.setBalance(-money);
            }
            Transaction ta = new Transaction(money, "Withdraw",acconumber,"-");
        }
        System.out.println("Withdraw succeeded");
        return "Withdraw succeeded";
    }

    public String printUserAccos(int userID){
        for(Account a : accos) {
            if (a.getUserID() == userID) {
                y+="Account: "+a.getNumber() + " Money: "+a.getMoney()+"\n";
            }s = y;


        }return s;
    }

    public void printUsers(){
        for(User u : this.users){
            System.out.println(u.getName());
        }
    }


}