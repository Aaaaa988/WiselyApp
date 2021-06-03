package com.kiselev.wiselyapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.entity.Spend_Income;
import com.kiselev.wiselyapp.database.entity.Type;

import java.util.List;

public class DialogAddIncome extends DialogFragment {
    EditText amount;
    DatePicker datePicker;

    private Spend_IncomeDAO spend_incomeDAO;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View addSpendView = li.inflate(R.layout.dialog_add_income, null);

        /*-БД-*/
        AppDatabase db = DBHelper.getInstance().getDatabase();
        spend_incomeDAO = db.spend_incomeDAO();
        /*----*/

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());

        mDialogBuilder.setView(addSpendView);


        mDialogBuilder
                .setTitle("Добавить доход")
                .setIcon(R.drawable.arrow2)
                .setCancelable(false);

        Dialog dialog = mDialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);


        Button Ok = (Button) addSpendView.findViewById(R.id.addIncomeDialogOk);
        Button Cancel = (Button) addSpendView.findViewById(R.id.addIncomeDialogCancel);

        amount = (EditText) addSpendView.findViewById(R.id.input_income_amount);
        datePicker = (DatePicker) addSpendView.findViewById(R.id.input_income_date);

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Заполните поля со звездочкой",Toast.LENGTH_SHORT).show();
                }else{
                    dialog.dismiss();
                    //Toast.makeText(getActivity(),"Получено:" + amount.getText().toString() +" Год = "+ datePicker.getYear(),Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(),"Доход был добавлен в базу данных",Toast.LENGTH_SHORT).show();
                    double doubleAmount = Double.parseDouble(amount.getText().toString());
                    String date = datePicker.getDayOfMonth() +"/"+ datePicker.getMonth() +"/"+ datePicker.getYear();
                    addIncomeInDB(doubleAmount, date);

                }
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    private void addIncomeInDB(double amount, String data) {
        Spend_Income spend_income = new Spend_Income(amount, data, 1);
        spend_incomeDAO.insert(spend_income);
    }
}
