package com.example.aplikasi_ecommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.aplikasi_ecommerce.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigation();
    }
    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, CartActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, ProfileActivity.class)));
    }
}