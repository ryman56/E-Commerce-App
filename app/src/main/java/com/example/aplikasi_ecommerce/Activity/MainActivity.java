package com.example.aplikasi_ecommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasi_ecommerce.Adapter.PopularAdapter;
import com.example.aplikasi_ecommerce.Domain.PopularDomain;
import com.example.aplikasi_ecommerce.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private  RecyclerView recyclerViewPopular;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");
//        Toast.makeText(MainActivity.this, savedUsername , Toast.LENGTH_SHORT).show();
        TextView usernameTextView = findViewById(R.id.textView2);
        usernameTextView.setText(savedUsername);
        initRecyclerView();
        bottomNavigation();


    }
    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items=new ArrayList<>();
        items.add(new PopularDomain("T-Shirt Black","Lorem Ipsum Dolor sit met","item_1",15,4,500,10));
        items.add(new PopularDomain("Smart Watch","Lorem Ipsum Dolor sit met","item_2",10,4.5,450,20));
        items.add(new PopularDomain("Iphone 14","Lorem Ipsum Dolor sit met","item_3",15,4.3,800,30));
        items.add(new PopularDomain("VisionX pro LED TV","Lorem Ipsum Dolor sit met","item_4",18,4.0,1500,40));

        recyclerViewPopular=findViewById(R.id.view1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);
    }
}