package com.cg.todoapp;

import com.cg.todoapp.model.Todo;
import com.cg.todoapp.model.Priority;
import com.cg.todoapp.service.TodoManager;
import com.cg.todoapp.ui.UserInterface;
import com.cg.todoapp.util.FileHandler;

import java.util.Comparator;
import java.util.List;

public class TodoApp {
    public static void main(String[] args) {
        TodoManager manager = new TodoManager();
        UserInterface ui = new UserInterface();
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
                    manager.addTodo(newTodo);
                    System.out.println("Todo added.");
                    break;

                case 2:
                    ui.printTodos(manager.getAllTodos());
                    break;

                case 3:
                    int idToUpdate = ui.getTodoIdInput();
                    Todo updated = ui.getTodoInputFromUser(idToUpdate);
                    manager.updateTodo(updated);
                    System.out.println(" Todo updated.");
                    break;

                case 4:
                    manager.deleteTodo(ui.getTodoIdInput());
                    System.out.println(" Todo deleted.");
                    break;

                case 5:
                    Todo complete = manager.getTodoById(ui.getTodoIdInput());
                    if (complete != null) {
                        complete.markComplete();
                        System.out.println(" Marked as complete.");
                    } else {
                        System.out.println(" Todo not found.");
                    }
                    break;

                case 6:
                    Todo incomplete = manager.getTodoById(ui.getTodoIdInput());
                    if (incomplete != null) {
                        incomplete.markIncomplete();
                        System.out.println(" Marked as incomplete.");
                    } else {
                        System.out.println(" Todo not found.");
                    }
                    break;

                case 7:
                    System.out.print("Enter keyword to search: ");
                    String keyword = new java.util.Scanner(System.in).nextLine();
                    ui.printTodos(manager.searchByKeyword(keyword));
                    break;

                case 8:
                    System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
                    String priority = new java.util.Scanner(System.in).nextLine();
                    ui.printTodos(manager.filterByPriority(Priority.valueOf(priority.toUpperCase())));
                    break;

                case 9:
                    System.out.print("Start date (yyyy-MM-dd): ");
                    String start = new java.util.Scanner(System.in).nextLine();
                    System.out.print("End date (yyyy-MM-dd): ");
                    String end = new java.util.Scanner(System.in).nextLine();
                    ui.printTodos(manager.filterByDateRange(
                            java.time.LocalDate.parse(start),
                            java.time.LocalDate.parse(end)));
                    break;

                case 10:
                    System.out.println("Sort by: 1) Priority 2) Due Date 3) Creation Order");
                    int sortOption = Integer.parseInt(new java.util.Scanner(System.in).nextLine());

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
                    break;

                case 11:
                    fileHandler.saveTodos(manager.getAllTodos());
                    System.out.println(" Exiting. Todos saved.");
                    break;

                default:
                    System.out.println(" Invalid option. Try again.");
            }

        } while (choice != 11);
    }
}
