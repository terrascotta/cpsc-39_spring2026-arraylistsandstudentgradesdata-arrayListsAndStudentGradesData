/*
 * Name: Esteban Pereira, Jessie Alcalan, XAVIER ZAVALA
 * Date: 02/12/2026
 * Program: Course Grades Analyzer - reads CSV and analyzes grades.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static ArrayList<Course> courses = new ArrayList<>();

    public static void main(String[] args) {

        // Read CSV
        loadCourses("courseAndGradesData.csv");

        // Print table
        System.out.println("\nCOURSE SUMMARY");
        System.out.println("------------------------------------------------------------");

        for (Course c : courses) {
            System.out.println(c);
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Total courses: " + courses.size());

        // Find highest A%
        Course best = findBestCourse();

        if (best != null) {
            System.out.println("\nCourse with Highest A%:");
            System.out.println(best);
        }

        // Search
        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter course name to search (or press Enter to quit): ");
        String name = input.nextLine().trim();

        while (!name.isEmpty()) {

            Course found = searchCourse(name);

            if (found != null) {
                System.out.println("\nFound:");
                System.out.println(found);
            } else {
                System.out.println("\nCourse not found.");
            }

            System.out.print("\nEnter another course (or Enter to quit): ");
            name = input.nextLine().trim();
        }

        System.out.println("\nProgram finished.");
        input.close();
    }

    // Load CSV
    private static void loadCourses(String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {

                // Skip first line
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length < 6) continue;

                String courseName = parts[0].trim();

                ArrayList<Integer> grades = new ArrayList<>();

                boolean valid = true;

                for (int i = 1; i <= 5; i++) {

                    try {
                        grades.add(Integer.parseInt(parts[i].trim()));
                    } catch (NumberFormatException e) {
                        valid = false;
                        break;
                    }
                }

                if (!valid) continue;

                // Check duplicates
                Course existing = searchCourse(courseName);

                if (existing != null) {

                    for (int i = 0; i < 5; i++) {
                        int sum = existing.getCourseGrades().get(i) + grades.get(i);
                        existing.getCourseGrades().set(i, sum);
                    }

                } else {

                    Course c = new Course(courseName, grades);
                    courses.add(c);
                }
            }

        } catch (IOException e) {

            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }

    // Find highest A%
    private static Course findBestCourse() {

        if (courses.isEmpty()) return null;

        Course best = courses.get(0);

        for (Course c : courses) {

            if (c.getAPercent() > best.getAPercent()) {
                best = c;
            }
        }

        return best;
    }

    // Linear search
    private static Course searchCourse(String name) {

        for (Course c : courses) {

            if (c.getCourseName().equalsIgnoreCase(name)) {
                return c;
            }
        }

        return null;
    }
}