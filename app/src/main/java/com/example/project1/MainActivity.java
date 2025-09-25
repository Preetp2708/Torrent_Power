package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    Button login;

    String user1 = "preet@gmail.com";
    String user2 = "man@gmail.com";
    String user3 = "rahul@gmail.com";
    String pass1 = "1234";
    String pass2 = "5678";
    String pass3 = "0910";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(MainActivity.this , "Please fill all fields" , Toast.LENGTH_LONG).show();
            } else {
                if ((emailText.equals(user1) && passwordText.equals(pass1)) || (emailText.equals(user2) && passwordText.equals(pass2)) || (emailText.equals(user3) && passwordText.equals(pass3))) {
                    Toast.makeText(MainActivity.this,"Login Successful" , Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this , HomeActivity.class);
                    i.putExtra("user" , emailText);
                    i.putExtra("pass" , passwordText);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this,"Login Failed" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}