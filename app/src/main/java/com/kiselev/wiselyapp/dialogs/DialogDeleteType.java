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
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.List;

public class DialogDeleteType extends DialogFragment {
    Spinner spinner;
    private TypeDAO typeDAO;
    private Spend_TypeDAO spend_typeDAO;
    private Spend_IncomeDAO spend_incomeDAO;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View addSpendView = li.inflate(R.layout.dialog_delete_type, null);
        /*-БД-*/
        AppDatabase db = DBHelper.getInstance().getDatabase();
        typeDAO = db.typeDAO();
        spend_typeDAO = db.spend_typeDAO();
        spend_incomeDAO = db.spend_incomeDAO();
        List<Type> typeList = typeDAO.getAllType();
        /*----*/
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());
        mDialogBuilder.setView(addSpendView);
        mDialogBuilder
                .setTitle("Удалить тип траты")
                .setIcon(R.drawable.arrow1)
                .setCancelable(false);

        Dialog dialog = mDialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);

        Button Ok = (Button) addSpendView.findViewById(R.id.delete_type_Ok);
        Button Cancel = (Button) addSpendView.findViewById(R.id.delete_type_Cancel);

        spinner = (Spinner) addSpendView.findViewById(R.id.spinner_delete_type);

        spinner.setAdapter(getTypeFromBD(typeList));

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(getActivity(),"Тип расхода был удален из БД",Toast.LENGTH_SHORT).show();

                    Type type =(Type) spinner.getSelectedItem();
                    deleteTypeFromDB(type);

                    dialog.dismiss();

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

    private void deleteTypeFromDB(Type type) {
        try {
            List<Integer> listId = spend_typeDAO.getIdSpend(type.id);
            for(int i = 0; i < listId.size(); i++){
                spend_incomeDAO.deleteById(listId.get(i));
            }
            typeDAO.deleteById(type.id);
        }
        catch(NullPointerException exception) {
            Log.i("MyInfo1", "Попытка удалить пустой тип");
            exception.printStackTrace();
        }
    }

    private ArrayAdapter<Type> getTypeFromBD(List<Type> typeList) {
        ArrayAdapter<Type> adapter = new ArrayAdapter<Type>(getActivity(), android.R.layout.simple_spinner_item, typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
