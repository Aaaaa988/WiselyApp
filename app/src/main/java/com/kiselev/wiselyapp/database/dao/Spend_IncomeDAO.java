package com.kiselev.wiselyapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kiselev.wiselyapp.database.entity.Spend_Income;

import java.util.List;

@Dao
public interface Spend_IncomeDAO {
    @Insert
    void insertAll(Spend_Income... spend_income);

    @Insert
    void insert(Spend_Income spend_income);

    // Удаление Person из бд
    @Delete
    void delete(Spend_Income spend_income);

    // Удаление Person из бд
    @Query("DELETE FROM spend_income")
    void deleteAll();

    // Получение всех Person из бд
    @Query("SELECT * FROM spend_income")
    List<Spend_Income> getAllSpend_Income();
}
