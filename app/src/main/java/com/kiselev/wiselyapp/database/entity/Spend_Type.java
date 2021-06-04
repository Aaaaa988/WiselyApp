package com.kiselev.wiselyapp.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int spend_id;
    public int type_id;

    public Spend_Type() {
    }

    @Ignore
    public Spend_Type(int spend_id, int type_id) {
        this.spend_id = spend_id;
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "Spend_Type{" +
                "id=" + id +
                ", spend_id=" + spend_id +
                ", type_id=" + type_id +
                '}';
    }
}
