package com.example.snortdroid.snort_acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.snortdroid.R;

public class SnortActivity extends AppCompatActivity {
    private TableLayout table;
    private FirstFragment ff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snort);

        ff= new FirstFragment();

        SharedPreferences sp=getSharedPreferences("snortRules1", MODE_PRIVATE);
        if(!sp.contains("ruleID")){
            SharedPreferences.Editor editor=sp.edit();
            editor.putInt("ruleID",0);
            editor.commit();
        }

        table=new TableLayout(this);
        TableRow.LayoutParams tlparams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);


        Button showRules;
        showRules=(Button)findViewById(R.id.showRules);
        showRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("snortRules1", MODE_PRIVATE);
                for(int i=0;i<sp.getInt("ruleID",0);i++){
                    TableRow row=new TableRow(SnortActivity.this);
                    String[]parameters=sp.getString(i+"","").toString().split(";");

                    for(int j=0;j<parameters.length;j++) {
                        TextView tv = new TextView(SnortActivity.this);
                        tv.setPadding(5, 5, 0, 5);
                        if(j%2==0) tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        else tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 35);
                        tv.setLayoutParams(tlparams);
                        tv.setText(parameters[j]);
                        row.addView(tv);
                    }
                    table.addView(row);
                }
                LinearLayout ll=findViewById(R.id.layoutForTable);
                ll.addView(table);
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
        getSupportFragmentManager().beginTransaction().replace(R.id.frameID,ff ).commit();
    }


}