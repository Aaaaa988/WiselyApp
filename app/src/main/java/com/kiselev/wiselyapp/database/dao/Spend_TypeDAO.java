package com.kiselev.wiselyapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kiselev.wiselyapp.database.entity.Spend_Income;
import com.kiselev.wiselyapp.database.entity.Spend_Type;

import java.util.List;

@Dao
public interface Spend_TypeDAO {
    @Insert
    void insertAll(List<Spend_Type> spend_type);

    @Insert
    void insert(Spend_Type spend_type);

    @Delete
    void delete(Spend_Type spend_type);

    @Query("DELETE FROM spend_type")
    void deleteAll();

    @Query("SELECT * FROM spend_type")
    List<Spend_Type> getAllSpend_Type();

    @Query("SELECT type.type_name FROM spend_type, type WHERE spend_type.type_id = type.id AND spend_type.spend_id = :param_id")
    String getTypeName(int param_id);
}
