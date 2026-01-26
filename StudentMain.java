/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmain;

/**
 *
 * @author ekmor
 */
public class StudentMain {
    public static void main(String[] args) {

        // Create a student
        Student st1 = new Student("Bob", "Smith");

        // Set test scores for the 3 courses
        st1.setTestScore(0, 85);
        st1.setTestScore(1, 90);
        st1.setTestScore(2, 78);

        // Display student info and average
        System.out.println(st1);
        System.out.println("Average Score: " + st1.getAverage());
    }
}

// ===================== Student Class =====================
class Student {

    private String firstName;
    private String lastName;
    private Course[] courses;

    // Empty constructor
    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.courses = new Course[3];
        initializeCourses();
    }

    // Constructor with name only
    public Student(String first, String last) {
        this.firstName = first;
        this.lastName = last;
        this.courses = new Course[3];
        initializeCourses();
    }

    // Constructor with name and courses
    public Student(String first, String last, Course[] courses) {
        this.firstName = first;
        this.lastName = last;
        this.courses = courses;
    }

    // Initialize all courses with test score = 0
    private void initializeCourses() {
        for (int i = 0; i < courses.length; i++) {
            courses[i] = new Course();
        }
    }

    // Set test score for a specific course
    public void setTestScore(int courseIndex, int score) {
        if (courseIndex >= 0 && courseIndex < courses.length) {
            courses[courseIndex].setTestScore(score);
        }
    }

    // Get test score for a specific course
    public int getTestScore(int courseIndex) {
        if (courseIndex >= 0 && courseIndex < courses.length) {
            return courses[courseIndex].getTestScore();
        }
        return 0;
    }

    // Calculate average test score
    public double getAverage() {
        int total = 0;
        for (Course c : courses) {
            total += c.getTestScore();
        }
        return total / (double) courses.length;
    }

    // Display student details
    public String toString() {
        String result = "Student: " + firstName + " " + lastName + "\n";
        for (int i = 0; i < courses.length; i++) {
            result += "Course " + (i + 1) + " Score: "
                    + courses[i].getTestScore() + "\n";
        }
        return result;
    }
}

// ===================== Course Class =====================
class Course {

    private int testScore;

    // Default constructor (score initialized to 0)
    public Course() {
        this.testScore = 0;
    }

    // Overloaded constructor
    public Course(int score) {
        this.testScore = score;
    }

    public void setTestScore(int score) {
        this.testScore = score;
    }

    public int getTestScore() {
        return testScore;
    }
}

