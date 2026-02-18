/*
 * Name: Esteban Pereira, Jessie Alcalan, XAVIER ZAVALA
 * Date: 02/12/2026
 * Program: Course Grades Analyzer - reads CSV and analyzes grades.
 */

import java.util.ArrayList;

public class Course {

    // Required data variables
    private String courseName;
    private ArrayList<Integer> courseGrades; // 0=A,1=B,2=C,3=D,4=F

    // Constructor
    public Course(String courseName, ArrayList<Integer> courseGrades) {
        this.courseName = courseName;
        this.courseGrades = courseGrades;
    }

    // Default constructor
    public Course() {
        this.courseName = "";
        this.courseGrades = new ArrayList<>();
    }

    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Integer> getCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(ArrayList<Integer> courseGrades) {
        this.courseGrades = courseGrades;
    }

    // Returns total A+B+C+D+F
    public int getTotalGrades() {
        int total = 0;

        for (int value : courseGrades) {
            total += value;
        }

        return total;
    }

    // Returns A percentage
    public double getAPercent() {
        int total = getTotalGrades();

        if (total == 0) {
            return 0.0;
        }

        return (double) courseGrades.get(0) / total * 100.0;
    }

    // toString for printing
    @Override
    public String toString() {

        int a = courseGrades.get(0);
        int b = courseGrades.get(1);
        int c = courseGrades.get(2);
        int d = courseGrades.get(3);
        int f = courseGrades.get(4);

        return String.format(
                "%-15s A:%4d B:%4d C:%4d D:%4d F:%4d Total:%5d A%%:%6.2f",
                courseName, a, b, c, d, f,
                getTotalGrades(),
                getAPercent()
        );
    }
}