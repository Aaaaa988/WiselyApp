package com.kiselev.wiselyapp.database.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Type {
    @PrimaryKey
    public int id;
    public String type_name;


    public Type() {
    }

    @Ignore
    public Type(int id, String type_name) {
        this.id = id;
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return type_name;
    }
}
