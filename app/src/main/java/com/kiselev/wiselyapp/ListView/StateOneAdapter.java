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

        ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        StateOne state = states.get(position);

        viewHolder.flagView.setImageResource(state.getFlagImage());
        viewHolder.dayOfWeekView.setText(state.getDayOfWeek());
        viewHolder.spendView.setText(String.valueOf(state.getSpend()));
        viewHolder.incomeView.setText(String.valueOf(state.getIncome()));

        return convertView;
    }

    private static class ViewHolder {
        final ImageView flagView;
        final TextView dayOfWeekView, spendView, incomeView;
        ViewHolder(View view){
            flagView = (ImageView) view.findViewById(R.id.flag);
            dayOfWeekView = (TextView) view.findViewById(R.id.dayOfWeek);
            spendView = (TextView) view.findViewById(R.id.spend);
            incomeView = (TextView) view.findViewById(R.id.income);
        }
    }
}
