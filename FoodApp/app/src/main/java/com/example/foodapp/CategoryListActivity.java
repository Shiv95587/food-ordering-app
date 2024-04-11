package com.example.foodapp;

import static com.example.foodapp.MainActivity.POSITION;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapters.CategroyListAdapter;
import Helper.RecyclerTouchListener;

public class CategoryListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Food> list;
    Intent intent;
    public static final String SELECTED = "object";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        intent = getIntent();
        setCategoryListRecyclerView();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Food selectedItem = list.get(position);
                Intent intent = new Intent(CategoryListActivity.this,ShowDetailsActivity.class);
                intent.putExtra(SELECTED,selectedItem);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void setCategoryListRecyclerView()
    {
        recyclerView = findViewById(R.id.category_list_item_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        switch (Integer.parseInt(intent.getStringExtra(POSITION)))
        {
            case 0:
                list = getAllPizzas();
                break;
            case 1:
                list = getAllBurgers();
                break;
            case 2:
                list = getAllHotDogs();
                break;
            case 3:
                list = getAllDrinks();
                break;
            case 4:
                list = getAllDoughNuts();
                break;
        }
        CategroyListAdapter categroyListAdapter = new CategroyListAdapter(this,list);
        recyclerView.setAdapter(categroyListAdapter);
    }

    public static ArrayList<Food> getAllPizzas()
    {
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList.add(new Food("Medium Chicken Tikka Pizza",R.drawable.pop_1,7.3,"Stuffed Crust"));
        foodArrayList.add(new Food("Small Chicken Tikka Pizza",R.drawable.pop_1,5.0,"Stuffed Crust"));
        foodArrayList.add(new Food("Large Chicken Tikka Pizza",R.drawable.pop_1,9.0,"Stuffed Crust"));
        foodArrayList.add(new Food("Small Veg Pizza",R.drawable.pop_1,4.0,"Tomates, Cabage"));
        foodArrayList.add(new Food("Medium Veg Pizza",R.drawable.pop_1,6.5,"Tomates, Cabage"));
        foodArrayList.add(new Food("Large Veg Pizza",R.drawable.pop_1,8.0,"Tomates, Cabage"));
        return foodArrayList;
    }

    public static ArrayList<Food> getAllBurgers()
    {
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList.add(new Food("Small Cheese Burger",R.drawable.pop_2,4.0,"Small Cheese burger with no extra cheese"));
        foodArrayList.add(new Food("Medium Cheese Burger",R.drawable.pop_2,6.0,"Medium Cheese burger with extra cheese"));
        foodArrayList.add(new Food("Large Cheese Burger",R.drawable.pop_2,8.0,"Large Cheese burger with extra cheese"));
        return foodArrayList;
    }

    public static ArrayList<Food> getAllHotDogs()
    {
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList.add(new Food("Small Hot Dog",R.drawable.cat_3,2.0,"Hotdog with tomatoes"));
        foodArrayList.add(new Food("Medium Hot Dog",R.drawable.cat_3,3.0,"Hotdog with tomatoes"));
        foodArrayList.add(new Food("Large Hot Dog",R.drawable.cat_3,4.0,"Hotdog with tomatoes"));
        return foodArrayList;
    }

    public static ArrayList<Food> getAllDrinks()
    {
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList.add(new Food("7 up",R.drawable.cat_4,1.0,"Lemon flavour"));
        foodArrayList.add(new Food("Pepsi",R.drawable.cat_4,1.25,"Chilled"));
        foodArrayList.add(new Food("Coke",R.drawable.cat_4,1.5,"Coca cola"));
        return foodArrayList;
    }

    public static ArrayList<Food> getAllDoughNuts() {
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList.add(new Food("Small DoughNut",R.drawable.cat_5,2.0,"Small Chocolate DoughNut"));
        foodArrayList.add(new Food("Medium DoughNut",R.drawable.cat_5,3.0,"Medium Chocolate DoughNut"));
        foodArrayList.add(new Food("Large DoughNut",R.drawable.cat_5,4.0,"Large Chocolate DoughNut"));
        return foodArrayList;
    }
}
