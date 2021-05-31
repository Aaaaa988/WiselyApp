package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kiselev.wiselyapp.ListView.StateOne;
import com.kiselev.wiselyapp.ListView.StateOneAdapter;
import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.dialogs.DialogAddIncome;
import com.kiselev.wiselyapp.dialogs.DialogAddSpend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_income);
        printTodayDate();
        initTodayDate();
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

    private void addListenerOnItemList() {
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                StateOne selectedState = (StateOne)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Был выбран " + selectedState.getId(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        spend_incomeList.setOnItemClickListener(itemListener);
    }

    private ArrayList<StateOne> setData(int year, int month) {
        ArrayList<StateOne> states = new ArrayList<>();

        Calendar mycal = new GregorianCalendar(year, month, 1);

        int firstDayOfWeek = mycal.get(Calendar.DAY_OF_WEEK);
        for(int i = 0; i < mycal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            states.add(new StateOne(i,i+1+", "+dayOfWeek[(firstDayOfWeek-1+i)%7] , 300, 200, R.drawable.arrow3));
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
        DialogAddSpend dialog = new DialogAddSpend();
        dialog.show(getSupportFragmentManager(), "custom");

    }

    public void showDialogAddIncome(View v) {
        DialogAddIncome dialog = new DialogAddIncome();
        dialog.show(getSupportFragmentManager(), "custom");

    }

}