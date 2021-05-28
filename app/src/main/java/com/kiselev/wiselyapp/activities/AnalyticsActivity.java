package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.entity.Spend_Income;

import java.util.List;


public class AnalyticsActivity extends AppCompatActivity {

    private Button buttonAdd, buttonOutput;




    Spend_IncomeDAO spend_incomeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        AppDatabase db = DBHelper.getInstance().getDatabase();

        spend_incomeDAO = db.spend_incomeDAO();

        addListenerOnButton();
    }

    public void addListenerOnButton(){
        buttonAdd = (Button)findViewById(R.id.button);
        buttonOutput = (Button)findViewById(R.id.button2);

        buttonAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText amount = (EditText)findViewById(R.id.editTextNumberDecimal);
                        EditText date = (EditText)findViewById(R.id.editTextDate);
                        EditText type = (EditText)findViewById(R.id.editTextNumber);

                        Spend_Income spend_income = new Spend_Income();
                        spend_income.amount = Double.parseDouble(amount.getText().toString());
                        spend_income.date = date.getText().toString();
                        spend_income.type = Integer.parseInt(type.getText().toString());

                        spend_incomeDAO.insert(spend_income);
                    }
                }
        );

        buttonOutput.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        TextView textViewOutPut = (TextView) findViewById(R.id.textView5);

                        List<Spend_Income> spend_incomeList = spend_incomeDAO.getAllSpend_Income();
                        String output = "";
                        for(Spend_Income i: spend_incomeList){
                            output += i.toString()+"\n";
                        }
                        textViewOutPut.setText(output);
                    }
                }
        );
    }

}