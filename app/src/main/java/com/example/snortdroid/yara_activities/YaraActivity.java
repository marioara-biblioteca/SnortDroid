package com.example.snortdroid.yara_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.snortdroid.R;

public class YaraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yara);

        Button backToMain=findViewById(R.id.fromYaraToMain);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void insertMetadata(View v){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameYaraID,new YaraMetadataFragment() ).commit();

    }
}