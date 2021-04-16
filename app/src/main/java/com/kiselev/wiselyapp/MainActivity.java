package com.kiselev.wiselyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_sp_in, button_analytic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        button_sp_in = (Button)findViewById(R.id.button_spend_income);
        button_analytic = (Button)findViewById(R.id.button_analytics);

        button_sp_in.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.kiselev.wiselyapp.SpendIncomeActivity");
                        startActivity(intent);
                    }
                }
        );

        button_analytic.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.kiselev.wiselyapp.AnalyticsActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}