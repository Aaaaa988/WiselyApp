package com.kiselev.wiselyapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Spend_Income {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public double amount;
    public String date;
    public int type;
    public int clas;

    @Override
    public String toString() {
        return "{id=" + id + ", amount=" + amount + ", date='" + date + '\'' + ", type=" + type+'}';
    }
}
