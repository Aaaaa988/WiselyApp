package com.kiselev.wiselyapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Type;

public class DialogAddType extends DialogFragment {
    EditText type;

    private TypeDAO typeDAO;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View addSpendView = li.inflate(R.layout.dialog_add_type, null);

        /*-БД-*/
        AppDatabase db = DBHelper.getInstance().getDatabase();
        typeDAO = db.typeDAO();
        /*----*/

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());

        mDialogBuilder.setView(addSpendView);


        mDialogBuilder
                .setTitle("Добавить тип расхода")
                .setIcon(R.drawable.arrow1)
                .setCancelable(false);

        Dialog dialog = mDialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);


        Button Ok = (Button) addSpendView.findViewById(R.id.add_type_Ok);
        Button Cancel = (Button) addSpendView.findViewById(R.id.add_type_Cancel);

        type = (EditText) addSpendView.findViewById(R.id.input_type);

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Заполните поля со звездочкой",Toast.LENGTH_SHORT).show();
                }else{
                    dialog.dismiss();
                    Toast.makeText(getActivity(),"Тип был добавлен в БД",Toast.LENGTH_SHORT).show();
                    addTypeInDB(type.getText().toString());
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

    private void addTypeInDB(String type_name) {
        Type type = new Type(type_name);
        typeDAO.insert(type);
    }
}
