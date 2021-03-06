package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kiselev.wiselyapp.ListView.StateOne;
import com.kiselev.wiselyapp.ListView.StateOneAdapter;
import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Spend_Income;
import com.kiselev.wiselyapp.database.entity.Type;
import com.kiselev.wiselyapp.dialogs.DialogAddIncome;
import com.kiselev.wiselyapp.dialogs.DialogAddSpend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class SpendIncomeActivity extends AppCompatActivity {

    TextView dateTextView;
    TextView year, month;
    Thread thread;

    ListView spend_incomeList;

    Button yearLess, yearMore, monthLess, monthMore;


    String[] monthNames = {
            "Январь",
            "Февраль",
            "Март",
            "Апрель",
            "Май",
            "Июнь",
            "Июль",
            "Август",
            "Сентябрь",
            "Октябрь",
            "Ноябрь",
            "Декабрь"
    };

    String[] dayOfWeek = {
            "вс",
            "пн",
            "вт",
            "ср",
            "чт",
            "пт",
            "сб"
    };


    private Spend_IncomeDAO spend_incomeDAO;
    int index = 0, top = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_income);
        printTodayDate();
        initTodayDate();

        /*-БД-*/
        AppDatabase db = DBHelper.getInstance().getDatabase();
        spend_incomeDAO = db.spend_incomeDAO();
        /*----*/

        addListenerOnButton();

        outputList(setData(Integer.parseInt(
                year.getText().toString()),
                Arrays.asList(monthNames).indexOf(month.getText().toString()))
        );


        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                printTodayDate();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };thread.start();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        outputList(setData(Integer.parseInt(
                year.getText().toString()),
                Arrays.asList(monthNames).indexOf(month.getText().toString()))
        );
        spend_incomeList.setSelectionFromTop(index, top);
    }

    public void savePositionListView(){
        index = spend_incomeList.getFirstVisiblePosition();
        View v = spend_incomeList.getChildAt(0);
        top = (v == null) ? 0 : (v.getTop() - spend_incomeList.getPaddingTop());
    }

    private void addListenerOnItemList() {
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                StateOne selectedState = (StateOne)parent.getItemAtPosition(position);

                savePositionListView();

                Intent intent = new Intent("com.kiselev.wiselyapp.activities.SpendIncomeDayActivity");
                String stringMonth = String.valueOf(Arrays.asList(monthNames).indexOf(month.getText().toString()));
                intent.putExtra("date", (selectedState.getId()+1)+"/"+stringMonth+"/"+year.getText().toString());
                startActivity(intent);
            }
        };

        spend_incomeList.setOnItemClickListener(itemListener);
    }

    private void addMultiChoiceModeListener(StateOneAdapter stateAdapter) {
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
                        String stringMonth = String.valueOf(Arrays.asList(monthNames).indexOf(month.getText().toString()));

                        for(int i = (selected.size()-1); i>= 0; i--){
                            if (selected.valueAt(i)){
                                StateOne selecteditem = stateAdapter.getItem(selected.keyAt(i));
                                selecteditem.changeColor();
                            }
                        }
                        stateAdapter.removeSelection();

                        for(int i = (selected.size()-1); i>= 0; i--){
                            if (selected.valueAt(i)){
                                StateOne selecteditem = stateAdapter.getItem(selected.keyAt(i));

                                spend_incomeDAO.deleteAllSpend_IncomeWhereDayMonthYear(String.valueOf(selecteditem.getId()+1),stringMonth,year.getText().toString());

                                outputList(setData(Integer.parseInt(
                                        year.getText().toString()),
                                        Arrays.asList(monthNames).indexOf(month.getText().toString()))
                                );
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
                        StateOne selecteditem = stateAdapter.getItem(selected.keyAt(i));
                        selecteditem.changeColor();
                    }
                }
                stateAdapter.removeSelection();
            }
        });
    }

    private ArrayList<StateOne> setData(int year, int month) {
        ArrayList<StateOne> states = new ArrayList<>();

        List<Spend_Income> spend_incomes = new ArrayList<Spend_Income>();

        spend_incomes = spend_incomeDAO.getAllSpend_IncomeWhereMonthAndYear(String.valueOf(month), String.valueOf(year));


        Calendar mycal = new GregorianCalendar(year, month, 1);
        Calendar calendar = Calendar.getInstance();


        int firstDayOfWeek = mycal.get(Calendar.DAY_OF_WEEK);
        for(int i = 0; i < mycal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){

            if(calendar.get(Calendar.DAY_OF_MONTH) == i+1 && calendar.get(Calendar.MONTH) == month && calendar.get(Calendar.YEAR) == year){
                states.add(new StateOne(i,i+1+", "+dayOfWeek[(firstDayOfWeek-1+i)%7]+" *Сегодня*" , " ", " ", 0, Color.parseColor("#d1fffb")));
            }else{
                if((firstDayOfWeek-1+i)%7 == 0 || (firstDayOfWeek-1+i)%7 == 6){
                    if((firstDayOfWeek-1+i)%7 == 0)
                        states.add(new StateOne(i,i+1+", "+dayOfWeek[(firstDayOfWeek-1+i)%7] , " ", " ", 0, Color.parseColor("#ffe3e3")));
                    if((firstDayOfWeek-1+i)%7 == 6)
                        states.add(new StateOne(i,i+1+", "+dayOfWeek[(firstDayOfWeek-1+i)%7] , " ", " ", 0, Color.parseColor("#ffedee")));
                }else{
                    states.add(new StateOne(i,i+1+", "+dayOfWeek[(firstDayOfWeek-1+i)%7] , " ", " ", 0, Color.WHITE));
                }
            }
        }

        for(int i = 0; i < states.size(); i++){
            double summ_spend, summ_income;
            int currentDay = i+1;
            summ_spend = 0;
            summ_income = 0;
            for(int j = 0; j < spend_incomes.size(); j++){
                String[] date = spend_incomes.get(j).date.split("/");
                if (date[0].equals(String.valueOf(currentDay)) && spend_incomes.get(j).type == 0){
                    summ_spend += spend_incomes.get(j).amount;
                }
                if (date[0].equals(String.valueOf(currentDay)) && spend_incomes.get(j).type == 1){
                    summ_income += spend_incomes.get(j).amount;
                }
            }

            if (summ_spend != 0 && summ_income != 0){
                String spend_str = String.format(Locale.ENGLISH,"%.2f",summ_spend);
                String income_str = String.format(Locale.ENGLISH,"%.2f",summ_income);

                states.get(i).setIncome("+" + income_str + " р");
                states.get(i).setSpend("-" + spend_str + " р");
                states.get(i).setFlagImage(R.drawable.arrow3);
            }else{
                if(summ_income != 0 ){
                    String income_str = String.format(Locale.ENGLISH,"%.2f",summ_income);
                    states.get(i).setFlagImage(R.drawable.arrow2);
                    states.get(i).setIncome("+" + income_str + " р");
                }
                if(summ_spend != 0) {
                    String spend_str = String.format(Locale.ENGLISH,"%.2f",summ_spend);
                    states.get(i).setFlagImage(R.drawable.arrow1);
                    states.get(i).setSpend("-" + spend_str + " р");
                }
            }
        }

        return states;
    }

    private void outputList(ArrayList<StateOne> states) {
        // получаем элемент ListView
        spend_incomeList = (ListView) findViewById(R.id.spend_incomeList);
        // создаем адаптер
        StateOneAdapter stateAdapter = new StateOneAdapter(this, R.layout.list_item, states);
        // устанавливаем адаптер
        spend_incomeList.setAdapter(stateAdapter);
        spend_incomeList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        addMultiChoiceModeListener(stateAdapter);
        addListenerOnItemList();
    }

    public void addListenerOnButton(){
        yearLess = (Button)findViewById(R.id.yearLess);
        yearMore = (Button)findViewById(R.id.yearMore);
        monthLess = (Button)findViewById(R.id.monthLess);
        monthMore = (Button)findViewById(R.id.monthMore);

        year = (TextView)findViewById(R.id.year1);
        month = (TextView)findViewById(R.id.month1);

        yearLess.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int year_temp = Integer.parseInt(year.getText().toString());
                        if(year_temp > 1990 && year_temp <= 2100){
                            year_temp -= 1;
                            year.setText(String.valueOf(year_temp));

                            outputList(setData(Integer.parseInt(
                                    year.getText().toString()),
                                    Arrays.asList(monthNames).indexOf(month.getText().toString()))
                            );
                        }

                    }
                }
        );

        yearMore.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int year_temp = Integer.parseInt(year.getText().toString());
                        if(year_temp >= 1990 && year_temp < 2100){
                            year_temp += 1;
                            year.setText(String.valueOf(year_temp));

                            outputList(setData(Integer.parseInt(
                                    year.getText().toString()),
                                    Arrays.asList(monthNames).indexOf(month.getText().toString()))
                            );
                        }
                    }
                }
        );

        monthLess.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int month_temp = Arrays.asList(monthNames).indexOf(month.getText().toString());
                        month_temp -= 1;
                        if(month_temp < 0) month_temp = 11;
                        month.setText(monthNames[month_temp]);

                        outputList(setData(Integer.parseInt(
                                year.getText().toString()),
                                Arrays.asList(monthNames).indexOf(month.getText().toString()))
                        );
                    }
                }
        );

        monthMore.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int month_temp = Arrays.asList(monthNames).indexOf(month.getText().toString());
                        month_temp += 1;
                        if(month_temp > 11) month_temp = 0;
                        month.setText(monthNames[month_temp]);

                        outputList(setData(Integer.parseInt(
                                year.getText().toString()),
                                Arrays.asList(monthNames).indexOf(month.getText().toString()))
                        );
                    }
                }
        );
    }

    private void initTodayDate() {
        year = (TextView)findViewById(R.id.year1);
        month = (TextView)findViewById(R.id.month1);
        Calendar calendar = Calendar.getInstance();

        year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        month.setText(monthNames[calendar.get(Calendar.MONTH)]);
    }

    public void printTodayDate(){
        dateTextView = (TextView)findViewById(R.id.dateTextView);

        // Текущее время
        Date currentDate = new Date();

        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        // Форматирование времени как "часы:минуты:секунды"
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        dateTextView.setText(dateText + " " +timeText);

    }

    public void showDialogAddSpend(View v) {
        savePositionListView();
        DialogAddSpend dialog = new DialogAddSpend();
        dialog.show(getSupportFragmentManager(), "custom");
    }

    public void showDialogAddIncome(View v) {
        savePositionListView();
        DialogAddIncome dialog = new DialogAddIncome();
        dialog.show(getSupportFragmentManager(), "custom");

    }

}