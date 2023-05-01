package com.example.snortdroid.snort_acivities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.snortdroid.rules.snort.SnortRule;
import com.example.snortdroid.R;
import com.example.snortdroid.rules.enums.Direction;
import com.example.snortdroid.rules.enums.HttpMethods;
import com.example.snortdroid.rules.enums.HttpStatusCodes;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {
    private SnortRule rule;
    private int flag=-1;
    public FirstFragment() {
        rule=new SnortRule();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Spinner actionSpinner,protocolSpinner;
        Button sendToFirstFrag;

        View rootView= inflater.inflate(R.layout.fragment_first, container, false);

        actionSpinner=rootView.findViewById(R.id.action);
        protocolSpinner=rootView.findViewById(R.id.protocol);


        ArrayAdapter<CharSequence> actionAdapter = ArrayAdapter.createFromResource(getContext(),R.array.actionSpinner, android.R.layout.simple_spinner_dropdown_item );
        actionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actionSpinner.setAdapter(actionAdapter);
        actionSpinner.setOnItemSelectedListener(new ActionSpinnerClass());

        ArrayAdapter<CharSequence> protocolAdapter = ArrayAdapter.createFromResource(getContext(), R.array.protoSpinner,android.R.layout.simple_spinner_dropdown_item );
        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        protocolSpinner.setAdapter(protocolAdapter);
        protocolSpinner.setOnItemSelectedListener(new ProtocolSpinnerClass());

        sendToFirstFrag = (Button)rootView.findViewById(R.id.sendFirst);

        EditText ttlText = new EditText(getContext());
        ttlText.setId(R.id.my_edit_text_1);
        sendToFirstFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText revText=(EditText) rootView.findViewById(R.id.rev);
                    rule.setRev(Integer.parseInt(revText.getText().toString()));
                    EditText content=rootView.findViewById(R.id.contentMatch);
                    rule.setContent(content.getText().toString());
                    EditText offset=rootView.findViewById(R.id.offset);
                    rule.setContentOffset(Integer.parseInt(offset.getText().toString()));
                }catch (Exception e){

                }
                AlertDialog.Builder alertDialog;
                switch (flag){
                    case 0:
                        alertDialog=new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("ICMP");

                        Spinner icmpSpinner=new Spinner(getContext());
                        ArrayAdapter<CharSequence> icmpTypes = ArrayAdapter.createFromResource(getContext(),R.array.icmpMessage, android.R.layout.simple_spinner_dropdown_item );
                        icmpTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        icmpSpinner.setAdapter(icmpTypes);
                        icmpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String icmp=(String) adapterView.getItemAtPosition(i);
                                rule.setProtocol(icmp);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        alertDialog.setView(icmpSpinner);

                        break;
                    case 1:

                        alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("IP");
                        alertDialog.setMessage("Enter ttl:");


                        alertDialog.setView(ttlText);
//

                        break;
                    case 2:
                        alertDialog=new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("TCP");

                        LinearLayout layoutMain = new LinearLayout(getContext());
                        layoutMain.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                        layoutMain.setOrientation(LinearLayout.VERTICAL);

                        LinearLayout layout1 = new LinearLayout(getContext());
                        LinearLayout layout2 = new LinearLayout(getContext());

                        Spinner httpMethods=new Spinner(getContext());
                        ArrayAdapter<CharSequence> httpMethodsAdapter = ArrayAdapter.createFromResource(getContext(),R.array.httpMethodsSpinner, android.R.layout.simple_spinner_dropdown_item );
                        httpMethodsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        httpMethods.setAdapter(httpMethodsAdapter);
                        httpMethods.setOnItemSelectedListener(new HttpMethodSpinnerClass());

                        Spinner httpCode=new Spinner(getContext());
                        ArrayAdapter<CharSequence> httpCodeAdapter = ArrayAdapter.createFromResource(getContext(),R.array.httpCodesSpinner, android.R.layout.simple_spinner_dropdown_item );
                        httpCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        httpCode.setAdapter(httpCodeAdapter);
                        httpCode.setOnItemSelectedListener(new HttpCodeSpinnerClass());


                        layout1.addView(httpMethods);
                        layout1.addView(httpCode);

                        String[] flags=getResources().getStringArray(R.array.tcpFlags);
                        List<CheckBox> checkBoxList=new ArrayList<>();
                        for(String flag : flags){
                            CheckBox cbflag=new CheckBox(getContext());
                            cbflag.setText(flag);
                            layout2.addView(cbflag);
                            checkBoxList.add(cbflag);
                        }
                        for(CheckBox cb:checkBoxList){
                            if(cb.isChecked())
                                rule.addToTcpFlagsList(cb.getText().toString());
                        }

                        layoutMain.addView(layout1);
                        layoutMain.addView(layout2);

                        alertDialog.setView(layoutMain);
                        break;
                    case 3:

                        alertDialog=new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("UDP");
                        LinearLayout layout3 = new LinearLayout(getContext());
                        Spinner directionSp=new Spinner(getContext());
                        ArrayAdapter<CharSequence> directionAdapter = ArrayAdapter.createFromResource(getContext(),R.array.direction, android.R.layout.simple_spinner_dropdown_item );
                        directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        directionSp.setAdapter(directionAdapter);
                        directionSp.setOnItemSelectedListener(new DirectionSpinnerClass());
                        layout3.addView(directionSp);
                        alertDialog.setView(layout3);

                        break;
                    default:
                        alertDialog=null;
                        break;

                }
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(flag==1){
                            rule.setTtl(Integer.parseInt(ttlText.getText().toString()));
                        }
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("snortRule",rule);
                        SecondFragment secondFragment=new SecondFragment();
                        secondFragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frameID,secondFragment).commit();
                        getActivity().getSupportFragmentManager().beginTransaction().remove(FirstFragment.this).commit();

                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();

            }
        });
        return rootView;
    }

    class HttpMethodSpinnerClass implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String httpMethod=(String) adapterView.getItemAtPosition(i);
            rule.setHttpMethod(HttpMethods.valueOf(httpMethod));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    class DirectionSpinnerClass implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String direction=(String) adapterView.getItemAtPosition(i);
            rule.setDirection(Direction.valueOf(direction));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    class HttpCodeSpinnerClass implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String httpCode=(String) adapterView.getItemAtPosition(i);
            rule.setHttpStatusCode(HttpStatusCodes.valueOf(httpCode));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
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
