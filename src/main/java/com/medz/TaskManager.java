package com.medz;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        if (!isValidIndex(index)) return;
        tasks.remove(index);
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + i + "] " + tasks.get(i));
            System.out.println("----------------------");
        }
    }

    public void markTaskCompleted(int index) {
        if (!isValidIndex(index)) return;
        tasks.get(index).markCompleted();
    }

    public Task getTask(int index) {
        if (!isValidIndex(index)) return null;
        return tasks.get(index);
    }

    public boolean editTask(int index, String newName, String newDescription, String newPriority) {
        if (!isValidIndex(index)) return false;

        Task task = tasks.get(index);

        if (!newName.isEmpty()) task.setName(newName);
        if (!newDescription.isEmpty()) task.setDescription(newDescription);

        if (!newPriority.isEmpty()) {
            try {
                task.setPriority(Task.Priority.valueOf(newPriority.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Use LOW, MEDIUM, HIGH.");
            }
        }

        return true;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}