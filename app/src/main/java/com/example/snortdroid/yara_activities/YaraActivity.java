package com.example.snortdroid.yara_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.snortdroid.R;
import com.example.snortdroid.rules.yara.YaraRule;
import com.example.snortdroid.rules.yara.YaraRuleDb;
import com.example.snortdroid.snort_acivities.CustomRuleAdapter;
import com.example.snortdroid.snort_acivities.SnortActivity;

import java.util.List;

public class YaraActivity extends AppCompatActivity {
    public static YaraRuleDb yaraDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yara);
        yaraDatabase = Room.databaseBuilder(
                YaraActivity.this,
                YaraRuleDb.class,
                "YaraDatabase"
        ).allowMainThreadQueries().build();

        List<YaraRule> yaraRuleList=yaraDatabase.yaraRuleDAO().getAllYaraRules();
       CustomRuleAdapter rulesAdapter =new CustomRuleAdapter(YaraActivity.this, yaraRuleList,R.drawable.info1);
        ListView rulesListView=findViewById(R.id.rulesListViewYara) ;
       rulesListView.setAdapter(rulesAdapter);
        rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(YaraActivity.this,"Deleted: "+yaraRuleList.get(i).toString(), Toast.LENGTH_SHORT).show();
                yaraDatabase.yaraRuleDAO().deleteYaraRule(yaraRuleList.get(i));
            }
        });
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