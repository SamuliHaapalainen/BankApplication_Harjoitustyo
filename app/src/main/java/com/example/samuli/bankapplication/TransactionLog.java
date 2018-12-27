package com.example.samuli.bankapplication;

import android.content.Context;
import android.util.Xml;
import android.view.View;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import java.util.ArrayList;


public class TransactionLog{
    public static final String xmlFileName = "transactions.xml";

    ArrayList<Transaction> transactions = new ArrayList<>();




    public void writeToXMLFile(View view) {


        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try{
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8",true);
            serializer.startTag("","Transactions");
            for(Transaction ta : transactions){
                serializer.startTag("", "Money");
                serializer.text(String.valueOf(ta.getMoney()));
                serializer.endTag("", "Money");

                serializer.startTag("", "Transaction type");
                serializer.text(String.valueOf(ta.getTransactionType()));
                serializer.endTag("", "Transaction type");

                serializer.startTag("", "Invoiced account");
                serializer.text(String.valueOf(ta.getAccountFrom()));
                serializer.endTag("", "Invoiced account");

                serializer.startTag("", "Deposited account");
                serializer.text(String.valueOf(ta.getAccountTo()));
                serializer.endTag("", "Deposited account");
            }
            serializer.endTag("", "Transactions");
            serializer.endDocument();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


