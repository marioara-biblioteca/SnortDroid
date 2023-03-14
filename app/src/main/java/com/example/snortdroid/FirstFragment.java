package com.example.snortdroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FirstFragment extends Fragment {


    private Spinner action,protocol,rev;
    private Button btn;
    private String[] actionItems = new String[]{
            "accept", "reject", " drop"
    };
    private static final String icmp="ICMP";
    private static final String ip="IP";
    private static final String tcp="TCP";
    private static final String udp="UDP";
    private String[] revItems=new String[]{
            "1","2",  "3"
    };
    private int flag=-1;
    public FirstFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_first, container, false);

        action=rootView.findViewById(R.id.action);
        protocol=rootView.findViewById(R.id.protocol);
        rev=rootView.findViewById(R.id.rev);

        ArrayAdapter<String> actionAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item , actionItems);
        action.setAdapter(actionAdapter);
        action.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("action", (String) parent.getItemAtPosition(position));
                String actionStr=(String) parent.getItemAtPosition(position);
                switch (actionStr){
                    case icmp:
                        flag=0;
                        break;
                    case ip:
                        flag=1;
                        break;
                    case tcp:
                        flag=2;
                        break;
                    case udp:
                        flag=3;
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> protocolAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item , new String[]{icmp,ip,tcp,udp});
        action.setAdapter(protocolAdapter);
        action.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("protocol", (String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> revAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item , revItems);
        action.setAdapter(revAdapter);
        action.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("protocol", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        btn = (Button)rootView.findViewById(R.id.sendFirst);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent ;
                switch (flag){
                    case 0:
                        intent = new Intent(getActivity(), ICMPActivity.class);
                        break;
                    case 1:
                        intent=new Intent(getActivity(),IPActivity.class);
                        break;
                    case 2:
                        intent=new Intent(getActivity(),TCPActivity.class);
                        break;
                    case 3:
                        intent=new Intent(getActivity(),UDPActivity.class);
                        break;
                    default:
                        intent=null;

                }
                startActivity(intent);

            }
        });
        return rootView;
    }

}
