package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.TestDataset;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Type;
import com.kiselev.wiselyapp.dialogs.DialogAddSpend;
import com.kiselev.wiselyapp.dialogs.DialogAddType;
import com.kiselev.wiselyapp.dialogs.DialogDeleteType;

import java.util.ArrayList;
import java.util.List;

public class BDSettingsActivity extends AppCompatActivity {

    Button deleteSpendAndIncome, button_addTest;

    TypeDAO typeDAO;
    Spend_IncomeDAO spend_incomeDAO;
    List<Type> typeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdsettings);

        AppDatabase db = DBHelper.getInstance().getDatabase();

        typeDAO = db.typeDAO();
        spend_incomeDAO = db.spend_incomeDAO();
        typeList = typeDAO.getAllType();




        addListenerOnButton();
    }

    public void addListenerOnButton(){
        deleteSpendAndIncome = (Button)findViewById(R.id.button_deleteSpendAndIncome);
        button_addTest = (Button)findViewById(R.id.button_addTest);

        deleteSpendAndIncome.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spend_incomeDAO.deleteAll();
                    }
                }
        );

        button_addTest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TestDataset testDataset = new TestDataset();
                        testDataset.insertTestDataSetInDB();
                    }
                }
        );

    }

    public void openDialogAddType(View v){
        DialogAddType dialog = new DialogAddType();
        dialog.show(getSupportFragmentManager(), "custom");
    }

    public void openDialogDeleteType(View v){
        DialogDeleteType dialog = new DialogDeleteType();
        dialog.show(getSupportFragmentManager(), "custom");
    }


}