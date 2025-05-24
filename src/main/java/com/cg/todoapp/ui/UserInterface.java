package com.cg.todoapp.ui;

import com.cg.todoapp.model.Todo;
import com.cg.todoapp.model.Priority;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("\n====== TODO APP MENU ======");
        System.out.println("1. Add Todo");
        System.out.println("2. View All Todos");
        System.out.println("3. Update Todo");
        System.out.println("4. Delete Todo");
        System.out.println("5. Mark Todo as Complete");
        System.out.println("6. Mark Todo as Incomplete");
        System.out.println("7. Search by Keyword");
        System.out.println("8. Filter by Priority");
        System.out.println("9. Filter by Date Range");
        System.out.println("10. Sort Todos");
        System.out.println("11. Save and Exit");
        System.out.print("Enter your choice: ");
    }

    public int handleUserInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int getTodoIdInput() {
        System.out.print("Enter Todo ID: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric ID.");
            return getTodoIdInput(); // recursive retry
        }
    }

    public Todo getTodoInputFromUser(int id) {
        String title;
        do {
            System.out.print("Enter title: ");
            title = scanner.nextLine().trim();
            if (!isValidTitle(title)) {
                System.out.println("Error: Title must contain at least one alphabet character (cannot be only numbers).");
            }
        } while (!isValidTitle(title));

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Priority priority = null;
        while (priority == null) {
            System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
            try {
                priority = Priority.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
            }
        }

        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();
            try {
                LocalDate inputDate = LocalDate.parse(dateInput);
                if (inputDate.isBefore(LocalDate.now())) {
                    System.out.println("Error: Due date cannot be in the past.");
                } else {
                    dueDate = inputDate;
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        return new Todo(id, title, description, priority, dueDate);
    }

    private boolean isValidTitle(String title) {
        return title != null && title.matches(".*[a-zA-Z].*");
    }

    public void printTodos(List<Todo> todos) {
        if (todos.isEmpty()) {
            System.out.println("No todos found.");
        } else {
            System.out.println("\n---- TODOS ----");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
            for (Todo todo : todos) {
                String status = todo.isCompleted() ? "Completed" : "Pending";
                String formattedDate = todo.getDueDate().format(formatter);
                System.out.printf("ID: %d | Title: %s | Description: %s | %s | %s | %s%n",
                        todo.getId(),
                        capitalize(todo.getTitle()),
                        capitalize(todo.getDescription()),
                        todo.getPriority(),
                        formattedDate,
                        status
                );
            }
        }
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
