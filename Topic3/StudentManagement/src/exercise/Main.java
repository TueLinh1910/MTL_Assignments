package exercise;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println("1 Create student");
            System.out.println("2 Display all students");
            System.out.println("3 Find student by id");
            System.out.println("4 Update student by id");
            System.out.println("5 Quit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:
                    manager.createStudent();
                    break;

                case 2:
                    manager.displayAll();
                    break;

                case 3:
                    manager.findStudent();
                    break;

                case 4:
                    manager.updateStudent();
                    break;

                case 5:
                    return;

            }
        }
    }
}