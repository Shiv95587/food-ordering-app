package com.example.foodapp;

import static com.example.foodapp.TransactionActivity.ORDER;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapters.SingleTransactionAdapter;

public class SingleTransactionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView totalTxt;
    private TextView totalItemsFee,deliveryFee,taxFee;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        setRecyclerView();

    }

    private void setRecyclerView()
    {
        recyclerView = findViewById(R.id.single_order_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(SingleTransactionActivity.this,RecyclerView.VERTICAL,false));
        Intent intent = getIntent();
        ArrayList<Transaction> transactions = (ArrayList<Transaction>)intent.getSerializableExtra(ORDER);
        totalTxt = findViewById(R.id.total_order_fee);
        totalTxt.setText(transactions.get(0).getTotal());
        totalItemsFee = findViewById(R.id.order_item_total_price);
        deliveryFee = findViewById(R.id.order_delivery_services_price);
        taxFee = findViewById(R.id.order_tax_price);

        totalItemsFee.setText(transactions.get(0).getFoodTotalFee());
        deliveryFee.setText(transactions.get(0).getDeliveryFee());
        taxFee.setText(transactions.get(0).getTax());
        SingleTransactionAdapter adapter = new SingleTransactionAdapter(transactions,SingleTransactionActivity.this);
        recyclerView.setAdapter(adapter);
    }
}
