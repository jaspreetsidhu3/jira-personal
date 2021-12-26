package com.example.room_database;

public class SingleRow {
    private int prority;
    private int status;
    private String title;
    int id;
    private String description;

    public SingleRow(int prority, String title, String description, int status, int id) {
        this.prority = prority;
        this.status = status;
        this.title = title;
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getPrority() {
        return prority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
