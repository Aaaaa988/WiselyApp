package com.kiselev.wiselyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.TypeDAO;

public class BDSettingsActivity extends AppCompatActivity {

    Button buttonInitBD;

    TypeDAO typeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdsettings);

        AppDatabase db = DBHelper.getInstance().getDatabase();

        typeDAO = db.typeDAO();

        addListenerOnButton();
    }

    public void addListenerOnButton(){
        buttonInitBD = (Button)findViewById(R.id.button_initBD);

        buttonInitBD.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );

    }
}