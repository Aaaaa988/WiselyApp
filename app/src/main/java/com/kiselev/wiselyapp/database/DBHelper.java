package com.kiselev.wiselyapp.database;

import android.app.Application;


import androidx.room.Room;


public class DBHelper extends Application {
    public static DBHelper instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static DBHelper getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
