package com.kiselev.wiselyapp.database.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Spend_Income {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public double amount;
    public String date;
    public int type;

    public Spend_Income() {
    }

    @Ignore
    public Spend_Income(double amount, String date, int type) {
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", amount=" + amount + ", date='" + date + '\'' + ", type=" + type+'}';
    }
}
