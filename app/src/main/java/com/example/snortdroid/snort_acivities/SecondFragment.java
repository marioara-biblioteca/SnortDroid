package com.example.snortdroid.snort_acivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.snortdroid.R;
import com.example.snortdroid.rules.SnortRule;

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
                    String srcNet = rootView.findViewById(R.id.sourceNet).toString();
                    String destNet = rootView.findViewById(R.id.destNet).toString();
                    String message=rootView.findViewById(R.id.message).toString();
                    String classType=rootView.findViewById(R.id.classType).toString();
                    rule.setSourceNet(srcNet);
                    rule.setDestNet(destNet);
                    rule.setMessage(message);

                    try {
                        int srcPort = Integer.parseInt(rootView.findViewById(R.id.sourcePort).toString());
                        int destPort = Integer.parseInt(rootView.findViewById(R.id.destPort).toString());
                        int sid = Integer.parseInt(rootView.findViewById(R.id.sid).toString());
                        rule.setSourcePort(srcPort);
                        rule.setDestPort(destPort);
                        rule.setSid(sid);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }


                }
            });
        }
        return rootView;

    }
}