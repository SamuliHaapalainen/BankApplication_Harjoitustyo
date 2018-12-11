package com.example.samuli.bankapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner chooseFunction;
    EditText accoNumber;
    EditText transferAcco;
    EditText editText;
    int money;
    String choice="";
    Bank bk = new Bank();
    String acconumberInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final User user = new User();
        ArrayList<String> func = new ArrayList<String>();
        func.add("Choose the function");
        func.add("Create an account");
        func.add("Deposit money");
        func.add("Withdraw money");
        func.add("Account transfer");
        func.add("Card payment");
        chooseFunction = (Spinner) findViewById(R.id.chooseFunction);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, func);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseFunction.setAdapter(adapter);
        accoNumber = (EditText) findViewById(R.id.accoNumber);
        acconumberInput = accoNumber.getText().toString();
        String et = editText.getText().toString();
        money = new Integer(et).intValue();

        transferAcco = (EditText) findViewById(R.id.transferAcco);

        chooseFunction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice=parent.getItemAtPosition(position).toString();
                if(choice.equals("Create an account")){
                    accoNumber.setVisibility(View.VISIBLE);
                    bk.addNormalAcco(user.id, money);
                }
                if(choice.equals("Deposit money")){
                    accoNumber.setVisibility(View.VISIBLE);

                }
                if (choice.equals("Withdraw money")){
                    accoNumber.setVisibility(View.VISIBLE);
                    bk.withdraw("0000",0);
                }
                if (choice.equals("Account transfer")){
                    accoNumber.setVisibility(View.VISIBLE);
                    transferAcco.setVisibility(View.VISIBLE);

                    bk.accountTransfer("000","0000",88);
                }
                //TODO tänne luotava vielä kortin luominen ja kortilla maksaminen
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



}
