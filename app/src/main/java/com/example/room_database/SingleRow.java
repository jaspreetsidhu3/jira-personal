package com.example.room_database;

public class SingleRow {
    private int prority;
    private String title;
    int id;
    private String description;

    public SingleRow(int prority, String title, String description, int id) {
        this.prority = prority;
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

    public void setPrority(int prority) {
        this.prority = prority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
