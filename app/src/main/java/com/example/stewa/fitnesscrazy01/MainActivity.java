package com.example.stewa.fitnesscrazy01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragmentContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }
            MainLoginFragment L = new MainLoginFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, L).commit();
        }
    }
}

