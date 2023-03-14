package com.example.snortdroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SecondFragment extends Fragment {

    Button sendButton;
    EditText srcNet,destNet,srcPort,destPort;
    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_second, container, false);

        srcNet=(EditText)rootView.findViewById(R.id.sourceNet);
        srcPort=(EditText)rootView.findViewById(R.id.sourcePort);
        destNet=(EditText)rootView.findViewById(R.id.destNet);
        destPort=(EditText)rootView.findViewById(R.id.destPort);

        sendButton = (Button)rootView.findViewById(R.id.sendSecond);
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println( srcNet.getText().toString() + srcPort.getText() + destNet.getText() + destPort.getText());
            }
        });
        return rootView;

    }
}