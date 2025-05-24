package com.cg.todoapp.ui;

import com.cg.todoapp.model.Priority;
import com.cg.todoapp.model.Todo;
import com.cg.todoapp.util.InputValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator validator = new InputValidator();

    public void displayMenu() {
        System.out.println("\n========= TODO APP MENU =========");
        System.out.println("1. Add Todo");
        System.out.println("2. View All Todos");
        System.out.println("3. Update Todo");
        System.out.println("4. Delete Todo");
        System.out.println("5. Mark as Complete");
        System.out.println("6. Mark as Incomplete");
        System.out.println("7. Search by Keyword");
        System.out.println("8. Filter by Priority");
        System.out.println("9. Filter by Due Date Range");
        System.out.println("10. Sort Todos");
        System.out.println("11. Exit");
        System.out.print("Choose an option: ");
    }

    public int handleUserInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void printTodos(List<Todo> todos) {
        if (todos.isEmpty()) {
            System.out.println("No todos found.");
        } else {
            for (Todo todo : todos) {
                System.out.println(todo);
            }
        }
    }

    public Todo getTodoInputFromUser(int nextId) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter description: ");
        String desc = scanner.nextLine();

        String dateInput;
        do {
            System.out.print("Enter due date (yyyy-MM-dd): ");
            dateInput = scanner.nextLine();
        } while (!validator.isValidDate(dateInput));

        String priorityInput;
        do {
            System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
            priorityInput = scanner.nextLine();
        } while (!validator.isValidPriority(priorityInput));

        return new Todo(nextId, title, desc,
                LocalDate.parse(dateInput),
                Priority.valueOf(priorityInput.toUpperCase()));
    }

    public int getTodoIdInput() {
        System.out.print("Enter Todo ID: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
        	System.out.println("Invalid ID. Please enter a number.");
        	return getTodoIdInput(); // retry instead of exiting silently

        }
    }
}
