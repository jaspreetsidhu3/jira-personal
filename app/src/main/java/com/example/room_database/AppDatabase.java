package com.example.room_database;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

import java.io.Serializable;

@Database(entities={todo_table.class},version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract todoDao todoDao();
}
