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
        )
})
public class Spend_Comment {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int spend_id;
    public String comment;

    public Spend_Comment() {
    }

    @Ignore
    public Spend_Comment(int spend_id, String comment) {
        this.spend_id = spend_id;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Spend_Comment{" +
                "id=" + id +
                ", spend_id=" + spend_id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
