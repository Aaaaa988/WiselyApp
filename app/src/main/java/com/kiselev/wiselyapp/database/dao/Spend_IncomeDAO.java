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

    @Delete
    void delete(Spend_Income spend_income);

    @Query("DELETE FROM spend_income WHERE id = :param_id")
    void deleteById(int param_id);

    @Query("DELETE FROM spend_income")
    void deleteAll();

    @Query("SELECT * FROM spend_income")
    List<Spend_Income> getAllSpend_Income();

    @Query("SELECT MAX(ID) FROM spend_income")
    Integer getLastPrimaryKey();

    @Query("SELECT SUM(amount) FROM spend_income WHERE type = 0")
    Double getSummSpend();

    @Query("SELECT SUM(amount) FROM spend_income WHERE type = 1")
    Double getSummIncome();

    @Query("SELECT COUNT(amount) FROM spend_income WHERE type = 0 AND date LIKE '%/' || :month || '/' || :year")
    Integer getCountSpendMonthYear(String month, String year);

    @Query("SELECT SUM(amount) FROM spend_income WHERE type = 0 AND date LIKE :day || '/' || :month || '/' || :year")
    Double getSummSpendDayMonthYear(String day, String month, String year);

    @Query("SELECT SUM(amount) FROM spend_income WHERE type = 0 AND date LIKE '%/' || :month || '/' || :year")
    Double getSummSpendMonthYear(String month, String year);

    @Query("SELECT SUM(amount) FROM spend_income WHERE type = 1 AND date LIKE '%/' || :month || '/' || :year")
    Double getSummIncomeMonthYear(String month, String year);

    @Query("SELECT * FROM spend_income WHERE date LIKE '%/' || :month || '/' || :year")
    List<Spend_Income> getAllSpend_IncomeWhereMonthAndYear(String month, String year);

    @Query("SELECT * FROM spend_income WHERE date LIKE :day || '/' || :month || '/' || :year")
    List<Spend_Income> getAllSpend_IncomeWhereDayMonthYear(String day, String month, String year);

    @Query("DELETE FROM spend_income WHERE date LIKE :day || '/' || :month || '/' || :year")
    void deleteAllSpend_IncomeWhereDayMonthYear(String day, String month, String year);
}
