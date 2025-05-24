package com.cg.todoapp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Todo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private boolean isCompleted;

    public Todo(int id, String title, String description, LocalDate dueDate, Priority priority) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        setPriority(priority);
        setCompleted(false);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public void markComplete() { setCompleted(true); }
    public void markIncomplete() { setCompleted(false); }

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Due: %s | Priority: %s | Status: %s",
                getId(), getTitle(), getDueDate(), getPriority(), (isCompleted() ? "Completed" : "Pending"));
    }
}
