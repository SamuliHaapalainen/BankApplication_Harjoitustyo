package com.example.samuli.bankapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    Spinner chooseFunction;
    Spinner accountSP;
    EditText textField;
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
    String acconumberInput2;
    String acconumberTo;
    int userIDMain;
    Integer userIDtobeRemoved;
    double money;
    double moneyW;
    ArrayList<String> tempList = new ArrayList();



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


        //initializing of user accounts spinner
        accountSP = (Spinner) findViewById(R.id.accountSP);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, tempList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSP.setAdapter(adapter1);


        //Define functionality to Sign-in button
        SIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bk.setVariablesXYnull(userIDMain);
                genField.setText(bk.printUserAccos(userIDMain));
                String userIDStr = etUserID.getText().toString();
                accoInfo.setText("");

                try {
                    userIDMain = new Integer(userIDStr).intValue();
                    currentUser.setText(bk.setUser(userIDMain)); //setting the current user
                    adapter1.clear();
                    adapter1.addAll(bk.currentUser.userAccoNums);
                    if (userIDMain == 987654321) {
                        accountSP.setVisibility(View.VISIBLE);
                        adapter1.clear();
                        adapter1.addAll(bk.accoNumsBank);
                        func.add("Remove account");
                        func.add("Remove user");
                        chooseFunction.setVisibility(View.VISIBLE);
                        SIbtn.setVisibility(View.INVISIBLE);
                        SObtn.setVisibility(View.VISIBLE);
                    } else {
                        refreshBtn.setVisibility(View.VISIBLE);
                        chooseFunction.setVisibility(View.VISIBLE);
                        //currentUser.setText("Signed in with: "+bk.getUserName(userIDMain)+"\nUser ID:"+userIDMain);
                        SIbtn.setVisibility(View.INVISIBLE);
                        SObtn.setVisibility(View.VISIBLE);}
                        try {
                            genField.setText(bk.printUserAccos(userIDMain));
                        } catch (NullPointerException n) {
                            System.out.println("Null pointer exception");
                        }
                    } catch(NumberFormatException e){
                        System.out.println("Number format exception");
                    }

            }
        });




        //defining Sign-out button's functionality
        SObtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIDMain=0;
                SObtn.setVisibility(View.INVISIBLE);
                SIbtn.setVisibility(View.VISIBLE);
                accountSP.setVisibility(View.INVISIBLE);
                genBtn.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.INVISIBLE);
                refreshBtn.setVisibility(View.INVISIBLE);
                textField.setVisibility(View.INVISIBLE);
                currentUser.setText("Sign in by writing your userID");
                chooseFunction.setVisibility(View.INVISIBLE);
                genField.setVisibility(View.INVISIBLE);
                func.remove("Remove account");
                func.remove("Remove user");
            }
        });

        //User can refresh the list of her/his accounts in general field. Also general button comes visible if some reason it's disappeared
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bk.setVariablesXYnull(userIDMain);
                genBtn.setVisibility(View.VISIBLE);
                genField.setVisibility(View.VISIBLE);
                genField.setText(bk.printUserAccos(userIDMain));

            }

 //               }
        });


        //Main options, which includes different functions of application
        chooseFunction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice=parent.getItemAtPosition(position).toString();
                if(choice.equals("Choose the function")){
                    System.out.println("Choose the function");
                    accoInfo.setVisibility(View.INVISIBLE);
                    genBtn.setVisibility(View.INVISIBLE);
                    bk.setVariablesXYnull(userIDMain);
                    genField.setVisibility(View.VISIBLE);
                    genField.setText(bk.printUserAccos(userIDMain));
                    accountSP.setVisibility(View.INVISIBLE);
                    editText.setVisibility(View.INVISIBLE);
                    textField.setVisibility(View.INVISIBLE);
                }
                if(choice.equals("Create an account")){
                    genField.setText("");
                    genField.setVisibility(View.VISIBLE);
                    genBtn.setVisibility(View.VISIBLE);
                    genBtn.setText("Create new account");
                    refreshBtn.setVisibility(View.VISIBLE);
                    accountSP.setVisibility(View.INVISIBLE);
                    accoInfo.setVisibility(View.INVISIBLE);
                    editText.setVisibility(View.INVISIBLE);
                    textField.setVisibility(View.INVISIBLE);

                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            genField.setText(bk.addNormalAcco(userIDMain));
                            System.out.println("ACCOUNTNUMBER BEFORE UPDATING ADAPTER1: "+bk.currentUser.userAccoNums.get(0));
                            adapter1.clear();
                            adapter1.addAll(bk.currentUser.userAccoNums);

                        }
                    });

                }
                if(choice.equals("Deposit money")){
                    genBtn.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    editText.setHint("Money to deposit");
                    genBtn.setText("Deposit");
                    genBtn.setVisibility(View.VISIBLE);
                    refreshBtn.setVisibility(View.INVISIBLE);
                    accoInfo.setText("");
                    genField.setText("");
                    accountSP.setVisibility(View.VISIBLE);
                    textField.setVisibility(View.INVISIBLE);
                    accoInfo.setVisibility(View.VISIBLE);

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
                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            try {
                                money = Double.parseDouble(editText.getText().toString());
                            }catch (NumberFormatException e){
                                System.out.println("Number format exception");
                            }catch (NullPointerException e) {
                                System.out.println("Null pointer exception");
                            }
                            if(acconumberInput!=null) {
                                accoInfo.setText(bk.deposit(acconumberInput, money));
                            }else{accoInfo.setText("Transaction failed. Choose account again");}
                            System.out.println("Account number selected. Account number is: "+acconumberInput+" Money is: " + money);

                        }
                    });

                }
                if (choice.equals("Withdraw money")){
                    genBtn.setVisibility(View.VISIBLE);
                    accoInfo.setText("");
                    accoInfo.setVisibility(View.VISIBLE);
                    genField.setText("");
                    accountSP.setVisibility(View.VISIBLE);
                    genBtn.setText("Withdraw");
                    editText.setVisibility(View.VISIBLE);
                    editText.setHint("How much money you want to withdraw?");
                    textField.setVisibility(View.INVISIBLE);

                    accountSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            acconumberInput2=parent.getItemAtPosition(position).toString();
                            System.out.println("Account number selected. Account number is: "+acconumberInput2);
                            genField.setText("Balance: "+bk.getAccount(acconumberInput2).getMoney()+" €");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });


                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                moneyW = Double.parseDouble(editText.getText().toString());
                            }catch (NumberFormatException e){
                                System.out.println("Number format exception");
                            }catch (NullPointerException e) {
                                System.out.println("Null pointer exception");
                            }
                            if(acconumberInput2!=null) {
                                accoInfo.setText(bk.withdraw(acconumberInput2, moneyW));
                            }else{accoInfo.setText("Transaction failed. Choose account again");}
                        }
                    });

                }
                if (choice.equals("Account transfer")) {
                    genBtn.setVisibility(View.VISIBLE);
                    accoInfo.setText("");
                    accoInfo.setVisibility(View.VISIBLE);
                    genField.setText("");
                    accountSP.setVisibility(View.VISIBLE);
                    textField.setVisibility(View.VISIBLE);
                    textField.setHint("Type the account you want transfer to");
                    genBtn.setText("Transfer");
                    editText.setVisibility(View.VISIBLE);
                    editText.setHint("How much money you want to transfer");

                    accountSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            acconumberInput = parent.getItemAtPosition(position).toString();
                            genField.setText("Balance: " + bk.getAccount(acconumberInput).getMoney() + " €");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            acconumberTo = textField.getText().toString();
                            try {
                                money = Double.parseDouble(editText.getText().toString());
                            } catch (NumberFormatException e) {
                                System.out.println("Number format exception");
                            } catch (NullPointerException e) {
                                System.out.println("Null pointer exception");
                            }
                            if (acconumberInput != null) {
                                accoInfo.setText(bk.accountTransfer(acconumberInput, acconumberTo, money));
                                genField.setText("Balance: " + bk.getAccount(acconumberInput).getMoney() + " €");
                            } else {
                                accoInfo.setText("Transaction failed. Choose account again");
                            }
                        }
                    });
                }
                if(choice.equals("Remove account")){
                    genBtn.setVisibility(View.VISIBLE);
                    genBtn.setText("Remove");
                    refreshBtn.setVisibility(View.INVISIBLE);
                    genField.setVisibility(View.INVISIBLE);
                    textField.setVisibility(View.INVISIBLE);
                    accoInfo.setText("");
                    accoInfo.setVisibility(View.VISIBLE);
                    accountSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            acconumberInput=parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            accoInfo.setText(bk.removeAcco(acconumberInput));
                            adapter1.clear();
                            adapter1.addAll(bk.accoNumsBank);
                        }
                    });

                }
                if(choice.equals("Remove user")) {
                    genBtn.setVisibility(View.VISIBLE);
                    genBtn.setText("Remove");
                    refreshBtn.setVisibility(View.INVISIBLE);
                    genField.setVisibility(View.INVISIBLE);
                    textField.setVisibility(View.INVISIBLE);
                    accoInfo.setText("");
                    accoInfo.setVisibility(View.VISIBLE);
                    adapter1.clear();
                    adapter1.addAll(bk.userIdlist);
                    accountSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                userIDtobeRemoved = Integer.parseInt(parent.getItemAtPosition(position).toString());
                            } catch(NumberFormatException e){
                                System.out.println("Numberformatexception");
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    genBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            accoInfo.setText(bk.removeUser(userIDtobeRemoved));
                            adapter1.clear();
                            adapter1.addAll(bk.userIdlist);
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
