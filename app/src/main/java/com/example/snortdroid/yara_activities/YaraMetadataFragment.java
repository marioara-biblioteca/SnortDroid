package com.example.snortdroid.yara_activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.snortdroid.R;
import com.example.snortdroid.rules.yara.YaraRule;
import com.example.snortdroid.rules.yara.YaraStrings;

import java.util.Calendar;


public class YaraMetadataFragment extends Fragment {

    private YaraRule yaraRule;
    String size;

    public YaraMetadataFragment() {
        yaraRule=new YaraRule();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_yara_metadata, container, false);

        EditText desc=(EditText) rootView.findViewById(R.id.description);
        EditText author=(EditText) rootView.findViewById(R.id.author);
        EditText reference=(EditText) rootView.findViewById(R.id.reference);
        Button date=(Button) rootView.findViewById(R.id.date);
        EditText hash=(EditText) rootView.findViewById(R.id.hash);
        try {
            yaraRule.setDescription(desc.getText().toString());
            yaraRule.setAuthor(author.getText().toString());
            yaraRule.setReference(reference.getText().toString());
            yaraRule.setHash(hash.getText().toString());
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();

                    DatePickerDialog dpd = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    date.setText(dayOfMonth + "-"
                                            + (monthOfYear + 1) + "-" + year);
                                    yaraRule.setDate(year+"/"+monthOfYear+"/"+dayOfMonth);

                                        }
                            }, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE));
                    dpd.show();
                }
            });
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        ////////////////////////////////////////STRINGS///////////////////////////////////////////////////////////
        Button addStrings=(Button)rootView.findViewById(R.id.goToAddStrings);
        addStrings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout layout = new LinearLayout(getContext());

                layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.setOrientation(LinearLayout.VERTICAL);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Add Strings");
                alertDialog.setView(layout);

                EditText paramName=new EditText(getContext());
                paramName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                paramName.setHint("Param name: $");
                EditText paramValue=new EditText(getContext());
                paramValue.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                paramValue.setHint("Param Hint:");
                Spinner sizeTypesSpinner=new Spinner(getContext());
                ArrayAdapter<CharSequence> wordSizes = ArrayAdapter.createFromResource(getContext(),R.array.wordSize, android.R.layout.simple_spinner_dropdown_item );
                wordSizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sizeTypesSpinner.setAdapter(wordSizes);

                layout.addView(sizeTypesSpinner);
                layout.addView(paramName);
                layout.addView(paramValue);
                sizeTypesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        size=(String) adapterView.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        yaraRule.addString(new YaraStrings(paramName.getText().toString(),
                                paramValue.getText().toString(),size));
                    }
                });
                alertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();

            }
        });
////////////////////////////////////CONDITION/////////////////////////////////
        Button addCond=(Button)rootView.findViewById(R.id.goToAddCondition);
        addCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout layout = new LinearLayout(getContext());

                layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.setOrientation(LinearLayout.VERTICAL);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Add Conditions");
                alertDialog.setView(layout);

               EditText condition=new EditText(getContext());
               condition.setHint("Condition");
               condition.setInputType(InputType.TYPE_CLASS_TEXT);

                layout.addView(condition);

                alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        yaraRule.addCondition(condition.getText().toString());
                        //TODO same problem with text editor

                    }
                });
                alertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();

            }
        });

        Button close=(Button) rootView.findViewById(R.id.closeFragmentYaraStrings);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return rootView;
    }
}