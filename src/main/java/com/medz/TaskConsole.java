package com.medz;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskConsole {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskManager manager = new TaskManager();
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");

    public TaskConsole() throws AWTException {
    }

    public void close() {
        scanner.close();
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Edit Task");
            System.out.println("6. Exit");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Task name: ");
                    String name = scanner.nextLine();

                    System.out.print("Description: ");
                    String desc = scanner.nextLine();

                    System.out.print("Priority (LOW/MEDIUM/HIGH): ");
                    String priorityInput = scanner.nextLine();

                    Task.Priority priority;
                    try {
                        priority = Task.Priority.valueOf(priorityInput.toUpperCase());
                    } catch (Exception e) {
                        System.out.println("Invalid priority. Defaulting to LOW.");
                        priority = Task.Priority.LOW;
                    }

                    System.out.print("Enter due date (yyyy-M-d HH:mm): ");
                    String dueInput = scanner.nextLine();

                    LocalDateTime dueDate;
                    try {
                        dueDate = LocalDateTime.parse(dueInput, formatter);
                    } catch (Exception e) {
                        System.out.println("Invalid format. Using default (1 day later).");
                        dueDate = LocalDateTime.now().plusDays(1);
                    }

                    manager.addTask(new Task(name, desc, priority, dueDate));
                    break;

                case 2:
                    manager.listTasks();
                    break;

                case 3:
                    System.out.print("Task index: ");
                    int completeIndex = scanner.nextInt();
                    scanner.nextLine();
                    manager.markTaskCompleted(completeIndex);
                    break;

                case 4:
                    System.out.print("Task index: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();
                    manager.removeTask(deleteIndex);
                    break;

                case 5:
                    System.out.print("Task index: ");
                    int editIndex = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("New name (leave blank to skip): ");
                    String newName = scanner.nextLine();

                    System.out.print("New description (leave blank to skip): ");
                    String newDesc = scanner.nextLine();

                    System.out.print("New priority (leave blank to skip): ");
                    String newPriority = scanner.nextLine();

                    System.out.print("New due date (yyyy-M-d HH:mm) or blank: ");
                    String newDueInput = scanner.nextLine();

                    boolean edited = manager.editTask(editIndex, newName, newDesc, newPriority);

                    if (!edited) {
                        System.out.println("Invalid task index.");
                        break;
                    }

                    if (!newDueInput.isEmpty()) {
                        try {
                            LocalDateTime newDueDate = LocalDateTime.parse(newDueInput, formatter);
                            Task task = manager.getTask(editIndex);
                            if (task != null) {
                                task.setDueDate(newDueDate);
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid date format, skipping...");
                        }
                    }

                    break;

                case 6:
                    close();
                    return;
            }
        }
    }
}