package com.kiselev.wiselyapp.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Spend_Income.class,
                parentColumns = "id",
                childColumns = "spend_id",
                onDelete = CASCADE
        ),
        @ForeignKey(
                entity = Type.class,
                parentColumns = "id",
                childColumns = "type_id",
                onDelete = CASCADE
        )
})
public class Spend_Type {
    @PrimaryKey
    public int id;
    public int spend_id;
    public int type_id;
}
