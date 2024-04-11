package com.example.foodapp;

import static com.example.foodapp.RegisterActivity.NAME;
import static com.example.foodapp.RegisterActivity.USERS_DATA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.jar.Attributes;

import Helper.TinyDB;

public class LoginActivity extends AppCompatActivity {

    EditText phNumber, password;
    private HashMap<String,User> hashMap;
    TextView loginBtn,registerTxt;
    TinyDB tinyDB;
    public static String user;
    public static String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);
        initialize();
        hashMap = pushDataToMap();

        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(phNumber.getText()).isEmpty() || String.valueOf(password.getText()).isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String searchKey = phNumber.getText().toString();
                    if(hashMap.containsKey(searchKey))
                    {
                        if(password.getText().toString().equals(hashMap.get(searchKey).getPassword()))
                        {
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            user = searchKey;
                            intent.putExtra(NAME,"Hi " + hashMap.get(searchKey).getName());
                            userName = hashMap.get(searchKey).getName();
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Account doesn't exist. Register yourself", Toast.LENGTH_SHORT).show();
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

    private ArrayList<User> getListUser()
    {
        return tinyDB.getListUser(USERS_DATA);
    }
    private void initialize()
    {
        tinyDB = new TinyDB(LoginActivity.this);
        phNumber = findViewById(R.id.login_phone);
        password = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        registerTxt = findViewById(R.id.register_txtView);

        phNumber.setText("");
        password.setText("");
    }
}
