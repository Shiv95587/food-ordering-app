package com.example.foodapp;

import static Adapters.PopularAdapter.FOOD_OBJECT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Helper.ManagementCart;

public class ShowDetailsActivity extends AppCompatActivity {

    //Creating objects
    private TextView addToCartButton;
    private TextView priceTxtView, titleTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, foodPic;
    private int quantity=1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        managementCart = new ManagementCart(this);
        // Function to initialize views
        initializeViews();
        getData();
    }

    private void getData()
    {
        Intent intent = getIntent();
            Food obj = (Food) intent.getSerializableExtra(FOOD_OBJECT);

            foodPic.setImageResource(obj.getPic());
            priceTxtView.setText("$ " + String.valueOf(obj.getPrice()));
            titleTxt.setText(obj.getTitle());
            descriptionTxt.setText(obj.getDescription());
            numberOrderTxt.setText(String.valueOf(quantity));

            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberOrderTxt.setText(String.valueOf(++quantity));
                }
            });

            minusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (quantity > 1) {
                        numberOrderTxt.setText(String.valueOf(--quantity));
                    }
                }
            });

            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    obj.setNumberInCart(quantity);
                    managementCart.insertFood(obj);
                }
            });
    }

    private void initializeViews()
    {
        addToCartButton = findViewById(R.id.addCart_btn);
        priceTxtView = findViewById(R.id.price_txt);
        titleTxt = findViewById(R.id.title_txt);
        descriptionTxt = findViewById(R.id.description_txt);
        numberOrderTxt = findViewById(R.id.quantity_txt);
        plusBtn = findViewById(R.id.plus_image);
        minusBtn = findViewById(R.id.minus_image);
        foodPic = findViewById(R.id.food_image);
    }
}
