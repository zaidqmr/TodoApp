package com.cg.todoapp;

import com.cg.todoapp.model.Todo;
import com.cg.todoapp.model.Priority;
import com.cg.todoapp.service.TodoManager;
import com.cg.todoapp.ui.UserInterface;
import com.cg.todoapp.util.FileHandler;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

public class TodoApp {
    public static void main(String[] args) {
        TodoManager manager = new TodoManager();
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface(scanner);
        FileHandler fileHandler = new FileHandler();

        List<Todo> loadedTodos = fileHandler.loadTodos();
        for (Todo t : loadedTodos) {
            manager.addTodo(t);
        }

        int choice;
        int nextId = loadedTodos.size() + 1;

        do {
            ui.displayMenu();
            choice = ui.handleUserInput();

            switch (choice) {
                case 1:
                    Todo newTodo = ui.getTodoInputFromUser(nextId++);
                    if (newTodo != null) {
                        manager.addTodo(newTodo);
                        System.out.println("Todo added.");
                    } else {
                        System.out.println("Failed to add Todo. Please try again.");
                    }
                    break;

                case 2:
                    ui.printTodos(manager.getAllTodos());
                    break;

                case 3:
                    int idToUpdate = ui.getTodoIdInput();
                    Todo updated = ui.getTodoInputFromUser(idToUpdate);
                    if (updated != null) {
                        manager.updateTodo(updated);
                        System.out.println("Todo updated.");
                    } else {
                        System.out.println("Failed to update Todo.");
                    }
                    break;

                case 4:
                    manager.deleteTodo(ui.getTodoIdInput());
                    System.out.println("Todo deleted.");
                    break;

                case 5:
                    Todo complete = manager.getTodoById(ui.getTodoIdInput());
                    if (complete != null) {
                        complete.markComplete();
                        System.out.println("Marked as complete.");
                    } else {
                        System.out.println("Todo not found.");
                    }
                    break;

                case 6:
                    Todo incomplete = manager.getTodoById(ui.getTodoIdInput());
                    if (incomplete != null) {
                        incomplete.markIncomplete();
                        System.out.println("Marked as incomplete.");
                    } else {
                        System.out.println("Todo not found.");
                    }
                    break;

                case 7:
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    ui.printTodos(manager.searchByKeyword(keyword));
                    break;

                case 8:
                    System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
                    try {
                        String priorityStr = scanner.nextLine();
                        Priority priority = Priority.valueOf(priorityStr.toUpperCase());
                        ui.printTodos(manager.filterByPriority(priority));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
                    }
                    break;

                case 9:
                    try {
                        System.out.print("Start date (yyyy-MM-dd): ");
                        LocalDate start = LocalDate.parse(scanner.nextLine());

                        System.out.print("End date (yyyy-MM-dd): ");
                        LocalDate end = LocalDate.parse(scanner.nextLine());

                        ui.printTodos(manager.filterByDateRange(start, end));
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Use yyyy-MM-dd.");
                    }
                    break;

                case 10:
                    System.out.println("Sort by: 1) Priority 2) Due Date 3) Creation Order");
                    try {
                        int sortOption = Integer.parseInt(scanner.nextLine());
                        Comparator<Todo> comparator;

                        switch (sortOption) {
                            case 1:
                                comparator = Comparator.comparing(Todo::getPriority);
                                break;
                            case 2:
                                comparator = Comparator.comparing(Todo::getDueDate);
                                break;
                            default:
                                comparator = Comparator.comparing(Todo::getId);
                                break;
                        }
                        ui.printTodos(manager.sortBy(comparator));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid sorting option.");
                    }
                    break;

                case 11:
                    fileHandler.saveTodos(manager.getAllTodos());
                    System.out.println("Exiting. Todos saved.");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 11);

        scanner.close();
    }
}
