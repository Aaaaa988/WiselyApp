package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private ImageButton button_sp_in, button_analytic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        button_sp_in = (ImageButton)findViewById(R.id.button_spend_income);
        button_analytic = (ImageButton)findViewById(R.id.button_analytics);

        button_sp_in.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.kiselev.wiselyapp.activities.SpendIncomeActivity");
                        startActivity(intent);
                    }
                }
        );

        button_analytic.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.kiselev.wiselyapp.activities.AnalyticsActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}