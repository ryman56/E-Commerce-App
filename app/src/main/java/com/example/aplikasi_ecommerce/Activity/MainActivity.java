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
    private String email;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("USERNAME");
            email = intent.getStringExtra("EMAIL");
            TextView usernameTextView = findViewById(R.id.textView2);
            usernameTextView.setText(username);
        }
        initRecyclerView();
        bottomNavigation();


    }
    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.putExtra("USERNAME", username);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
        });
        cartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            intent.putExtra("USERNAME", username);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
        });
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("USERNAME", username);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
        });
    }


    private void initRecyclerView() {
        ArrayList<PopularDomain> items=new ArrayList<>();
        items.add(new PopularDomain("T-Shirt Black","Introducing our Classic Black Cotton T-Shirt, a wardrobe staple that seamlessly blends timeless style with unmatched comfort. This versatile piece is meticulously crafted to meet the demands of your everyday style needs. The deep black hue radiates sophistication, making it a perfect choice for various occasions, whether you're heading out for a casual day or a night on the town. Our commitment to quality is evident in the premium cotton fabric, ensuring a soft and breathable feel against your skin. The tailored fit strikes the perfect balance between style and comfort, making this T-shirt an essential addition to your collection. Embrace the enduring appeal of black, simplify your routine with easy care, and express your unique style effortlessly. Elevate your wardrobe with our Classic Black Cotton T-Shirt – where comfort meets enduring elegance. Order now and make a statement with simplicity!","item_1",15,4,50000,13));
        items.add(new PopularDomain("Smart Watch","Meet the future of wearable technology with our Smart Watch – your ultimate companion for a connected and convenient lifestyle. Packed with features designed to enhance your daily routine, this sleek and modern smartwatch goes beyond just telling time. Track your fitness goals, receive notifications on the go, and monitor your health effortlessly. The stylish design seamlessly integrates into your lifestyle, ensuring you stay connected without compromising on fashion. Embrace the synergy of innovation and style with our Smart Watch. Order now and experience the next level of wearable technology!","item_2",10,4.5,200000,20));
        items.add(new PopularDomain("Iphone 14","Introducing the iPhone 14 – a marvel of innovation and sophistication. Redefining the boundaries of mobile technology, this cutting-edge device seamlessly blends form and function. With its sleek design and powerful features, the iPhone 14 is a statement of elegance and efficiency. Capture moments with stunning clarity using the advanced camera system, experience lightning-fast performance, and enjoy the immersive display. Stay at the forefront of technology and style with the iPhone 14. Elevate your mobile experience – order now and step into the future!","item_3",15,4.3,1500000,30));
        items.add(new PopularDomain("VisionX pro LED TV","Immerse yourself in the brilliance of entertainment with the VisionX Pro LED TV. Designed to redefine your viewing experience, this television combines sleek aesthetics with state-of-the-art technology. The stunning 4K resolution and HDR capabilities bring every scene to life with vibrant colors and unparalleled clarity. The VisionX Pro is not just a TV; it's a gateway to a cinematic world in the comfort of your home. With smart features and a sleek design, it seamlessly integrates into your living space. Elevate your home entertainment with the VisionX Pro LED TV – where visual excellence meets smart innovation. Order now and transform your viewing experience!","item_4",18,4.0,20000000,40));

        recyclerViewPopular=findViewById(R.id.view1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);
    }
}