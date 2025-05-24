package com.cg.todoapp.service;

import com.cg.todoapp.model.Todo;
import com.cg.todoapp.model.Priority;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class TodoManagerTest {

    private TodoManager manager;

    @Before
    public void setUp() {
        manager = new TodoManager();
    }

    @Test
    public void testAddTodo() {
        Todo todo = new Todo(1, "Test", "Testing add", Priority.MEDIUM, LocalDate.now().plusDays(1));
        manager.addTodo(todo);
        List<Todo> todos = manager.getAllTodos();
        assertEquals(1, todos.size());
        assertEquals("Test", todos.get(0).getTitle());
    }

    @Test
    public void testDeleteTodo() {
        Todo todo = new Todo(1, "Delete", "Should be deleted", Priority.LOW, LocalDate.now().plusDays(2));
        manager.addTodo(todo);
        manager.deleteTodo(1);
        assertEquals(0, manager.getAllTodos().size());
    }

    @Test
    public void testGetTodoById() {
        Todo todo = new Todo(10, "Lookup", "Find me", Priority.HIGH, LocalDate.now().plusDays(3));
        manager.addTodo(todo);
        Todo found = manager.getTodoById(10);
        assertNotNull(found);
        assertEquals("Lookup", found.getTitle());
    }

    @Test
    public void testUpdateTodo() {
        Todo original = new Todo(2, "Old", "Outdated", Priority.LOW, LocalDate.now().plusDays(5));
        manager.addTodo(original);

        Todo updated = new Todo(2, "New", "Updated desc", Priority.HIGH, LocalDate.now().plusDays(7));
        manager.updateTodo(updated);

        Todo result = manager.getTodoById(2);
        assertEquals("New", result.getTitle());
        assertEquals(Priority.HIGH, result.getPriority());
    }
}
