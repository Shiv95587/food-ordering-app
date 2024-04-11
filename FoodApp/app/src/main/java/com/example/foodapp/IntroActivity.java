package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000); // stops screen for 3 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(IntroActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}
