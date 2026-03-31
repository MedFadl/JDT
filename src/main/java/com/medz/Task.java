package com.medz;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    static {

    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    private String name;
    private String description;
    private Priority priority;
    boolean isCompleted;

    private final LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private LocalDateTime lastModified;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task(String name, String description, Priority priority, LocalDateTime dueDate) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.createdAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.isCompleted = false;
    }

    public void setName(String name) {
        this.name = name;
        this.lastModified = LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
        this.lastModified = LocalDateTime.now();
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
        this.lastModified = LocalDateTime.now();
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        this.lastModified = LocalDateTime.now();
    }

    public void markCompleted() {
        this.isCompleted = true;
        this.lastModified = LocalDateTime.now();
    }

    public String getTimeLeft() {
        if (isCompleted) return "Completed";

        LocalDateTime now = LocalDateTime.now();

        if (dueDate.isBefore(now)) return "Overdue";

        Duration duration = Duration.between(now, dueDate);

        long minutes = duration.toMinutes();
        if (minutes <= 0) return "Due now";

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long mins = minutes % 60;

        return days + "d " + hours + "h " + mins + "m left";
    }

    @Override
    public String toString() {
        return "Task: " + name +
                "\nDescription: " + description +
                "\nPriority: " + priority +
                "\nCreated: " + createdAt.format(formatter) +
                "\nLast Modified: " + lastModified.format(formatter) +
                "\nDue: " + dueDate.format(formatter) +
                "\nTime Left: " + getTimeLeft() +
                "\nCompleted: " + isCompleted;
    }
}