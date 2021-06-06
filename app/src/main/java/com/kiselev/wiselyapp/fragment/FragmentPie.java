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
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_TypeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Type;

import java.util.ArrayList;
import java.util.List;


public class FragmentPie extends Fragment {

    private PieChart pieChart;
    private TypeDAO typeDAO;
    private Spend_TypeDAO spend_typeDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void setupPieChart(){
        //pieChart.setHoleRadius(10);
        pieChart.setDrawHoleEnabled(true);
        //pieChart.setUsePercentValues(true);

        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Категории");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        /*l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);*/
        l.setEnabled(false);

    }



    private void loadPieChartData(ArrayList<PieEntry> entries){

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: PASTEL_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Категории");
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

        AppDatabase db = DBHelper.getInstance().getDatabase();
        typeDAO = db.typeDAO();
        spend_typeDAO = db.spend_typeDAO();

        TextView textView = view.findViewById(R.id.text_view_one);
        pieChart = view.findViewById(R.id.pie_chart);

        String[] date = getArguments().getString("date").split("/");

        ArrayList<PieEntry> entries = new ArrayList<>();

        Double sumSpendType;
        List<Type> typeList = typeDAO.getAllType();
        for (int i = 0; i <typeList.size(); i++){
            sumSpendType = spend_typeDAO.getSumByType(typeList.get(i).id, Integer.parseInt(date[1]),Integer.parseInt(date[2]));
            if(sumSpendType != null){
                entries.add(new PieEntry(sumSpendType.floatValue(), typeList.get(i).type_name));
            }
        }

        setupPieChart();
        loadPieChartData(entries);

        return view;
    }

    public static final int[] PASTEL_COLORS = {
            Color.parseColor("#e6194B"),
            Color.parseColor("#3cb44b"),
            Color.parseColor("#ffe119"),
            Color.parseColor("#4363d8"),
            Color.parseColor("#f58231"),
            Color.parseColor("#42d4f4"),
            Color.parseColor("#f032e6"),
            Color.parseColor("#fabed4"),
            Color.parseColor("#469990"),
            Color.parseColor("#dcbeff"),
            Color.parseColor("#9A6324"),
            Color.parseColor("#fffac8"),
            Color.parseColor("#800000"),
            Color.parseColor("#aaffc3"),
            Color.parseColor("#000075"),
            Color.parseColor("#a9a9a9")
    };
}