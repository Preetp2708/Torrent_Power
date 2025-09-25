package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {
    TextView guest, cno, add, bill;
    Button payment, back;

    // ✅ Track payment status
    boolean isPaid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        guest = findViewById(R.id.user);
        cno = findViewById(R.id.cno);
        add = findViewById(R.id.add);
        bill = findViewById(R.id.amount);
        payment = findViewById(R.id.payment);
        back = findViewById(R.id.back);

        // ✅ Get values from MainActivity
        String user = getIntent().getStringExtra("user");
        String pass = getIntent().getStringExtra("pass");

        guest.setText(user);

        // Example values
        String sno = "5000100" + pass;
        String address = "Varachha Road , Surat - 395006.";
        String amount = "105$";

        cno.setText(sno);
        add.setText(address);
        bill.setText(amount);

        // ✅ Launch PaymentActivity
        payment.setOnClickListener(v -> {
            if (isPaid) {
                // If already paid → show message instead of opening payment screen
                android.widget.Toast.makeText(HomeActivity.this, "Bill already paid!", android.widget.Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(HomeActivity.this, PaymentActivity.class);
                i.putExtra("sno", sno);
                i.putExtra("amount", amount);
                startActivityForResult(i, 100);  // ✅ expecting result back
            }
        });

        // ✅ Back to login → clear data
        back.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // clears everything
            startActivity(i);
            finish();
        });
    }

    // ✅ Receive result from PaymentActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            boolean paid = data.getBooleanExtra("paid", false);
            if (paid) {
                isPaid = true;
                android.widget.Toast.makeText(this, "Payment marked as PAID.", android.widget.Toast.LENGTH_SHORT).show();
            }
        }
    }
}
