package com.example.snortdroid.snort_acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snortdroid.R;
import com.example.snortdroid.rules.SnortRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnortActivity extends AppCompatActivity {

    private SnortRule deserializeSnortRule(String serializedRule){
        SnortRule snortRule=new SnortRule();
        String[]parameters=serializedRule.split(";");
        //        return  this.action+";"+this.protocol+";"+this.message+";"+this.sourceNet+";"+this.sourcePort+";"+this.destNet+";"+this.destPort;
        snortRule.setAction(parameters[0]);
        snortRule.setProtocol(parameters[1]);
        snortRule.setMessage(parameters[2]);
        snortRule.setSourceNet(parameters[3]);
        snortRule.setSourcePort(Integer.parseInt(parameters[4]));
        snortRule.setDestNet(parameters[5]);
        snortRule.setDestPort(Integer.parseInt(parameters[6]));

        return snortRule;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snort);

        SharedPreferences sp=getSharedPreferences("snortRules1", MODE_PRIVATE);
        if(!sp.contains("ruleID")){
            SharedPreferences.Editor editor=sp.edit();
            editor.putInt("ruleID",0);
            editor.commit();
        }
       ListView rulesListView=findViewById(R.id.rulesListView);
        //ListView rulesListView=new ListView(SnortActivity.this);
        Button showRules;
        showRules=(Button)findViewById(R.id.showRules);
        showRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("snortRules1", MODE_PRIVATE);
                List<String> rules=new ArrayList<>();
                int img;
                for(int i=0;i<sp.getInt("ruleID",0);i++){
                    String rule=sp.getString(i+"","").toString();
                    SnortRule snortRule=deserializeSnortRule(rule);
                    rules.add(rule);
                    //TODO create rule from serialized string and create Adapter of "SnortRule" object
                }

                img=R.drawable.info1;

                CustomRuleAdapter rulesAdapter=new CustomRuleAdapter(SnortActivity.this, Arrays.copyOf(
                        rules.toArray(), rules.size(), String[].class),img);
                rulesListView.setAdapter(rulesAdapter);
                rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String r=rules.get(i);
                        Toast.makeText(SnortActivity.this,r, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Button backToMain=findViewById(R.id.fromSnortToMain);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void goToFragment1(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameID,new FirstFragment() ).commit();
    }


}