package exercise;

import java.util.Scanner;

public class TraineeForm {

    private Scanner scanner;

    // Constructor
    public TraineeForm(Scanner scanner) {
        this.scanner = scanner;
    }

    // nhập id
    public String getId() {

        while (true) {

            System.out.print("Enter id: ");
            String id = scanner.nextLine();

            if (id.isEmpty()) {
                System.out.println("Id cannot be empty");
            } else {
                return id;
            }
        }
    }

    // nhập thông tin trainee
    public Trainee getTrainee() {

        Trainee t = new Trainee();

        // validate name
        String name;
        while (true) {

            System.out.print("Enter name: ");
            name = scanner.nextLine();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty");
            } else {
                break;
            }
        }
        t.setName(name);

        // validate gender
        String gender;
        while (true) {

            System.out.print("Enter gender (male/female): ");
            gender = scanner.nextLine();

            if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
                break;
            } else {
                System.out.println("Gender must be male or female");
            }
        }
        t.setGender(gender);

        // validate age
        byte age;
        while (true) {

            System.out.print("Enter age: ");
            age = Byte.parseByte(scanner.nextLine());

            if (age >= 6) {
                break;
            } else {
                System.out.println("Age must be >= 6");
            }
        }
        t.setAge(age);

        return t;
    }
}