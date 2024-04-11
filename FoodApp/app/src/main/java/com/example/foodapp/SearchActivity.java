package com.example.foodapp;

import static com.example.foodapp.MainActivity.NO_OF_RESULTS;
import static com.example.foodapp.MainActivity.SEARCH_RESULTS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapters.CategroyListAdapter;
import Helper.RecyclerTouchListener;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView noOfResultsTxt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        setSearchRecyclerView();
    }

    private void setSearchRecyclerView()
    {
        noOfResultsTxt = findViewById(R.id.no_of_search_results);
        recyclerView = findViewById(R.id.search_results_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        ArrayList<Food> foodArrayList = (ArrayList<Food>) intent.getSerializableExtra(SEARCH_RESULTS);
        String noOfResults = intent.getStringExtra(NO_OF_RESULTS);
        String key = intent.getStringExtra(MainActivity.KEY);
        noOfResultsTxt.setText(noOfResults + " results for \"" + key + "\"");
        CategroyListAdapter adapter = new CategroyListAdapter(this,foodArrayList);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Food selectedItem = foodArrayList.get(position);
                Intent intent = new Intent(SearchActivity.this,ShowDetailsActivity.class);
                intent.putExtra(CategoryListActivity.SELECTED,selectedItem);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(adapter);

    }
}
