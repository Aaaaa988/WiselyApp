package com.kiselev.wiselyapp.database;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.entity.Spend_Income;

@Database(entities = {Spend_Income.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Spend_IncomeDAO spend_incomeDAO();
}
