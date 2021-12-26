package com.example.room_database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class todo_table {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "priority")
    public int priority;
    @ColumnInfo(name = "status")
    public int status;
    @ColumnInfo(name = "description")
    public String description;

    public todo_table(String title, String description, int priority, int status) {
        this.title = title;
        this.priority = priority;
        this.description = description;
        this.status = status;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public int getStatus(){return  status;}

}
