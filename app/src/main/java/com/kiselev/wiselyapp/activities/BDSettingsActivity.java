package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Type;

import java.util.ArrayList;
import java.util.List;

public class BDSettingsActivity extends AppCompatActivity {

    Button buttonInitBD, deleteSpendAndIncome;

    TypeDAO typeDAO;
    Spend_IncomeDAO spend_incomeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdsettings);

        AppDatabase db = DBHelper.getInstance().getDatabase();

        typeDAO = db.typeDAO();
        spend_incomeDAO = db.spend_incomeDAO();

        addListenerOnButton();
    }

    public void addListenerOnButton(){
        buttonInitBD = (Button)findViewById(R.id.button_initBD);
        deleteSpendAndIncome = (Button)findViewById(R.id.button_deleteSpendAndIncome);

        buttonInitBD.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
        );

        deleteSpendAndIncome.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spend_incomeDAO.deleteAll();
                    }
                }
        );

    }
}