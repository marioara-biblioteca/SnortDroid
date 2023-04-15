package com.example.snortdroid.snort_acivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.snortdroid.R;
import com.example.snortdroid.rules.snort.SnortRule;

public class SecondFragment extends Fragment {
    private SnortRule rule;
    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_second, container, false);

        if(getArguments()!=null) {
            rule = getArguments().getSerializable("snortRule", SnortRule.class);


            Button sendButton = (Button) rootView.findViewById(R.id.sendSecond);
            sendButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    EditText srcNet=(rootView.findViewById(R.id.sourceNet));
                    EditText destNet = rootView.findViewById(R.id.destNet);
                    EditText message=rootView.findViewById(R.id.message);
                    EditText classType=rootView.findViewById(R.id.classType);

                    rule.setSourceNet(srcNet.getText().toString());
                    rule.setDestNet(destNet.getText().toString());
                    rule.setMessage(message.getText().toString());

                    try {
                        EditText srcPort = rootView.findViewById(R.id.sourcePort);
                        EditText destPort = rootView.findViewById(R.id.destPort);
                        EditText sid = rootView.findViewById(R.id.sid);
                        rule.setSourcePort(Integer.parseInt(srcPort.getText().toString()));
                        rule.setDestPort(Integer.parseInt(destPort.getText().toString()));
                        rule.setSid(Integer.parseInt(sid.getText().toString()));



                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }


                }
            });
            Button goBack=(Button) rootView.findViewById(R.id.closeFragment2Snort);
            goBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });

        }
        return rootView;

    }

}