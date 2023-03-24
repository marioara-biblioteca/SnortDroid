package com.example.snortdroid.yara_activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.snortdroid.R;
import com.example.snortdroid.rules.YaraRule;


public class YaraMetadataFragment extends Fragment {

    private YaraRule yaraRule;

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
        EditText date=(EditText) rootView.findViewById(R.id.date);
        EditText hash=(EditText) rootView.findViewById(R.id.hash);
        try {
            yaraRule.setDescription(desc.getText().toString());
            yaraRule.setAuthor(author.getText().toString());
            yaraRule.setReference(reference.getText().toString());
            yaraRule.setDate(date.getText().toString());
            yaraRule.setHash(hash.getText().toString());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rootView;
    }
}