package com.kiselev.wiselyapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kiselev.wiselyapp.database.entity.Type;

import java.util.List;

@Dao
public interface TypeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Type>  type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Type type);

    @Delete
    void delete(Type type);

    @Query("DELETE FROM type")
    void deleteAll();

    @Query("SELECT * FROM type")
    List<Type> getAllType();

    @Query("SELECT id FROM type WHERE type_name LIKE :param_name")
    Integer getIdType(String param_name);

    @Query("DELETE FROM type WHERE id = :param_id")
    void deleteById(int param_id);


}
