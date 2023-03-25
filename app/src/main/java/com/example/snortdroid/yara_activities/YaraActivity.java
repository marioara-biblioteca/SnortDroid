package com.example.snortdroid.yara_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.snortdroid.R;

public class YaraActivity extends AppCompatActivity {

    private void test(){
        SharedPreferences sp=getSharedPreferences("yaraRules", MODE_PRIVATE);
        int ruleNum=sp.getInt("ruleNum",0);
        String rule="";
        for (int i=0;i<ruleNum;i++) {
            sp.getString("yaraRule", rule);
            Log.d("YARA",rule);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yara);

        test();



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