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

public class StateTwoAdapter extends ArrayAdapter<StateTwo> {
    private LayoutInflater inflater;
    private int layout;
    private List<StateTwo> states;

    private SparseBooleanArray mSelectedItemsIds = new SparseBooleanArray();

    public StateTwoAdapter(Context context, int resource, List<StateTwo> states) {
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
        StateTwo state = states.get(position);

        viewHolder.flagView.setImageResource(state.getFlagImage());
        viewHolder.numberOfListView.setText(state.getNumberOfList());
        viewHolder.spendIncomeDayView.setText(state.getSpendIncomeDay());
        viewHolder.typeView.setText(state.getType());
        viewHolder.commentView.setText(state.getComment());
        viewHolder.layout.setBackgroundColor(state.getColor());

        return convertView;
    }

    private static class ViewHolder {
        final ImageView flagView;
        final TextView numberOfListView, spendIncomeDayView, typeView, commentView;
        LinearLayout layout;
        ViewHolder(View view){
            flagView = (ImageView) view.findViewById(R.id.flag_day);
            numberOfListView = (TextView) view.findViewById(R.id.numberOfList);
            spendIncomeDayView = (TextView) view.findViewById(R.id.spend_income_day);
            typeView = (TextView) view.findViewById(R.id.type_day);
            commentView = (TextView) view.findViewById(R.id.comment_day);
            layout = (LinearLayout)view.findViewById(R.id.list_main_layout_day);
        }
    }

    public List<StateTwo> getStates() {
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
