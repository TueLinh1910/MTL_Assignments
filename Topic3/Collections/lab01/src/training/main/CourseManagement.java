package training.main;

import java.util.ArrayList;
import java.util.Scanner;
import training.entities.Course;

public class CourseManagement {

    private ArrayList<Course> courses = new ArrayList<>();

    public void input(Scanner sc) {

        Course c = new Course();

        c.input(sc, courses);

        courses.add(c);

        System.out.println("Course added successfully.");
    }

    public ArrayList<Course> search(String type, Object data) {

        ArrayList<Course> result = new ArrayList<>();

        for (Course c : courses) {

            if (type.equalsIgnoreCase("code")) {

                if (c.getCode().equalsIgnoreCase((String) data)) {
                    result.add(c);
                }

            }

            if (type.equalsIgnoreCase("name")) {

                if (c.getName().toLowerCase().contains(((String) data).toLowerCase())) {
                    result.add(c);
                }

            }

        }

        return result;
    }

    public void displayAll(String flag) {

        for (Course c : courses) {

            if (c.getFlag().equalsIgnoreCase(flag)) {

                System.out.println(c);

            }

        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CourseManagement cm = new CourseManagement();

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Create course");
            System.out.println("2. Search course");
            System.out.println("3. Display courses by flag");
            System.out.println("4. Quit");

            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:

                    cm.input(sc);
                    break;

                case 2:

                    System.out.print("Search by (code/name): ");
                    String type = sc.nextLine();

                    System.out.print("Enter data: ");
                    String data = sc.nextLine();

                    ArrayList<Course> result = cm.search(type, data);

                    if (result.isEmpty()) {

                        System.out.println("No course found.");

                    } else {

                        for (Course c : result) {
                            System.out.println(c);
                        }

                    }

                    break;

                case 3:

                    System.out.print("Enter flag: ");
                    String flag = sc.nextLine();

                    cm.displayAll(flag);

                    break;

                case 4:

                    System.out.println("Program ended.");
                    return;

            }
        }
    }
}