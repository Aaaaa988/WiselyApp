package com.kiselev.wiselyapp.ListView;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kiselev.wiselyapp.R;

import java.util.List;

public class StateOneAdapter extends ArrayAdapter<StateOne> {
    private LayoutInflater inflater;
    private int layout;
    private List<StateOne> states;

    private SparseBooleanArray mSelectedItemsIds = new SparseBooleanArray();

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
        viewHolder.spendView.setText(state.getSpend());
        viewHolder.incomeView.setText(state.getIncome());
        viewHolder.layout.setBackgroundColor(state.getColor());

        return convertView;
    }

    private static class ViewHolder {
        final ImageView flagView;
        final TextView dayOfWeekView, spendView, incomeView;
        LinearLayout layout;
        ViewHolder(View view){
            flagView = (ImageView) view.findViewById(R.id.flag);
            dayOfWeekView = (TextView) view.findViewById(R.id.dayOfWeek);
            spendView = (TextView) view.findViewById(R.id.spend);
            incomeView = (TextView) view.findViewById(R.id.income);
            layout = (LinearLayout)view.findViewById(R.id.list_main_layout);
        }
    }

    public void remove(StateOne object){
        states.remove(object);
        notifyDataSetChanged();
    }

    public List<StateOne> getStates() {
        return states;
    }

    public void toggleSelection(int position){
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection(){
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value){
        if(value){
            mSelectedItemsIds.put(position, value);
        }else{
            mSelectedItemsIds.delete(position);
        }
        notifyDataSetChanged();
    }

    public int getSelectedCount(){
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds(){
        return mSelectedItemsIds;
    }
}
