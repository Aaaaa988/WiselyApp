package com.kiselev.wiselyapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kiselev.wiselyapp.R;

import java.util.ArrayList;


public class FragmentBar extends Fragment {

    BarChart barChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar, container, false);

        barChart = view.findViewById(R.id.bar_chart);

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1,5));
        entries.add(new BarEntry(2,4));
        entries.add(new BarEntry(3,3));
        entries.add(new BarEntry(4,2));
        entries.add(new BarEntry(5,3));
        entries.add(new BarEntry(6,4));
        entries.add(new BarEntry(7,5));

        BarDataSet barDataSet = new BarDataSet(entries, "BarDataSet");
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("KeK");
        barChart.animateY(2000);


        return view;
    }
}