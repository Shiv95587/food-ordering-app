package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Adapters.TransactionAdapter;
import Helper.RecyclerTouchListener;
import Helper.TinyDB;

public class TransactionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static final String ORDER = "order";
    // this map will store all transactions of a particular user
    HashMap<String,ArrayList<Transaction>> hashMap = new HashMap<>();
    public static final String TRANSACTION = "Transactions";
    ArrayList<String> datesList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_list);

        setTransactionRecyclerView();
        putTransactionToMap();
        setClickListener();
    }

    private void setClickListener()
    {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(TransactionActivity.this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String date = datesList.get(position);

                Intent intent = new Intent(TransactionActivity.this,SingleTransactionActivity.class);
                intent.putExtra(ORDER,hashMap.get(date));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void putTransactionToMap()
    {
        TinyDB tinyDB = new TinyDB(TransactionActivity.this);
        ArrayList<Transaction> transactions = tinyDB.getListTransaction(LoginActivity.user + TransactionActivity.TRANSACTION);

        for(Transaction transaction : transactions)
        {
            ArrayList<Transaction> temp = hashMap.get(transaction.getDate());
            if(temp == null) {
               temp = new ArrayList<Transaction>();
           }
           temp.add(transaction);
           hashMap.put(transaction.getDate(),temp);
        }
    }

    private void setTransactionRecyclerView()
    {
        recyclerView = findViewById(R.id.transaction_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TransactionActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        TinyDB tinyDB = new TinyDB(TransactionActivity.this);
        ArrayList<Transaction> transactions = tinyDB.getListTransaction(LoginActivity.user + TRANSACTION);

            // Using set to store dates so that duplicate dates gets deleted
            Set<String> dates = new HashSet<>();

            for(Transaction transaction : transactions)
            {
                String date = transaction.getDate();
                dates.add(date);
            }

            for(String date : dates)
            {
                Log.v("Date",date);
                datesList.add(date);
            }

            if(datesList.isEmpty())
            {
                Log.v("Date","No Transactions");
            }

            TransactionAdapter adapter = new TransactionAdapter(datesList,TransactionActivity.this);
            recyclerView.setAdapter(adapter);
    }
}
