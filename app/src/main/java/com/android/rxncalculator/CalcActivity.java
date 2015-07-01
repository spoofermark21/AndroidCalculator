package com.android.rxncalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class CalcActivity extends AppCompatActivity {

    public EditText et;
    public Button btnBckSpace, btnCE, btnC, btnPlusMinus, btnSqrRoot,
                btn1, btn2, btn3, btn4, btn5, btn6,btn7, btn8, btn9,
                btnMinus, btn0, btnDot, btnAddition, btnResults,
                btnDivision, btnPercent, btnMultiply, btn1x;

    public char operation;

    public static int firstNum, secondNum, totalNum = 0;

    public void instantiateBtns() {
        btnBckSpace = (Button) findViewById(R.id.btnBckSpace);
        btnCE = (Button) findViewById(R.id.btnCE);
        btnC = (Button) findViewById(R.id.btnC);
        btnPlusMinus = (Button) findViewById(R.id.btnPlusMinus);
        btnSqrRoot = (Button) findViewById(R.id.btnSqrRoot);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnAddition = (Button) findViewById(R.id.btnAddition);
        btnResults = (Button) findViewById(R.id.btnResults);
        btnDivision = (Button) findViewById(R.id.btnDivision);
        btnPercent = (Button) findViewById(R.id.btnPercent);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btn1x = (Button) findViewById(R.id.btn1x);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn4 = (Button) findViewById(R.id.btn4);
        btn0 = (Button) findViewById(R.id.btn0);
    }

    public void onClickListener() {

        btnBckSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // et.setText("");
                if (!isZero() && et.length() != 1) {
                    et.setText(clearString(et.getText().toString()));
                } else {
                    if(et.length() == 1) {
                        et.setText("0");
                    }
                }
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText("0");
                firstNum = 0;
                secondNum = 0;
                totalNum = 0;
            }
        });

        btnCE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText("0");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isZero()) {
                    et.setText("9");
                } else {
                    et.setText(et.getText().toString() + "" + 9);
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isZero()) {
                    et.setText("8");
                } else {
                    et.setText(et.getText().toString() + "" + 8);
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isZero()) {
                    et.setText("7");
                } else {
                    et.setText(et.getText().toString() + "" + 7);
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isZero()) {
                    et.setText("6");
                } else {
                    et.setText(et.getText().toString() + "" + 6);
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isZero()) {
                    et.setText("5");
                } else {
                    et.setText(et.getText().toString() + "" + 5);
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isZero()) {
                    et.setText("4");
                } else {
                    et.setText(et.getText().toString() + "" + 4);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isZero()) {
                    et.setText("3");
                } else {
                    et.setText(et.getText().toString() + "" + 3);
                }
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isZero()) {
                    et.setText("2");
                } else {
                    et.setText(et.getText().toString() + "" + 2);
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isZero()) {
                    et.setText("1");
                } else {
                    et.setText(et.getText().toString() + "" + 1);
                }
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!isZero()) {
                    et.setText(et.getText().toString() + "" + 0);
                }
            }
        });

        btnAddition.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                firstNum = Integer.parseInt(et.getText().toString());
                et.setText("");
                operation = '+';

            }
        });

        btnResults.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                secondNum = Integer.parseInt(et.getText().toString());

                if (operation == '+') {
                    totalNum = firstNum + secondNum;
                    et.setText(totalNum + "");
                }
            }
        });

    }

    public boolean isZero() {
        if (et.getText().toString().equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    public String clearString(String num) {
        String returnNum = "";

        for (int i=1; i < num.length(); i++) {
            returnNum = returnNum + "" + num.charAt(i);
        }

        return returnNum;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        et = (EditText) findViewById(R.id.editText); // declare text
        et.setEnabled(false); // readonly edit text

        instantiateBtns(); // declare buttons
        onClickListener(); // events buttons


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
