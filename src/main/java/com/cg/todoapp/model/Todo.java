package com.cg.todoapp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Todo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private boolean isCompleted;

    // ✅ Default constructor required by Jackson
    public Todo() {
    }

    public Todo(int id, String title, String description, Priority priority, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id + ", title=" + title + ", description=" + description +
                ", priority=" + priority + ", dueDate=" + dueDate +
                ", completed=" + isCompleted + "]";
    }
}
