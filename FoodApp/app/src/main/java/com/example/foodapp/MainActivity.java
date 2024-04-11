package com.example.foodapp;

import static com.example.foodapp.RegisterActivity.NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapters.CategoryAdapter;
import Adapters.PopularAdapter;
import Helper.RecyclerTouchListener;

public class MainActivity extends AppCompatActivity {

    RecyclerView categortyListRecyclerView;
    RecyclerView popularListRecyclerView;
    private EditText searchBar;
    AVLTree items;
    public static final String KEY = "key";
    public static final String SEARCH_KEY = "search_key";
    public static final String NO_OF_RESULTS = "No of results";
    public static final String BUNDLE = "BUNDLE";
    public static final String SEARCH_RESULTS = "Search results";
    public static final String POSITION = "Position";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = mySPrefs.edit();
//        editor.remove("03326403399Transaction");
//        editor.apply();

        setCategoryRecyclerView();
        setPopularRecyclerView();
        bottomNavigation();


        TextView welcomeUserTxt = findViewById(R.id.textView);
        welcomeUserTxt.setText("Hi " + LoginActivity.userName);

        // inserting data to AVL Tree
        putDataToTree();

        searchBar = findViewById(R.id.editTextTextPersonName3);

        // Setting focus change listener to searchBar
        searchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus)
                {
                    if(!(String.valueOf(searchBar.getText()).isEmpty()))
                    {
                        ArrayList<Food> result = new ArrayList<>();
                        items.searchAllOccurrences(items.root,String.valueOf(searchBar.getText()),result); // result array will have search results
                        Intent intent1 = new Intent(MainActivity.this, SearchActivity.class);
                        intent1.putExtra(SEARCH_RESULTS,result);
                        intent1.putExtra(KEY,String.valueOf(searchBar.getText()));
                        intent1.putExtra(NO_OF_RESULTS,String.valueOf(result.size()));
                        startActivity(intent1);
                    }
                }
            }
        });
    }

    private void putDataToTree()
    {
        items = new AVLTree();

        ArrayList<Food> pizzas = CategoryListActivity.getAllPizzas();
        ArrayList<Food> burgers = CategoryListActivity.getAllBurgers();
        ArrayList<Food> hotDogs = CategoryListActivity.getAllHotDogs();
        ArrayList<Food> drinks = CategoryListActivity.getAllDrinks();
        ArrayList<Food> doughNuts = CategoryListActivity.getAllDoughNuts();
        for(int i = 0; i < pizzas.size(); ++i)
            items.root = items.insert(items.root,pizzas.get(i));

        for(int i = 0; i < burgers.size(); ++i)
            items.root = items.insert(items.root,burgers.get(i));

        for(int i = 0; i < hotDogs.size(); ++i)
            items.root = items.insert(items.root,hotDogs.get(i));

        for(int i = 0; i < drinks.size(); ++i)
            items.root = items.insert(items.root,drinks.get(i));

        for(int i = 0; i < doughNuts.size(); ++i)
            items.root = items.insert(items.root,doughNuts.get(i));
    }

    private void setPopularRecyclerView()
    {
        popularListRecyclerView = findViewById(R.id.popular_recyclerView);
        popularListRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        ArrayList<Food> foodItems = new ArrayList<>();
        foodItems.add( new Food("Pepperoni pizza",R.drawable.pizza,9.78,"slices pepperoni, mozzerella cheese, fresh oregano, ground black pepper, pizza sauce"));
        foodItems.add( new Food("Cheese Burger",R.drawable.pop_2,8.79,"beef, Gouda Cheese, Special Sauce, Lettuce, tomato"));
        foodItems.add( new Food("Vegetable pizza",R.drawable.pop_1,8.56,"olive oil, Vegetable oil, pitted kalamata, cherry tomatoes, fresh oregano, basil"));

        PopularAdapter adapter = new PopularAdapter(foodItems,MainActivity.this);
        popularListRecyclerView.setAdapter(adapter);
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
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            }
        });
        orderInQueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,OrdersActivity.class);
                startActivity(intent);
            }
        });

        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TransactionActivity.class));
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CartListActivity.class);
                startActivity(intent);
            }
        });


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setCategoryRecyclerView()
    {
        categortyListRecyclerView = findViewById(R.id.category_recycler_view);
        // horizontal linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        categortyListRecyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<Category> categories = new ArrayList<Category>();
        categories.add(new Category("Pizza",R.drawable.cat_1));
        categories.add(new Category("Burger",R.drawable.cat_2));
        categories.add(new Category("Hotdog",R.drawable.cat_3));
        categories.add(new Category("Drinks",R.drawable.cat_4));
        categories.add(new Category("Doughnut",R.drawable.cat_5));


        categortyListRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, categortyListRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // sending an intent from main activity to categoryList activity
                Intent intent = new Intent(MainActivity.this,CategoryListActivity.class);
                intent.putExtra(POSITION,String.valueOf(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }) {
        });

        CategoryAdapter adapter = new CategoryAdapter(categories,MainActivity.this);
        categortyListRecyclerView.setAdapter(adapter);
    }
}