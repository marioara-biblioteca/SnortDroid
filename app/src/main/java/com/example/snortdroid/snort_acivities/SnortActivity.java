package com.example.snortdroid.snort_acivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.example.snortdroid.R;
import com.example.snortdroid.chart.MainActivityChart;
import com.example.snortdroid.rules.enums.Protocol;
import com.example.snortdroid.rules.enums.RuleAction;
import com.example.snortdroid.rules.snort.SnortRule;
import com.example.snortdroid.rules.snort.SnortRuleDb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class SnortActivity extends AppCompatActivity {
    public static String homeNet;
    public static String externalNet;
    private int lastSid;
    private CustomRuleAdapter rulesAdapter ;
    public  static SnortRuleDb snortDatabase;
    private void getSharedPreferences(){
        SharedPreferences sp=getSharedPreferences("envVars",MODE_PRIVATE);
        this.homeNet=sp.getString("$HOME_NET","");
        this.externalNet=sp.getString("$EXTERNAL_NET","");
        this.lastSid=sp.getInt("sid",0);
    }
    private void saveLastSidToSP(int sid){
        SharedPreferences.Editor editor=getSharedPreferences("envVars",MODE_PRIVATE).edit();
        editor.putInt("sid",sid);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snort);

        snortDatabase=Room.databaseBuilder(
                SnortActivity.this,
                SnortRuleDb.class,
                "snortDB1"
        ).allowMainThreadQueries().build();;

        getSharedPreferences();


        Button insertRules=findViewById(R.id.insertRules);
        insertRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String action, String protocol, String sourceNet, String destNet, int sourcePort, int destPort, String message, int rev, int sid

                snortDatabase.snortRuleDAO().insertSnortRule(
                        new SnortRule(RuleAction.ALERT.name().toString(),
                                Protocol.TCP.name().toString(),
                                homeNet,
                                externalNet,
                                80,
                                80,
                                "http accept rule",
                                1,
                                lastSid++));
                snortDatabase.snortRuleDAO().insertSnortRule(
                        new SnortRule(RuleAction.DROP.name().toString(),
                                Protocol.TCP.name().toString(),
                                homeNet,
                                externalNet,
                                22,
                                22,
                                "ssh drop rule",
                                1,
                                lastSid++));
                snortDatabase.snortRuleDAO().insertSnortRule(
                        new SnortRule(RuleAction.REJECT.name().toString(),
                                Protocol.UDP.name().toString(),
                                homeNet,
                                externalNet,
                                53,
                                53,
                                "dns reject rule",
                                1,
                                lastSid++));
                saveLastSidToSP(lastSid);
            }
        });
        ListView rulesListView=findViewById(R.id.rulesListView);
        //ListView rulesListView=new ListView(SnortActivity.this);
        Button showRules=showRules=(Button)findViewById(R.id.showRules);
        showRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int img=R.drawable.info1;

                new AlertDialog.Builder(SnortActivity.this)
                        .setTitle("Date range")
                        .setMessage("Choose date range in which rule was added to the system:")
                        .setPositiveButton("Select", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                final Calendar c = Calendar.getInstance();
                                DatePickerDialog dpd = new DatePickerDialog(SnortActivity.this,
                                        new DatePickerDialog.OnDateSetListener() {
                                            @Override
                                            public void onDateSet(DatePicker view, int year,
                                                                  int monthOfYear, int dayOfMonth) {
                                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                                                //get all dates after selcted date
                                                String minDate= dtf.format(LocalDateTime.of(year,monthOfYear+1,dayOfMonth,0,0,0));
                                                List<SnortRule>  selectedRules =snortDatabase.snortRuleDAO().getAllSnortRules();
                                                //=snortDatabase.snortRuleDAO().getRulesByTimestamp(minDate,dtf.format(LocalDateTime.now()));

                                                //if(selectedRules.size()!=0)
                                                rulesAdapter =new CustomRuleAdapter(SnortActivity.this, selectedRules,img);
                                                rulesListView.setAdapter(rulesAdapter);
                                                rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                        Toast.makeText(SnortActivity.this,"Deleted: "+selectedRules.get(i).toString(), Toast.LENGTH_SHORT).show();
                                                        snortDatabase.snortRuleDAO().deleteSnortRule(selectedRules.get(i));
                                                    }
                                                });
                                            }
                                        }, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE));
                                dpd.show();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_menu_my_calendar)
                        .show();

            }
        });
        Button showStatistics=findViewById(R.id.showStatistics);
        showStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String>results=getRulesActionStatics();
                Intent intent=new Intent(SnortActivity.this, MainActivityChart.class);
                intent.putStringArrayListExtra("data", (ArrayList<String>) results);
                startActivity(intent);
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
    private List<String> getRulesActionStatics(){
        List<String> results=new ArrayList<>();
        List<String> actions=Arrays.asList( getResources().getStringArray(R.array.actionSpinner));
        for(String action:actions){
            int resultForAction=snortDatabase.snortRuleDAO().getSnortRulesByAction(action.toUpperCase()).size();
            results.add(String.valueOf((float)resultForAction));
        }
        return results;
    }


}