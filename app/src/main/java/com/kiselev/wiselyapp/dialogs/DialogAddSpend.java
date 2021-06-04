package com.kiselev.wiselyapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_CommentDAO;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.Spend_TypeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Spend_Comment;
import com.kiselev.wiselyapp.database.entity.Spend_Income;
import com.kiselev.wiselyapp.database.entity.Spend_Type;
import com.kiselev.wiselyapp.database.entity.Type;

import java.sql.SQLException;
import java.util.List;

public class DialogAddSpend extends DialogFragment {
    EditText amount, comment;
    DatePicker datePicker;
    Spinner spinner;

    private TypeDAO typeDAO;
    private Spend_IncomeDAO spend_incomeDAO;
    private Spend_TypeDAO spend_typeDAO;
    private Spend_CommentDAO spend_commentDAO;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View addSpendView = li.inflate(R.layout.dialog_add_spend, null);
        /*-БД-*/
        AppDatabase db = DBHelper.getInstance().getDatabase();
        typeDAO = db.typeDAO();
        spend_incomeDAO = db.spend_incomeDAO();
        spend_typeDAO = db.spend_typeDAO();
        spend_commentDAO = db.spend_commentDAO();
        List<Type> typeList = typeDAO.getAllType();
        /*----*/
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());
        mDialogBuilder.setView(addSpendView);
        mDialogBuilder
                .setTitle("Добавить расход")
                .setIcon(R.drawable.arrow1)
                .setCancelable(false);

        Dialog dialog = mDialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);

        Button Ok = (Button) addSpendView.findViewById(R.id.addSpendDialogOk);
        Button Cancel = (Button) addSpendView.findViewById(R.id.addSpendDialogCancel);

        amount = (EditText) addSpendView.findViewById(R.id.input_spend_amount);
        datePicker = (DatePicker) addSpendView.findViewById(R.id.input_spend_date);
        spinner = (Spinner) addSpendView.findViewById(R.id.input_spend_spinner);
        comment = (EditText) addSpendView.findViewById(R.id.input_comment);

        spinner.setAdapter(getTypeFromBD(typeList));

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Заполните поля со звездочкой",Toast.LENGTH_SHORT).show();
                }else{
                    /*Toast.makeText(getActivity(),
                            "Данные в форме:\n"
                                    + "Потрачено " + amount.getText().toString() + "\n"
                                    + "Дата " + datePicker.getDayOfMonth() +"/"+ datePicker.getMonth() +"/"+ datePicker.getYear() + "\n"
                                    + "Тип траты " + spinner.getSelectedItem().toString() +"\n"
                                    + "Пояснение " + comment.getText().toString() +"\n"
                                    + "Последний ключ " + getLastPrimaryKey().toString()

                            ,Toast.LENGTH_LONG).show();*/

                    Toast.makeText(getActivity(),"Расход был добавлен в базу данных",Toast.LENGTH_SHORT).show();
                    double doubleAmount = Double.parseDouble(amount.getText().toString());
                    String date = datePicker.getDayOfMonth() +"/"+ datePicker.getMonth() +"/"+ datePicker.getYear();
                    addSpendInDB(doubleAmount, date);

                    Type type =(Type) spinner.getSelectedItem();
                    addSpendTypeInDB(getLastPrimaryKey(), type.id);

                    if(!comment.getText().toString().isEmpty())
                        addSpendCommentInDB(getLastPrimaryKey(), comment.getText().toString());

                    dialog.dismiss();
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

    private void addSpendCommentInDB(Integer spend_id, String comment) {
        Spend_Comment spend_comment = new Spend_Comment(spend_id, comment);
        spend_commentDAO.insert(spend_comment);
    }

    private void addSpendTypeInDB(Integer spend_id, int type_id) {
        Spend_Type spend_type = new Spend_Type(spend_id, type_id);
        spend_typeDAO.insert(spend_type);
    }

    private void addSpendInDB(double amount, String data) {
        Spend_Income spend_income = new Spend_Income(amount, data, 0);
        spend_incomeDAO.insert(spend_income);
    }

    private ArrayAdapter<Type> getTypeFromBD(List<Type> typeList) {
        ArrayAdapter<Type> adapter = new ArrayAdapter<Type>(getActivity(), android.R.layout.simple_spinner_item, typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public Integer getLastPrimaryKey(){
        Integer lastPrimaryKey = spend_incomeDAO.getLastPrimaryKey();
        if (lastPrimaryKey == null)
            lastPrimaryKey = 0;
        return lastPrimaryKey;
    }
}
