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
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.entity.Spend_Income;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class FragmentBar extends Fragment {

    BarChart barChart;
    private Spend_IncomeDAO spend_incomeDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar, container, false);

        AppDatabase db = DBHelper.getInstance().getDatabase();
        spend_incomeDAO = db.spend_incomeDAO();

        barChart = view.findViewById(R.id.bar_chart);

        String[] date = getArguments().getString("date").split("/");

        Calendar mycal = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), 1);

        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i = 1; i <= mycal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            Double summDaySpend = spend_incomeDAO.getSummSpendDayMonthYear(String.valueOf(i), date[1], date[2]);
            if(summDaySpend != null){
                entries.add(new BarEntry(i,summDaySpend.floatValue()));
            }else{
                entries.add(new BarEntry(i,0f));
            }

        }


        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setGranularity(1);
        barChart.getDescription().setText("");
        barChart.animateY(2000);


        return view;
    }
}