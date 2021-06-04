package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kiselev.wiselyapp.ListView.StateOne;
import com.kiselev.wiselyapp.ListView.StateOneAdapter;
import com.kiselev.wiselyapp.ListView.StateTwo;
import com.kiselev.wiselyapp.ListView.StateTwoAdapter;
import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_CommentDAO;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.Spend_TypeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Spend_Comment;
import com.kiselev.wiselyapp.database.entity.Spend_Income;
import com.kiselev.wiselyapp.database.entity.Spend_Type;
import com.kiselev.wiselyapp.database.entity.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SpendIncomeDayActivity extends AppCompatActivity {

    String[] dayOfWeek = {
            "Воскресенье",
            "Понедельник",
            "Вторник",
            "Среда",
            "Четверг",
            "Пятница",
            "Суббота"
    };

    TextView dateNumber, dateMonthAndYear, dateDayOfWeek;
    String[] date;

    ListView spend_incomeList;

    private Spend_IncomeDAO spend_incomeDAO;
    private Spend_TypeDAO spend_typeDAO;
    private Spend_CommentDAO spend_commentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_income_day);
        dateNumber = (TextView)findViewById(R.id.spend_income_day);
        dateMonthAndYear = (TextView)findViewById(R.id.spend_income_monthAndYear);
        dateDayOfWeek = (TextView)findViewById(R.id.spend_income_dayOfWeek);
        Bundle arguments = getIntent().getExtras();
        date = arguments.get("date").toString().split("/");
        String monthAndYear = "/"+date[1]+"/"+date[2];
        dateNumber.setText(date[0]);
        dateMonthAndYear.setText(monthAndYear);

        Calendar mycal = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));

        int dayWeek = mycal.get(Calendar.DAY_OF_WEEK);

        dateDayOfWeek.setText(dayOfWeek[dayWeek-1]);

        /*-БД-*/
        AppDatabase db = DBHelper.getInstance().getDatabase();
        spend_incomeDAO = db.spend_incomeDAO();
        spend_typeDAO = db.spend_typeDAO();
        spend_commentDAO = db.spend_commentDAO();
        /*----*/

        outputList(setData());
    }

    private ArrayList<StateTwo> setData() {
        ArrayList<StateTwo> states = new ArrayList<>();

        List<Spend_Income> spend_incomes = new ArrayList<Spend_Income>();

        spend_incomes = spend_incomeDAO.getAllSpend_IncomeWhereDayMonthYear(date[0], date[1], date[2]);

        for(int i = 0; i < spend_incomes.size(); i++){
            states.add(new StateTwo(i, spend_incomes.get(i).id,"№ "+(i+1) , "", "", "", 0));
        }

        for(int i = 0; i < states.size(); i++){
            if(spend_incomes.get(i).type == 0){
                states.get(i).setSpendIncomeDay("-"+String.valueOf(spend_incomes.get(i).amount) + " р");
                states.get(i).setType(spend_typeDAO.getTypeName(spend_incomes.get(i).id));
                states.get(i).setComment(spend_commentDAO.getComment(spend_incomes.get(i).id));
                states.get(i).setFlagImage(R.drawable.arrow1);
            }
            if(spend_incomes.get(i).type == 1){
                states.get(i).setSpendIncomeDay("+"+String.valueOf(spend_incomes.get(i).amount) + " р");
                states.get(i).setFlagImage(R.drawable.arrow2);
            }

        }

        return states;
    }

    private void outputList(ArrayList<StateTwo> states) {
        // получаем элемент ListView
        spend_incomeList = (ListView) findViewById(R.id.spend_incomeDayList);
        // создаем адаптер
        StateTwoAdapter stateAdapter = new StateTwoAdapter(this, R.layout.list_item_day, states);
        // устанавливаем адаптер
        spend_incomeList.setAdapter(stateAdapter);
        spend_incomeList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        addMultiChoiceModeListener(stateAdapter);
        addListenerOnItemList();
    }

    private void addListenerOnItemList() {
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                StateTwo selectedState = (StateTwo)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Был выбран " + selectedState.getIdInTable(), Toast.LENGTH_SHORT).show();
            }
        };

        spend_incomeList.setOnItemClickListener(itemListener);
    }

    private void addMultiChoiceModeListener(StateTwoAdapter stateAdapter) {
        spend_incomeList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                final int checkedCount = spend_incomeList.getCheckedItemCount();

                mode.setTitle("Выбрано дней "+checkedCount);
                stateAdapter.getItem(position).changeColor();
                stateAdapter.toggleSelection(position);

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.selected, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete:
                        SparseBooleanArray selected = stateAdapter.getSelectedIds();
                        String stringMonth = date[1];

                        for(int i = (selected.size()-1); i>= 0; i--){
                            if (selected.valueAt(i)){
                                StateTwo selecteditem = stateAdapter.getItem(selected.keyAt(i));
                                selecteditem.changeColor();
                            }
                        }
                        stateAdapter.removeSelection();

                        for(int i = (selected.size()-1); i>= 0; i--){
                            if (selected.valueAt(i)){
                                StateTwo selecteditem = stateAdapter.getItem(selected.keyAt(i));

                                outputList(setData());
                            }
                        }
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                SparseBooleanArray selected = stateAdapter.getSelectedIds();

                for(int i = (selected.size()-1); i>= 0; i--){
                    if (selected.valueAt(i)){
                        StateTwo selecteditem = stateAdapter.getItem(selected.keyAt(i));
                        selecteditem.changeColor();
                    }
                }
                stateAdapter.removeSelection();
            }
        });
    }
}