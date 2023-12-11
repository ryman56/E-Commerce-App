package com.example.aplikasi_ecommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aplikasi_ecommerce.Helper.TinyDB;
import com.example.aplikasi_ecommerce.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TinyDB tinyDB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView goToRegisterButton = findViewById(R.id.tvGoToRegister);
        goToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        emailEditText = findViewById(R.id.editTextLoginEmail);
        passwordEditText = findViewById(R.id.editTextLoginPassword);
        loginButton = findViewById(R.id.buttonLogin);
        tinyDB = new TinyDB(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredEmail = emailEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Kunci untuk mencari data pengguna adalah email
                String savedEmail = tinyDB.getString(enteredEmail + "_email");
                String savedUsername = tinyDB.getString(enteredEmail + "_username");
                String savedPassword = tinyDB.getString(enteredEmail + "_password");

                if (isValidLogin(enteredEmail, enteredPassword, savedEmail, savedPassword)) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    // Menyertakan username dan email ke dalam intent
                    intent.putExtra("USERNAME", savedUsername);
                    intent.putExtra("EMAIL", savedEmail);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidLogin(String enteredEmail, String enteredPassword, String savedEmail, String savedPassword) {
        return enteredEmail.equals(savedEmail) && enteredPassword.equals(savedPassword);
    }
}
