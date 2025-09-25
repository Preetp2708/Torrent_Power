package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button btnProceed, btnBackPayment;
    TextView tvSno, tvAmount;
    boolean isPaid = false; // track payment status

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        radioGroup = findViewById(R.id.radioGroupPayment);
        btnProceed = findViewById(R.id.btnProceed);
        btnBackPayment = findViewById(R.id.btnBackPayment);
        tvSno = findViewById(R.id.tvCustomerNo);
        tvAmount = findViewById(R.id.tvAmount);

        // ✅ Get sno & amount from HomeActivity
        String sno = getIntent().getStringExtra("sno");
        String amount = getIntent().getStringExtra("amount");

        tvSno.setText("Customer No: " + sno);
        tvAmount.setText("Bill Amount: " + amount);

        // ✅ Proceed button (make payment)
        btnProceed.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(PaymentActivity.this, "Please select a payment mode", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton selectedRadio = findViewById(selectedId);
                String mode = selectedRadio.getText().toString();
                Toast.makeText(PaymentActivity.this, "Payment Successful via " + mode, Toast.LENGTH_LONG).show();
                isPaid = true;
            }
        });

        // ✅ Back button (return to HomeActivity with result)
        btnBackPayment.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("paid", isPaid);
            setResult(RESULT_OK, resultIntent);
            finish(); // go back to HomeActivity
        });
    }
}
