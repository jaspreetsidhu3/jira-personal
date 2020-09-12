package com.example.room_database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface todoDao {

    @Insert
    public void insert(todo_table todo_table);

    @Delete
    public void delete(todo_table todo_table);

    @Query("Select * from todo_table ORDER BY priority DESC")
    public List<todo_table> getAll();

    @Query("DELETE FROM todo_table WHERE Id = :id")
    public void deleteitem(int id);

    @Query("UPDATE todo_table SET title=:tit, description=:desc, priority=:prio WHERE Id=:id")
    public void update(String tit, String desc, int prio, int id);
}
