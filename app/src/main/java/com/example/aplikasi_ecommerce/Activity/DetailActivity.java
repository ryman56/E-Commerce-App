package com.example.aplikasi_ecommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplikasi_ecommerce.Domain.PopularDomain;
import com.example.aplikasi_ecommerce.Helper.ManagementCart;
import com.example.aplikasi_ecommerce.R;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    public Button addToCartButton;
    public TextView titleTxt, feeTxt, descTxt, reviewTxt, scoreTxt;
    public ImageView picItem, backBtn;
    private PopularDomain object;
    private int numberOrder=1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        managementCart = new ManagementCart(this);

        initView();
        getBundle();

    }

    private void getBundle() {
        object= (PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picItem);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getPrice());
        descTxt.setText(object.getDescription());
        reviewTxt.setText(object.getReview()+"");
        scoreTxt.setText(object.getScore()+"");

        addToCartButton.setOnClickListener(v -> {
            object.setNumberInCart(numberOrder);
            managementCart.insertFood(object);
        });
        backBtn.setOnClickListener(v -> finish());
    }

    private void initView() {
        addToCartButton=findViewById(R.id.addToCartBtn);
        feeTxt=findViewById(R.id.feeTxt);
        titleTxt=findViewById(R.id.titleTxt);
        descTxt=findViewById(R.id.descTxt);
        picItem=findViewById(R.id.itemPic);
        reviewTxt=findViewById(R.id.reviewTxt);
        scoreTxt=findViewById(R.id.scoreTxt);
        backBtn=findViewById(R.id.backBtn);
    }
}