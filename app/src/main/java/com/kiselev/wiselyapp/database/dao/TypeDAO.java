package com.kiselev.wiselyapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kiselev.wiselyapp.database.entity.Type;

import java.util.List;

@Dao
public interface TypeDAO {
    @Insert
    void insertAll(Type... type);

    @Insert
    void insert(Type type);

    // Удаление Person из бд
    @Delete
    void delete(Type type);

    // Удаление Person из бд
    @Query("DELETE FROM type")
    void deleteAll();

    // Получение всех Person из бд
    @Query("SELECT * FROM type")
    List<Type> getAllType();
}
