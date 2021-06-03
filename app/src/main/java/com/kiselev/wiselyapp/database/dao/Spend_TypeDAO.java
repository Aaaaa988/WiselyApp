package com.kiselev.wiselyapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
}
