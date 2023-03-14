package com.example.snortdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToFragment1(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new FirstFragment()).commit();
    }

    public void goToFragment2(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new SecondFragment()).commit();
    }
}