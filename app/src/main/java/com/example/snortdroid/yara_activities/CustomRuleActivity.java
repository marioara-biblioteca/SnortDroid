package com.example.snortdroid.yara_activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.snortdroid.R;

import com.example.snortdroid.rules.yara.YaraRule;

import java.util.List;

public class CustomRuleActivity extends BaseAdapter {
    private Context context;
    private List<YaraRule> rules;
    private int image;
    private LayoutInflater layoutInflater;

    public CustomRuleActivity(Context context, List<YaraRule> rules, int image) {
        this.context = context;
        this.rules = rules;
        this.image = image;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return rules.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=layoutInflater.inflate(R.layout.activity_rules_list_view,null);
        TextView ruleTv=(TextView) view.findViewById(R.id.rulesTextView);
        ImageView ruleImg=(ImageView) view.findViewById(R.id.rulesInfoIcon);
        ruleTv.setText(rules.get(i).toString());
        ruleImg.setImageResource(image);
        return view;
    }
}
