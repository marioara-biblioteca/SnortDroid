package com.example.snortdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.snortdroid.snort_acivities.SnortActivity;
import com.example.snortdroid.yara_activities.YaraActivity;
import com.example.snortdroid.zeek_activities.ZeekActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.rule_types_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.yara: {
                Intent intent = new Intent(this, YaraActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.snort: {
                Intent intent = new Intent(this, SnortActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.zeek:{
                Intent intent = new Intent(this, ZeekActivity.class);
                startActivity(intent);
                break;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}