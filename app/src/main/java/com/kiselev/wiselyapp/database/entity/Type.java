package com.kiselev.wiselyapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Type {
    @PrimaryKey
    int id;
    String type_name;

}
