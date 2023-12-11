package com.example.aplikasi_ecommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasi_ecommerce.Helper.TinyDB;
import com.example.aplikasi_ecommerce.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private TinyDB tinyDB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.etRegisterUsername);
        emailEditText = findViewById(R.id.etRegisterEmail);
        passwordEditText = findViewById(R.id.etRegisterPassword);
        registerButton = findViewById(R.id.btnRegister);
        tinyDB = new TinyDB(this);
        TextView goToLoginButton = findViewById(R.id.tvGoToLogin);
        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (isValidRegistration(username, email, password)) {
                    saveRegistrationInfo(username, email, password);
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Invalid registration information", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidRegistration(String username, String email, String password) {
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password);
    }

    private void saveRegistrationInfo(String username, String email, String password) {
        // Simpan informasi registrasi ke TinyDB atau SharedPreferences
        tinyDB.putString("username", username);
        tinyDB.putString("email", email);
        // PENTING: Jangan simpan password secara langsung, gunakan praktik aman seperti enkripsi atau hashing
        tinyDB.putString("password", password); // Ini hanya contoh sederhana, seharusnya tidak dilakukan secara langsung
    }
}
