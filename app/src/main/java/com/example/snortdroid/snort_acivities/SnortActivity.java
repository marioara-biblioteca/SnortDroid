package com.example.snortdroid.snort_acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.snortdroid.R;

public class SnortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snort);
    }
    public void goToFragment1(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new FirstFragment()).commit();
    }

}