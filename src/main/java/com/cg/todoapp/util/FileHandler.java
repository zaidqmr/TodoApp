package com.cg.todoapp.util;

import com.cg.todoapp.model.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_NAME = "todos.dat";


    public void saveTodos(List<Todo> todos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(new ArrayList<>(todos));
        } catch (IOException e) {
            System.out.println("❌ Error saving todos: " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    public List<Todo> loadTodos() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Todo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error loading todos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
