package com.kiselev.wiselyapp.database;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.room.Room;


public class DBHelper extends Application {
    public static DBHelper instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }

    public static DBHelper getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
