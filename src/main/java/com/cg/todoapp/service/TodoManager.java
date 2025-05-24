package com.cg.todoapp.service;

import com.cg.todoapp.model.Todo;
import com.cg.todoapp.model.Priority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TodoManager {
    private List<Todo> todoList = new ArrayList<>();

    public void addTodo(Todo todo) {
        todoList.add(todo);
    }

    public void deleteTodo(int id) {
        todoList.removeIf(todo -> todo.getId() == id);
    }

    public Todo getTodoById(int id) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    public void updateTodo(Todo updatedTodo) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == updatedTodo.getId()) {
                todoList.set(i, updatedTodo);
                break;
            }
        }
    }

    public List<Todo> searchByKeyword(String keyword) {
        return todoList.stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || todo.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Todo> filterByPriority(Priority priority) {
        return todoList.stream()
                .filter(todo -> todo.getPriority() == priority)
                .collect(Collectors.toList());
    }

    public List<Todo> filterByDateRange(LocalDate start, LocalDate end) {
        return todoList.stream()
                .filter(todo -> !todo.getDueDate().isBefore(start) && !todo.getDueDate().isAfter(end))
                .collect(Collectors.toList());
    }

    public List<Todo> sortBy(Comparator<Todo> comparator) {
        return todoList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Todo> getAllTodos() {
        return todoList;
    }
}
