package com.example.samuli.bankapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner chooseFunction;
    Spinner accountSP;
    Spinner accoToSP;
    EditText accoNumber;
    EditText textField;
    EditText transferAcco;
    EditText editText;
    EditText etUserID;
    TextView genField;
    TextView currentUser;
    TextView accoInfo;
    Button SIbtn;
    Button SObtn;
    Button refreshBtn;
    Button genBtn;
    String choice;
    Bank bk = new Bank();
    String acconumberInput;
    String acconumberTo;
    int userIDMain;
    int money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> func = new ArrayList<String>();
        func.add("Choose the function");
        func.add("Create an account");
        func.add("Deposit money");
        func.add("Withdraw money");
        func.add("Account transfer");
        SIbtn=(Button) findViewById(R.id.button);
        SObtn = (Button) findViewById(R.id.button2);


        //initializing of main selection spinner
        chooseFunction = (Spinner) findViewById(R.id.chooseFunction);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, func);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseFunction.setAdapter(adapter);


        editText = (EditText) findViewById(R.id.editText);
        textField = (EditText) findViewById(R.id.textField);
        genField = (TextView) findViewById(R.id.generalField);
        currentUser = (TextView) findViewById(R.id.currentUser);
        accoInfo = (TextView) findViewById(R.id.accoInfo);
        refreshBtn = (Button) findViewById(R.id.refreshBtn);
        genBtn = (Button) findViewById(R.id.generalBtn);


        etUserID=(EditText) findViewById(R.id.etUserID);



        //Define functionality to Sign-in button
        SIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIDStr = etUserID.getText().toString();
                try{
                    refreshBtn.setVisibility(View.VISIBLE);
                    userIDMain=new Integer(userIDStr).intValue();
                    bk.setUser(userIDMain);
                    chooseFunction.setVisibility(View.VISIBLE);
                    currentUser.setText("Signed in with: "+bk.getUserName(userIDMain)+"\nUser ID:"+userIDMain);
                    SIbtn.setVisibility(View.INVISIBLE);
                    SObtn.setVisibility(View.VISIBLE);
                    System.out.println("/////////////////////////UserIdMain//////////// " +userIDMain);
                    System.out.println("////////////////////////printUserAccos////////// "+bk.printUserAccos(userIDMain));
                    try {
                        genField.setText(bk.printUserAccos(userIDMain));
                    }catch(NullPointerException n){
                        System.out.println("Null pointer exception");}
                }catch (NumberFormatException e){
                    System.out.println("Number format exception");
                }
            }
        });

        //initializing of user accounts spinner
        accountSP = (Spinner) findViewById(R.id.accountSP);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, bk.currentUser.userAccoNums);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSP.setAdapter(adapter1);



        SObtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIDMain=0;
                SObtn.setVisibility(View.INVISIBLE);
                SIbtn.setVisibility(View.VISIBLE);
                currentUser.setText("Sign in by writing your userID");
                chooseFunction.setVisibility(View.INVISIBLE);
                genField.setVisibility(View.INVISIBLE);
            }
        });

        //User can refresh the list of her/his accounts in general field
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genField.setText("");
                genField.setText(bk.printUserAccos(userIDMain));
            }
        });

        //Main options, which includes different functionalities
        chooseFunction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice=parent.getItemAtPosition(position).toString();
                if(choice.equals("Choose the function")){
                    System.out.println("Choose the function");
                    genBtn.setVisibility(View.INVISIBLE);
                    genField.setText(bk.printUserAccos(userIDMain));
                }
                if(choice.equals("Create an account")){
                    genField.setText("");
                    genBtn.setVisibility(View.VISIBLE);
                    genBtn.setText("Create new account");

                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            accoInfo.setText(bk.addNormalAcco(userIDMain));

                        }
                    });

                }
                if(choice.equals("Deposit money")){
                    genBtn.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    editText.setHint("Money to deposit");
                    accountSP.setPrompt("Select account");

                    accoInfo.setText("");
                    genField.setText("");
                    accountSP.setVisibility(View.VISIBLE);

                    accountSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            acconumberInput=parent.getItemAtPosition(position).toString();
                            System.out.println("Account number selected. Account number is: "+acconumberInput);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    genBtn.setVisibility(View.VISIBLE);
                    genBtn.setText("Deposit");
                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            try {
                                money = Integer.parseInt(editText.getText().toString());
                            }catch (NumberFormatException e){
                                System.out.println("Number format exception");
                            }catch (NullPointerException e) {
                                System.out.println("Null pointer exception");
                            }
                            genField.setText(bk.deposit(acconumberInput,money));
                            System.out.println("Account number selected. Account number is: "+acconumberInput+" Money is: " + money);

                        }
                    });

                }
                if (choice.equals("Withdraw money")){
                    genBtn.setVisibility(View.VISIBLE);
                    accoInfo.setText("");
                    genField.setText("");
                    accountSP.setVisibility(View.VISIBLE);
                    genBtn.setText("Withdraw");
                    editText.setVisibility(View.VISIBLE);
                    editText.setHint("How much money you want to withdraw?");

                    accountSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            acconumberInput=parent.getItemAtPosition(position).toString();
                            System.out.println("Account number selected. Account number is: "+acconumberInput);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    try {
                        money = Integer.parseInt(editText.getText().toString());
                    }catch (NumberFormatException e){
                        System.out.println("Number format exception");
                    }catch (NullPointerException e) {
                        System.out.println("Null pointer exception");
                    }

                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            genField.setText(bk.withdraw(acconumberInput,money));
                        }
                    });

                }
                if (choice.equals("Account transfer")){
                    genBtn.setVisibility(View.VISIBLE);
                    accoInfo.setText("");
                    genField.setText("");
                    accountSP.setVisibility(View.VISIBLE);
                    textField.setVisibility(View.VISIBLE);
                    textField.setHint("Type the account you want to transfer");
                    genBtn.setText("Transfer");
                    editText.setVisibility(View.VISIBLE);
                    editText.setHint("How much money you want to transfer");

                    accountSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            acconumberInput=parent.getItemAtPosition(position).toString();
                            System.out.println("Account number selected. Account number is: "+acconumberInput);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    try {
                        money = Integer.parseInt(editText.getText().toString());
                    }catch (NumberFormatException e){
                        System.out.println("Number format exception");
                    }catch (NullPointerException e) {
                        System.out.println("Null pointer exception");
                    }

                    acconumberTo = textField.getText().toString();
                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            genField.setText(bk.accountTransfer(acconumberInput, acconumberTo, money));
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



}
