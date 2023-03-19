package com.example.snortdroid.snort_acivities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.snortdroid.rules.Rule;
import com.example.snortdroid.rules.SnortRule;
import com.example.snortdroid.snort_acivities.protocol_activities.ICMPActivity;
import com.example.snortdroid.snort_acivities.protocol_activities.IPActivity;
import com.example.snortdroid.R;
import com.example.snortdroid.snort_acivities.protocol_activities.TCPActivity;
import com.example.snortdroid.snort_acivities.protocol_activities.UDPActivity;

public class FirstFragment extends Fragment {
    private SnortRule rule;

    private int flag=-1;
    public FirstFragment() {
        rule=new SnortRule();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Spinner action,protocol;
        EditText rev;
        Button btn;

        View rootView= inflater.inflate(R.layout.fragment_first, container, false);

        action=rootView.findViewById(R.id.action);
        protocol=rootView.findViewById(R.id.protocol);
        rev=rootView.findViewById(R.id.rev);

        ArrayAdapter<CharSequence> actionAdapter = ArrayAdapter.createFromResource(getContext(),R.array.actionSpinner, android.R.layout.simple_spinner_dropdown_item );
        actionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        action.setAdapter(actionAdapter);
        action.setOnItemSelectedListener(new ActionSpinnerClass());

        ArrayAdapter<CharSequence> protocolAdapter = ArrayAdapter.createFromResource(getContext(), R.array.protoSpinner,android.R.layout.simple_spinner_dropdown_item );
        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        protocol.setAdapter(protocolAdapter);
        protocol.setOnItemSelectedListener(new ProtocolSpinnerClass());


        btn = (Button)rootView.findViewById(R.id.sendFirst);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int rev=Integer.parseInt(rootView.findViewById(R.id.rev).toString());
                    rule.setRev(rev);
                    String content=rootView.findViewById(R.id.contentMatch).toString();
                    rule.setContent(content);
                    int offset=Integer.parseInt(rootView.findViewById(R.id.offset).toString());
                    rule.setContentOffset(offset);
                }catch (Exception e){

                }
                Intent intent ;
                switch (flag){
                    case 0:
                        intent = new Intent(getActivity(), ICMPActivity.class);
                        intent.putExtra("snortRule",rule);
                        break;
                    case 1:
                        intent=new Intent(getActivity(), IPActivity.class);
                        intent.putExtra("snortRule",rule);
                        break;
                    case 2:
                        intent=new Intent(getActivity(), TCPActivity.class);
                        intent.putExtra("snortRule",rule);
                        break;
                    case 3:
                        intent=new Intent(getActivity(), UDPActivity.class);
                        intent.putExtra("snortRule",rule);
                        break;
                    default:
                        intent=null;

                }
                startActivity(intent);

            }
        });
        return rootView;
    }
    class ActionSpinnerClass implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String actionStr=(String) adapterView.getItemAtPosition(i);
            rule.setAction(actionStr);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    class ProtocolSpinnerClass implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String[]protoArr=getResources().getStringArray(R.array.protoSpinner);
            String protoStr=(String) adapterView.getItemAtPosition(i);
            for(int j =0;j<protoArr.length;j++)
                if(protoArr[j].equals(protoStr)) {
                    flag = j;
                    rule.setProtocol(protoStr);
                }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

}
