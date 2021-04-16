package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.kiselev.wiselyapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SpendIncomeActivity extends AppCompatActivity {

    TextView dateTextView;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_income);
        printTodayDate();
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
        };

        thread.start();


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
}