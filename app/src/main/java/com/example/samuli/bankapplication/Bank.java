package com.example.samuli.bankapplication;

import java.util.ArrayList;
import android.util.Xml;



public class Bank {

    //bank's list for all the accounts
    public ArrayList<Account> accos;
    //bank's list for all the users
    public ArrayList<User> users;
    //for the spinner
    ArrayList<String> userIdlist;
    //list for acooSP spinner
    public ArrayList<String> userAccoNums;
    protected int bankid=9999999;
    public int seq = 88888888; //seq number is for account number
    String x;
    String y="Users accounts:\n";
    double i;
    double balance;
    String s;
    Account acc;
    public String acconumber;
    private static Bank bk = new Bank();
    public static Bank getInstance(){
        return bk;
    }
    double money=0;
    //list for accoToSP spinner
    ArrayList <String> accoNumsBank;
    //Initializing current user of a Bank
    User currentUser;
    TransactionLog taLog;





    public Bank() {
        accos = new ArrayList<Account>();
        users = new ArrayList<User>();
        userAccoNums = new ArrayList<String>();
        accoNumsBank = new ArrayList<String>();
        taLog = new TransactionLog();
        userIdlist = new ArrayList<>();

        users.add(0, new User("Petri Pekkanen", "0500055550", 9999999));
        users.add(1, new User("Kalle Palander", "0455400054", 9999998));
        users.add(2, new User("Jorma Ollila", "0505556666", 9999997));
        users.add(3, new User("Jaska Jokunen", "0449987894", 9999996));
        users.add(4,new User("Admin","0200000020",987654321));

        for(User u : users){
            userIdlist.add(String.valueOf(u.getId()));
        }
    }

    public String setUser(int id){
        for(User u : this.users){
            if (u.getId()==id){
                this.currentUser = u;
                return "Signed in with: "+getUserName(id)+"\nUser ID:"+id;
            }
        }return "User doesn't exist";
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
        accoNumsBank.add(nacco.getNumber());
        accos.add(nacco);
        for (User u : users) {
            System.out.println("***********get ID***********" + u.getId());
            if (u.getId()==userID) {
                u.userAccos.add(nacco);
                u.userAccoNums.add(nacco.getNumber());
                System.out.println("Tulostus ACCOUNTNUMBER:" + u.userAccoNums.get(0));
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



    public void addCreditAcco(int userID, String acconumber, double money, double c) {
        accos.add(new CreditAccount(userID, acconumber, money, c));
    }

    //admin can remove users with this method
    public String removeAcco(String acconumber) {
        Account temp = null;
        for (Account account : accos) {
            if(account.getNumber().equals(acconumber)) {
                temp = account;
                System.out.println("Account removed.");
            }
        }
        accoNumsBank.remove(temp.getNumber());
        accos.remove(temp);
        return "Account "+acconumber+" removed.";
    }

    //admin can remove users with this method
    public String removeUser(int userID) {
        User temp = null;
        for (User user : users) {
            if(user.getId()==userID) {
                temp = user;
                System.out.println("User removed.");
            }
        }
        userIdlist.remove(String.valueOf(temp.getId()));
        users.remove(temp);
        return "User "+userID+" removed.";
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

    public Account getAccount(String acconum){
        for(Account a : accos){
            if(a.getNumber().equals(acconum)){
               this.acc = a;
            }
        }return acc;
    }

    public String deposit(String acconumber, double money) {
        for (Account account : accos) {
            if(account.getNumber().equals(acconumber)) {
                account.setBalance(money);
                this.balance=account.getMoney();
            }
            //adds the transaction to transaction list
            Transaction ta = new Transaction(money, "Deposit", "-",acconumber);
            //adds transaction object to Account class
            account.taList.add(ta);


        }
        System.out.println("Deposit succeeded");
        return "Deposit succeeded. "+money +" € deposited into account: "+acconumber+", Balance: "+balance+" €";
    }

    public String accountTransfer(String acconumberFrom, String acconumberTo, double money){
        if(acconumberFrom.equals(acconumberTo)){
            return"Account numbers are same";
        }
        for(Account account : accos){
            if(account.getNumber().equals(acconumberFrom)){
                withdraw(acconumberFrom, money);
                for(Account a : accos){
                    if(a.getNumber().equals(acconumberTo)){
                        deposit(acconumberTo,money);
                    }
                }
            }
            Transaction ta = new Transaction(money, "Transfer", acconumberFrom, acconumberTo);
            account.taList.add(ta);
        }
        System.out.println("Money transfered "+money+"€ from "+acconumberFrom+"\nto "+acconumberTo);
        return "Money transfered "+money+" € from "+acconumberFrom+"\nto "+acconumberTo;
    }

    public String withdraw(String acconumber, double money) {
        for (Account account : accos) {
            if(account.getNumber().equals(acconumber)) {
                account.setBalance(-money);
                this.balance=account.getMoney();
            }
            Transaction ta = new Transaction(money, "Withdraw",acconumber,"-");
        }
        System.out.println("Withdraw succeeded");
        return "Withdraw succeeded, "+money+"€ withdrawn\nBalance: "+balance+" €";
    }

    public String printUserAccos(int userID){
        for(Account a : accos) {
            if (a.getUserID() == userID) {
                y+="Account: "+a.getNumber() + " Money: "+a.getMoney()+"\n";
            }s = y;


        }return s;
    }

    //helping method for printing users' acocunts to screen
    public void setVariablesXYnull(int userID){
        x=null;
        y="User accounts:\n";
    }

    public void printUsers(){
        for(User u : this.users){
            System.out.println(u.getName());
        }
    }


}