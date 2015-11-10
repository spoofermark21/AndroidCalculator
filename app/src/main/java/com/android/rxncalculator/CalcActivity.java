package com.android.rxncalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.View;

import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {

    private EditText txtNum;
    private TextView lblOperations;

    /*private Button btnBckSpace, btnCE, btnC, btnNegate, btnSqrRoot,
            btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnMinus, btn0, btnDot, btnAddition, btnResults,
            btnDivision, btnSquare, btnMultiply, btnCube;*/

    private double totalNum, firstNum, secondNum = 0;

    private boolean isClick = false;
    private boolean isResultClick = false;
    private boolean isOperationClick = false;

    private String tempOperations = "";
    private double tempNum;

    private char operator = '1';


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calc);
        instantiateBtns(); // declare buttons

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
        return true;
    }

    /* functions
    --------------------------------------------------------------------------
     */

    public void setOperation(char tempOperator) {
        try {
            if (!isClick || isResultClick) {
                // temporary in case of change operations
                tempOperations = lblOperations.getText().toString();
                tempNum = Double.parseDouble(txtNum.getText().toString());

                if (operator != '1') {

                    switch (operator) {

                        case '+':
                            firstNum = firstNum + Double.parseDouble(txtNum.getText().toString());
                            break;
                        case '-':
                            firstNum = firstNum - Double.parseDouble(txtNum.getText().toString());
                            break;
                        case '×':
                            firstNum = firstNum * Double.parseDouble(txtNum.getText().toString());
                            break;
                        case '÷':
                            try {
                                if (Double.parseDouble(txtNum.getText().toString()) != 0.0) {
                                    firstNum = firstNum / Double.parseDouble(txtNum.getText().toString());
                                } else {
                                    txtNum.setText("0");
                                    showError("Can't divide by zero. Please try again.");
                                    return;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                    }
                    double temp = Double.parseDouble(txtNum.getText().toString());
                    txtNum.setText(convertDoubleInteger(firstNum) + "");
                    lblOperations.setText(Html.fromHtml(tempOperations + convertDoubleInteger(temp) + " " + colorOperator(tempOperator)) + " ");

                } else {
                    firstNum = Double.parseDouble(txtNum.getText().toString());
                    lblOperations.setText(Html.fromHtml(convertDoubleInteger(firstNum) + " " + colorOperator(tempOperator)) + " ");
                    txtNum.setText("0");
                }
                isClick = true;
                isResultClick = false;
                isOperationClick = true;
            } else if (isClick || !isResultClick) {
                lblOperations.setText(Html.fromHtml(tempOperations + convertDoubleInteger(tempNum) + " " + colorOperator(tempOperator)) + " ");
                if (!isOperationClick) {
                    lblOperations.setText(Html.fromHtml(convertDoubleInteger(tempNum) + " " + colorOperator(tempOperator)) + " ");
                }
            }

            operator = tempOperator;
        } catch (Exception ex) {
            showError(ex.getMessage());
        }

    }

    public String convertDoubleInteger(double num) {
        if (num > 2417483647.0) {
            showError("Overflow: Largest Value is 2417483647");
        }
        return num % 1 == 0 ? (int) num + "" : num + "";
    }

    public String colorOperator(char temp) {
        return "<font color='#EE0000'>" + temp + "</font>";
    }

    public boolean isEmpty() {
        return txtNum.getText().toString().equals("0");
    }

    public boolean isDotExist() {
        return txtNum.getText().toString().indexOf('.') != -1;
    }


    public String bckSpace(String num) {
        String returnNum = "";
        try {
            for (int i = 0; i < num.length() - 1; i++) {
                returnNum = returnNum + "" + num.charAt(i);
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
        return returnNum;
    }

    public void reset() {
        txtNum.setText("0");
        lblOperations.setText("");
        totalNum = 0;
        firstNum = 0;
        secondNum = 0;
        operator = '1';

        tempOperations = "";
        tempNum = 0;

        isClick = false;
        isOperationClick = false;
    }


    /* event listener
    ---------------------------------------------------------------------
     */

    public void getSquare(View v) {
        try {
            txtNum.setText(convertDoubleInteger(Double.parseDouble(txtNum.getText().toString()) * Double.parseDouble(txtNum.getText().toString())) + "");
            isClick = false;
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    public void getCube(View v) {
        try {
            txtNum.setText(convertDoubleInteger(Double.parseDouble(txtNum.getText().toString()) * Double.parseDouble(txtNum.getText().toString()) * Double.parseDouble(txtNum.getText().toString())) + "");
            isClick = false;
        } catch (Exception ex) {
            showError(ex.getMessage());
        }

    }

    public void getSqrRoot(View v) {
        try {
            if (Double.parseDouble(txtNum.getText().toString()) != 0) {
                txtNum.setText(convertDoubleInteger(Math.sqrt(Double.parseDouble(txtNum.getText().toString()))) + "");
                isClick = false;
            } else {
                showError("Can't do that.");
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    public void getNegate(View v) {
        try {
            txtNum.setText(convertDoubleInteger(Double.parseDouble(txtNum.getText().toString()) * -1) + "");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }

    }


    public void displayNum(View v) {
        try {
            if (isEmpty() || isClick) {
                txtNum.setText(v.getTag().toString() + "");
                isClick = false;
            } else {
                txtNum.setText(txtNum.getText().toString() + v.getTag().toString());
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    public void getOperation(View v) {
        try {
            char tempOperator = v.getTag().toString().charAt(0);
            setOperation(tempOperator);
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    public void getResults(View v) {
        try {
            secondNum = Double.parseDouble(txtNum.getText().toString());

            switch (operator) {
                case '+':
                    totalNum = firstNum + secondNum;
                    break;
                case '-':
                    totalNum = firstNum - secondNum;
                    break;
                case '×':
                    totalNum = firstNum * secondNum;
                    break;
                case '÷':
                    if (secondNum != 0) {
                        totalNum = firstNum / secondNum;
                    } else {
                        txtNum.setText("0");
                        showError("Can't divide by zero. Please try again.");
                        return;
                    }
                    break;
            }
            txtNum.setText(convertDoubleInteger(totalNum) + "");
            //reset after displaying result

            totalNum = 0;
            firstNum = 0;
            secondNum = 0;

            isClick = true;
            isResultClick = true;
            isOperationClick = false;

            operator = '1';
            lblOperations.setText("");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }

    }

    public void backSpace(View v) {
        try {
            if (!isEmpty() && txtNum.length() != 1) {
                if (txtNum.getText().toString().charAt(0) == '-' && txtNum.length() == 2) {
                    txtNum.setText("0");
                } else {
                    txtNum.setText(bckSpace(txtNum.getText().toString()));
                }
            } else {
                if (txtNum.length() == 1) {
                    txtNum.setText("0");
                }
            }
            isClick = false;
        } catch (Exception ex) {
            showError(ex.getMessage());
        }

    }

    public void clear(View v) {
        reset();
    }

    public void clearEntry(View v) {
        txtNum.setText("0");
    }

    public void displayDot(View v) {
        try {
            if (!isDotExist() || isClick) {
                if (isClick) {
                    txtNum.setText("0.");
                    isClick = false;
                } else {
                    txtNum.setText(txtNum.getText().toString() + ".");
                }
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
        }

    }

    public void showError(String error) {

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(error);
        dlgAlert.setTitle("ERROR");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
    }

    /* buttons
    -------------------------------------------------------------------
     */

    public void instantiateBtns() {

        txtNum = (EditText) findViewById(R.id.txtNum); // declare text
        txtNum.setEnabled(false); // readonly edit text
        lblOperations = (TextView) findViewById(R.id.lblOperations);
        /*
        btnBckSpace = (Button) findViewById(R.id.btnBckSpace);
        btnCE = (Button) findViewById(R.id.btnCE);
        btnC = (Button) findViewById(R.id.btnC);
        btnNegate = (Button) findViewById(R.id.btnPlusMinus);

        btnDot = (Button) findViewById(R.id.btnDot);
        btnAddition = (Button) findViewById(R.id.btnAddition);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);

        btnDivision = (Button) findViewById(R.id.btnDivision);

        btnResults = (Button) findViewById(R.id.btnResults);
        btnSquare = (Button) findViewById(R.id.btnSquare);
        btnCube = (Button) findViewById(R.id.btnCube);
        btnSqrRoot = (Button) findViewById(R.id.btnSqrRoot);

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
        btn0 = (Button) findViewById(R.id.btn0);*/
    }

}
