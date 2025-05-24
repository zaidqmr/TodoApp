package com.cg.todoapp.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cg.todoapp.model.Todo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class FileHandler {

    private static final String FILE_NAME = "todos.json";
    private final ObjectMapper objectMapper;

    public FileHandler() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // For LocalDate support
    }

    public void saveTodos(List<Todo> todos) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_NAME), todos);
        } catch (IOException e) {
            System.out.println("❌ Error saving todos to JSON: " + e.getMessage());
        }
    }

    public List<Todo> loadTodos() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try {
            return objectMapper.readValue(file, new TypeReference<List<Todo>>() {});
        } catch (IOException e) {
            System.out.println("❌ Error loading todos from JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
