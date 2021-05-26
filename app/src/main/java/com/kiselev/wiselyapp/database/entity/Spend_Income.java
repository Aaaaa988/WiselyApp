package com.kiselev.wiselyapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Spend_Income {
    @PrimaryKey int id;
    double amount;
    String date;
    int type;
}
