package com.kiselev.wiselyapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kiselev.wiselyapp.database.entity.Spend_Comment;
import com.kiselev.wiselyapp.database.entity.Spend_Type;

import java.util.List;

@Dao
public interface Spend_CommentDAO {
    @Insert
    void insertAll(List<Spend_Comment> spend_comment);

    @Insert
    void insert(Spend_Comment spend_comment);

    @Delete
    void delete(Spend_Comment spend_comment);

    @Query("DELETE FROM spend_comment")
    void deleteAll();

    @Query("SELECT * FROM spend_comment")
    List<Spend_Comment> getAllSpend_Comment();

    @Query("SELECT comment FROM spend_comment WHERE spend_comment.spend_id = :param_id")
    String getComment(int param_id);
}
