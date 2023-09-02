package com.mrravipande.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result;
    Button btnAC, bEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hide action bar
        getSupportActionBar().hide();

        result = findViewById(R.id.answer);
        assignBtn(R.id.btn1);
        assignBtn(R.id.btn2);
        assignBtn(R.id.btn3);
        assignBtn(R.id.btn4);
        assignBtn(R.id.btn5);
        assignBtn(R.id.btn6);
        assignBtn(R.id.btn7);
        assignBtn(R.id.btn8);
        assignBtn(R.id.btn9);
        assignBtn(R.id.btn0);
        assignBtn(R.id.btnMinus);
        assignBtn(R.id.btnPlus);
        assignBtn(R.id.btnDiv);
        assignBtn(R.id.btnMode);
        assignBtn(R.id.btnMulti);
        bEqual = findViewById(R.id.btnEqual);
        btnAC = findViewById(R.id.btnAC);

        // Set click listeners for "Equal" and "AC" buttons
        bEqual.setOnClickListener(this);
        btnAC.setOnClickListener(this);
    }

    void assignBtn(int id) {
        Button btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        String dataCalculate = result.getText().toString();

        if (btnText.equals("AC")) {
            result.setText("0");
        } else if (btnText.equals("=")) {
            String finalResult = getResult(dataCalculate);
            result.setText(finalResult);
        } else {
            if (dataCalculate.equals("0")) {
                dataCalculate = btnText;
            } else {
                dataCalculate = dataCalculate + btnText;
            }
            result.setText(dataCalculate);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            Object evalResult = context.evaluateString(scriptable, data, "Javascript", 1, null);

            if (evalResult instanceof Double) {
                double result = (Double) evalResult;
                // Check if the result is a whole number (integer)
                if (result == Math.floor(result)) {
                    // If it's an integer, convert it to an integer and return as a string
                    return String.valueOf((int) result);
                } else {
                    // If it's a decimal, return it as a string
                    return String.valueOf(result);
                }
            } else {
                return "Error";
            }
        } catch (Exception e) {
            return "Error";
        }
    }
}
