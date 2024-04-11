package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import Helper.TinyDB;

public class RegisterActivity extends AppCompatActivity {

    EditText name, phNumber, password;
    TextView registerBtn;
    private TinyDB tinyDB;
    static public final String NAME = "name";
    static public final String USERS_DATA = "users_data";
    private HashMap<String,User> hashMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();

       hashMap = pushDataToMap();
        
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (String.valueOf(phNumber.getText()).isEmpty() || String.valueOf(password.getText()).isEmpty() || String.valueOf(name.getText()).isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    String key = String.valueOf(phNumber.getText());
                    if(hashMap.containsKey(key))
                    {
                        Toast.makeText(RegisterActivity.this,"You are already registered", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String phoneNumber = String.valueOf(phNumber.getText());
                        String pass = String.valueOf(password.getText());
                        String nameOfUser = String.valueOf(name.getText());
                        // creating a new user
                        User newUser = new User(nameOfUser,phoneNumber,pass);
                        ArrayList<User> users = getListUser();
                        // adding a new user
                        users.add(newUser);
                       // hashMap.put(phoneNumber,newUser);
                        tinyDB.putListUser(USERS_DATA,users);

                        Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        intent.putExtra(NAME,String.valueOf("Hi " + name.getText()));
                        startActivity(intent);
                    }
                }
            }
        });
    }

    // this function transfers all users data from ArrayList to hashMap 
    HashMap<String,User> pushDataToMap()
    {
        ArrayList<User> users = getListUser();
        int noOfUsers = users.size();
        HashMap<String,User> hashMap = new HashMap<>();
        for(int i = 0; i < noOfUsers; ++i)
        {
            String key = users.get(i).getPhoneNumber();
            hashMap.put(key, users.get(i));
        }
        return hashMap;
    }
    // this function returns all users data
    private ArrayList<User> getListUser()
    {
        return tinyDB.getListUser(USERS_DATA);
    }
    private void initialize()
    {
        tinyDB = new TinyDB(RegisterActivity.this);
        name = findViewById(R.id.register_name);
        phNumber = findViewById(R.id.register_phone);
        password = findViewById(R.id.register_password);
        registerBtn = findViewById(R.id.register_btn);
        name.setText("");
        phNumber.setText("");
        password.setText("");
    }
}
