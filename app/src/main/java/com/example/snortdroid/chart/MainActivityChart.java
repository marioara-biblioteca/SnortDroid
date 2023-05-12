package com.example.snortdroid.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.snortdroid.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivityChart extends AppCompatActivity {
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;
    List<Float> dataValues=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chart);
        pieChart = findViewById(R.id.pieChart);

        if(getIntent().getExtras()!=null){
            List<String>stringRulesStatistics=getIntent().getStringArrayListExtra("data");
            for(int i=0;i<stringRulesStatistics.size();i++)
                dataValues.add(Float.parseFloat(stringRulesStatistics.get(i)));
        }

        if(!dataValues.isEmpty())
            getEntries(dataValues);
        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(5f);
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
    }
    private void getEntries(List<Float> values) {
        pieEntries = new ArrayList<>();
        for (int i=0;i<values.size();i++)
            pieEntries.add(new PieEntry((float)values.get(i),i));


    }
}