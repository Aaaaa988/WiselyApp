package com.kiselev.wiselyapp.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiselev.wiselyapp.R;

import java.util.List;

public class StateOneAdapter extends ArrayAdapter<StateOne> {
    private LayoutInflater inflater;
    private int layout;
    private List<StateOne> states;

    public StateOneAdapter(Context context, int resource, List<StateOne> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.flag);
        TextView dayOfWeekView = (TextView) view.findViewById(R.id.dayOfWeek);
        TextView spendView = (TextView) view.findViewById(R.id.spend);
        TextView incomeView = (TextView) view.findViewById(R.id.income);

        StateOne state = states.get(position);

        flagView.setImageResource(state.getFlagImage());
        dayOfWeekView.setText(state.getDayOfWeek());
        spendView.setText(String.valueOf(state.getSpend()));
        incomeView.setText(String.valueOf(state.getIncome()));

        return view;
    }
}
