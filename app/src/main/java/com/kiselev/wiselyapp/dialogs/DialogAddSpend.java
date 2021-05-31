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

public class DialogAddSpend extends DialogFragment {
    EditText amount, comment;
    DatePicker datePicker;
    Spinner spinner;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View addSpendView = li.inflate(R.layout.dialog_add_spend, null);

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

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Заполните поля со звездочкой",Toast.LENGTH_SHORT).show();
                }else{
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
}
