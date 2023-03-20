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
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, bZero, bMinus, bPlus, bDiv, bMod, bMulti, bEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.answer);
        //b1 = findViewById(R.id.btn1); replace in assignBtn() set on listener.
        assignBtn(b1, R.id.btn1);
        assignBtn(b2, R.id.btn2);
        assignBtn(b3, R.id.btn3);
        assignBtn(b4, R.id.btn4);
        assignBtn(b5, R.id.btn5);
        assignBtn(b6, R.id.btn6);
        assignBtn(b7, R.id.btn7);
        assignBtn(b8, R.id.btn8);
        assignBtn(b9, R.id.btn9);
        assignBtn(bZero, R.id.btnZero);
        assignBtn(bMinus, R.id.btnMinus);
        assignBtn(bPlus, R.id.btnPlus);
        assignBtn(bDiv, R.id.btnDiv);
        assignBtn(bMod, R.id.btnMod);
        assignBtn(bMulti, R.id.btnMulti);
        assignBtn(bEqual, R.id.btnEqual);
    }

    void assignBtn(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        String dataCalculate = result.getText().toString();

        if(btnText.equals("=")) {
            result.setText("");
            return;
        }

        dataCalculate = dataCalculate + btnText;
        result.setText(dataCalculate);

        String finalResult = getResult(dataCalculate);
        if (!finalResult.equals("Error")) {
            result.setText(finalResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}