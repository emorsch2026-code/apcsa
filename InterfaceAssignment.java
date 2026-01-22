/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interfaceassignment;

/**
 *
 * @author ekmor
 */
import java.util.ArrayList;
import java.util.Collections;

// Priority interface
interface Priority {
    void setPriority(int priority);
    int getPriority();
}

// Complexity interface
interface Complexity {
    void setComplexity(int complexity);
    int getComplexity();
}

// Task class
class Task implements Priority, Complexity, Comparable<Task> {

    private String name;
    private int priority;
    private int complexity;

    public Task(String name, int priority, int complexity) {
        this.name = name;
        this.priority = priority;
        this.complexity = complexity;
    }

    // Priority methods
    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    // Complexity methods
    @Override
    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    @Override
    public int getComplexity() {
        return complexity;
    }

    // Compare by priority first, then complexity
    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }
        return this.complexity - other.complexity;
    }

    @Override
    public String toString() {
        return name + " (Priority: " + priority +
               ", Complexity: " + complexity + ")";
    }
}

// Driver class
public class InterfaceAssignment {

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Write report", 2, 5));
        tasks.add(new Task("Do homework", 1, 3));
        tasks.add(new Task("Clean room", 3, 2));
        tasks.add(new Task("Study for exam", 1, 6));

        Collections.sort(tasks);

        System.out.println("Tasks ranked by priority, then complexity:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
