package com.kiselev.wiselyapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kiselev.wiselyapp.R;

import java.util.ArrayList;


public class FragmentPie extends Fragment {

    private PieChart pieChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void setupPieChart(){
        //pieChart.setHoleRadius(10);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Types");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);

    }

    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f,"typeOne"));
        entries.add(new PieEntry(0.2f,"typeTwo"));
        entries.add(new PieEntry(0.3f,"typeThree"));
        entries.add(new PieEntry(0.1f,"typeFour"));
        entries.add(new PieEntry(0.2f,"typeFive"));

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Types");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        dataSet.setDrawValues(true);
        dataSet.setValueFormatter(new PercentFormatter(pieChart));
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie, container, false);

        TextView textView = view.findViewById(R.id.text_view_one);
        pieChart = view.findViewById(R.id.pie_chart);

        setupPieChart();
        loadPieChartData();

        String title = getArguments().getString("title");

        textView.setText(title);

        return view;
    }
}