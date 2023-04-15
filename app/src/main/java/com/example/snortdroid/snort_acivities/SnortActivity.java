package com.example.snortdroid.snort_acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.example.snortdroid.R;
import com.example.snortdroid.rules.snort.SnortRule;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class SnortActivity extends AppCompatActivity {
    private String homeNet;
    private String externalNet;
    private CustomRuleAdapter rulesAdapter ;
    private void getSharedPreferences(){
        SharedPreferences sp=getSharedPreferences("envVars",MODE_PRIVATE);
        this.homeNet=sp.getString("$HOME_NET","");
        this.externalNet=sp.getString("$EXTERNAL_NET","");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snort);
        getSharedPreferences();

        ListView rulesListView=findViewById(R.id.rulesListView);
        //ListView rulesListView=new ListView(SnortActivity.this);
        Button showRules;
        showRules=(Button)findViewById(R.id.showRules);
        showRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int img=R.drawable.info1;
                List<SnortRule> rules=new ArrayList<>(
                        // public SnortRule(String action, String protocol, String sourceNet, String destNet, int sourcePort, int destPort, String message, int rev, int sid)
                        Arrays.asList(
                                new SnortRule("alert","tcp","$HOME_NET","$EXTERNAL_NET",0,80,"Alert http traffic",2,100001),
                                new SnortRule("alert","tcp","$HOME_NET","$EXTERNAL_NET",80,80,"Alert http traffic",2,100001)
                        )
                );
                final List<String> selectedRules=new ArrayList<>();


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
                                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                                //get all dates after selcted date
                                                for(SnortRule rule:rules){
                                                    String[ ]date=dtf.format(rule.getTimestamp()).split(" ");
                                                    int selectedYear=Integer.parseInt(date[0].split("/")[0]);
                                                    int selectedMonth=Integer.parseInt(date[0].split("/")[1]);
                                                    int selectedDay= Integer.parseInt(date[0].split("/")[2]);
                                                    if(selectedDay==dayOfMonth && year==selectedYear &&  selectedMonth==monthOfYear +1){
                                                        selectedRules.add(rule.toString());
                                                    }
                                                }

                                                //if(selectedRules.size()!=0)
                                                rulesAdapter =new CustomRuleAdapter(SnortActivity.this, selectedRules,img);
                                                rulesListView.setAdapter(rulesAdapter);
                                                rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                        Toast.makeText(SnortActivity.this,rules.get(i).toString(), Toast.LENGTH_SHORT).show();
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