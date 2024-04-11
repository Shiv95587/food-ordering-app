package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapters.CartListAdapter;
import Helper.ManagementCart;
import Helper.TinyDB;

public class CartListActivity extends AppCompatActivity {

    CartListAdapter adapter;
    private RecyclerView recyclerView;
    private ManagementCart managementCart;
    private TextView totalItemsFee, deliveryFee, taxFee, totalFee, emptyCartTxt;
    private double tax;
    public static final String TRANSACTION_LIST = "Transaction_list";
    private TextView checkOutBtn;
    private ScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        managementCart = new ManagementCart(this);

        initializeViews();
        setCartList();
        calculateCart();
        bottomNavigation(); // setting click listeners for botton navigation buttons

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TinyDB tinyDB = new TinyDB(CartListActivity.this);
                ArrayList<Food> list = managementCart.getListCart();
                int noOfItems = list.size(); // no of food items in cart

                tinyDB.putListObject(LoginActivity.user,new ArrayList<Food>());
                Intent intent = new Intent(CartListActivity.this,MainActivity.class);
                ArrayList<Transaction> transaction = tinyDB.getListTransaction(LoginActivity.user + "Transactions");


                long millis = System.currentTimeMillis();
                // creating a new object of the class Date
                java.util.Date date = new java.util.Date(millis);
                for(int index = 0; index < noOfItems; ++index)
                {
                    transaction.add(new Transaction(LoginActivity.user,list.get(index),totalItemsFee.getText().toString(),deliveryFee.getText().toString(),taxFee.getText().toString(),date.toString(),totalFee.getText().toString()));
                }
                tinyDB.putListTransaction(LoginActivity.user + "Transactions",transaction);
                startActivity(intent);
            }
        });
    }

    private void initializeViews()
    {
        totalItemsFee = findViewById(R.id.item_total_price);
        recyclerView = findViewById(R.id.cart_list);
        scrollView = findViewById(R.id.scrollView3);
        deliveryFee = findViewById(R.id.delivery_services_price);
        taxFee = findViewById(R.id.tax_price);
        totalFee = findViewById(R.id.total_price);
        checkOutBtn = findViewById(R.id.checkOut);
        emptyCartTxt = findViewById(R.id.empty_cart_txt);

    }

    private void bottomNavigation()
    {
        FloatingActionButton floatingActionButton = findViewById(R.id.cart_button);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout orderInQueueBtn = findViewById(R.id.ordersBtn);
        LinearLayout transactionBtn = findViewById(R.id.transactionBtn);
        LinearLayout settingsBtn = findViewById(R.id.setttings_btn);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,SettingsActivity.class));
            }
        });
        orderInQueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartListActivity.this,OrdersActivity.class);
                startActivity(intent);
            }
        });

        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,TransactionActivity.class));
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartListActivity.this,CartListActivity.class);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void setCartList()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerView.setAdapter(adapter);

        if(managementCart.getListCart().isEmpty())
        {
            emptyCartTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else
        {
            emptyCartTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    public void calculateCart()
    {
        float taxPercent = (float) 0.06; // 6% percent tax
        int deliveryCharges = 60;

        double tax = Math.round((managementCart.getTotal(managementCart.getListCart()) * taxPercent)* 100) / 100;
        totalFee.setText("$ " + String.valueOf(Math.round((managementCart.getTotal(managementCart.getListCart()) + tax + deliveryCharges) * 100) / 100));
        deliveryFee.setText("$ " + String.valueOf(deliveryCharges));
        taxFee.setText("$ " + String.valueOf(tax));
        totalItemsFee.setText("$ " + String.valueOf(Math.round(managementCart.getTotal(managementCart.getListCart()) * 100) / 100));
    }


}
