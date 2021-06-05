package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Type;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private ImageButton button_sp_in, button_analytic, button_bd_settings;
    TextView balance;

    TypeDAO typeDAO;
    Spend_IncomeDAO spend_incomeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = DBHelper.getInstance().getDatabase();
        typeDAO = db.typeDAO();
        spend_incomeDAO = db.spend_incomeDAO();
        checkAvailabilityTypes();
        balance = (TextView)findViewById(R.id.balance);
        getBalance();

        addListenerOnButton();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getBalance();
    }

    private void getBalance() {
        if(spend_incomeDAO.getSummIncome() != null && spend_incomeDAO.getSummSpend() != null){
            balance.setText(String.valueOf(spend_incomeDAO.getSummIncome() - spend_incomeDAO.getSummSpend())+"р");
        }else{
            balance.setText("");
        }
    }


    private void checkAvailabilityTypes() {
        if (typeDAO.getAllType().isEmpty()){
            List<Type> type = new ArrayList<>();
            type.add(new Type( "Здоровье"));
            type.add(new Type( "Одежда"));
            type.add(new Type( "Продукты"));
            type.add(new Type( "Интернет магазин"));
            try {
                typeDAO.insertAll(type);
            }
            catch(SQLiteException exception) {
                Log.i("MyInfo1", "Повторное добавление в таблицу Type");
                exception.printStackTrace();
            }
        }
    }

    public void addListenerOnButton(){
        button_sp_in = (ImageButton)findViewById(R.id.button_spend_income);
        button_analytic = (ImageButton)findViewById(R.id.button_analytics);
        button_bd_settings = (ImageButton)findViewById(R.id.button_BDSettings);

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

        button_bd_settings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.kiselev.wiselyapp.activities.BDSettingsActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}