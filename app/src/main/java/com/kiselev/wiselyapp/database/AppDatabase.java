package com.kiselev.wiselyapp.database;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kiselev.wiselyapp.database.dao.Spend_CommentDAO;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.Spend_TypeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Spend_Comment;
import com.kiselev.wiselyapp.database.entity.Spend_Income;
import com.kiselev.wiselyapp.database.entity.Spend_Type;
import com.kiselev.wiselyapp.database.entity.Type;

@Database(entities = {Spend_Income.class, Type.class, Spend_Type.class, Spend_Comment.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Spend_IncomeDAO spend_incomeDAO();
    public abstract Spend_CommentDAO spend_commentDAO();
    public abstract Spend_TypeDAO spend_typeDAO();
    public abstract TypeDAO typeDAO();
}
