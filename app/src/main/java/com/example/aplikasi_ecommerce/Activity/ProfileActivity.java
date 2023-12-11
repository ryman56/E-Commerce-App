package com.example.aplikasi_ecommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasi_ecommerce.R;

public class ProfileActivity extends AppCompatActivity {
    private String email;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("USERNAME");
            email = intent.getStringExtra("EMAIL");
//            Toast.makeText(ProfileActivity.this, username , Toast.LENGTH_SHORT).show();
//            Toast.makeText(ProfileActivity.this, email , Toast.LENGTH_SHORT).show();
            TextView tvUsername = findViewById(R.id.tv_nama_profile);
            TextView tvEmail= findViewById(R.id.tv_email_profile);
            tvUsername.setText(username);
            tvEmail.setText(email);
        }
        TextView tv_singOut= findViewById(R.id.tv_signOut);
        tv_singOut.setOnClickListener(v -> {
            Intent intent2 = new Intent(ProfileActivity.this, LoginActivity.class);
            intent2.putExtra("USERNAME", username);
            intent2.putExtra("EMAIL", email);
            startActivity(intent2);
        });
        bottomNavigation();
    }
    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.putExtra("USERNAME", username);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
        });
        cartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, CartActivity.class);
            intent.putExtra("USERNAME", username);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
        });
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
            intent.putExtra("USERNAME", username);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
        });
    }
}