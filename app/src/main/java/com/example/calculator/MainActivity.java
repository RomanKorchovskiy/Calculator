package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText value1;
    private EditText value2;
    private TextView sign;
    private TextView result;

    private Button addButton;
    private Button subtractButton;
    private Button multiplyButton;
    private Button divideButton;
    private Button rootButton;
    private Button resultButton;

    private Operation currentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value1 = findViewById(R.id.et_value_1);
        value2 = findViewById(R.id.et_value_2);
        sign = findViewById(R.id.tv_sign);
        result = findViewById(R.id.tv_result);

        addButton = findViewById(R.id.b_add);
        subtractButton = findViewById(R.id.b_subtract);
        multiplyButton = findViewById(R.id.b_multiply);
        divideButton = findViewById(R.id.b_divide);
        rootButton = findViewById(R.id.b_root);
        resultButton = findViewById(R.id.b_result);

        addButton.setOnClickListener(view -> setOperation(Operation.ADD));
        subtractButton.setOnClickListener(view -> setOperation(Operation.SUBTRACT));
        multiplyButton.setOnClickListener(view -> setOperation(Operation.MULTIPLY));
        divideButton.setOnClickListener(view -> setOperation(Operation.DIVIDE));
        rootButton.setOnClickListener(view -> setOperation(Operation.ROOT));
        resultButton.setOnClickListener(view -> calculate());

        setOperation(Operation.ADD);
    }

    private void calculate() {
        String val1 = value1.getText().toString();
        String val2 = value2.getText().toString();

        if (val1.isEmpty() || val2.isEmpty()) {
            Toast.makeText(this, "Два числа мають бути заповненими", Toast.LENGTH_LONG).show();
            return;
        }
        double dVal1 = Double.parseDouble(val1);
        double dVal2 = Double.parseDouble(val2);

        double result = 0;

        switch (currentOperation) {
            case ADD:
                result = dVal1 + dVal2;
                break;
            case SUBTRACT:
                result = dVal1 - dVal2;
                break;
            case MULTIPLY:
                result = dVal1 * dVal2;
                break;
            case DIVIDE:
                result = dVal1 / dVal2;
                break;
            case ROOT:
                // корінь з dVal2
                result = Math.pow(dVal1, 1.0 / dVal2);
                break;
        }

        this.result.setText(String.format(Locale.US, "%.5f", result));
    }

    private void setOperation(Operation operation) {
        currentOperation = operation;
        switch (operation) {
            case ADD:
                setOperationLabel("+");
                setNonRootOperation();
                break;
            case SUBTRACT:
                setOperationLabel("-");
                setNonRootOperation();
                break;
            case MULTIPLY:
                setOperationLabel("*");
                setNonRootOperation();
                break;
            case DIVIDE:
                setOperationLabel("/");
                setNonRootOperation();
                break;
            case ROOT:
                setOperationLabel("√");
                setRootOperation();
                break;
        }
    }

    private void setOperationLabel(String operationLabel) {
        this.sign.setText(operationLabel);
    }

    private void setRootOperation() {
        value2.setText("4", TextView.BufferType.NORMAL);
        value2.setEnabled(false);
    }

    private void setNonRootOperation() {
        value2.setEnabled(true);
        value2.setText("0", TextView.BufferType.EDITABLE);
    }
}
