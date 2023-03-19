package com.example.snortdroid.snort_acivities.protocol_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.snortdroid.R;
import com.example.snortdroid.rules.SnortRule;
import com.example.snortdroid.snort_acivities.SecondFragment;

public class ICMPActivity extends AppCompatActivity  {
    private SnortRule rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icmpactivity);

        Spinner ttlSpinner=findViewById(R.id.ttlSpinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.icmpSpinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ttlSpinner.setAdapter(adapter);

        ttlSpinner.setOnItemSelectedListener(new TTLSpinnerClass() );

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            rule=(SnortRule) getIntent().getSerializableExtra("snortRule");
        }

        Button sendback=findViewById(R.id.sendICMP);
        sendback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                rule.setTtl(findViewById(R.id.ttlText).toString());

                Bundle bundle=new Bundle();
                bundle.putSerializable("snortRule",rule);
                SecondFragment secondFragment=new SecondFragment();
                secondFragment.setArguments(bundle);

                 androidx.fragment.app
                        .FragmentManager mFragmentManager
                        = getSupportFragmentManager();
                 androidx.fragment.app
                        .FragmentTransaction mFragmentTransaction
                        = mFragmentManager.beginTransaction();
                 mFragmentTransaction.add(R.id.frameID,secondFragment).commit();


            }
        });
    }

    class TTLSpinnerClass implements  AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String how=adapterView.getItemAtPosition(i).toString();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

}