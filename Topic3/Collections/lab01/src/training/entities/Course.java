package training.entities;

import java.util.ArrayList;
import java.util.Scanner;
import training.utils.Validator;

public class Course {

    private String code;
    private String name;
    private boolean status;
    private short duration;
    private String flag;

    public Course() {
    }

    public Course(String code, String name, boolean status, short duration, String flag) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public short getDuration() {
        return duration;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return code + " | " + name + " | " + status + " | " + duration + " | " + flag;
    }

    public void input(Scanner sc, ArrayList<Course> courses) {

        while (true) {

            System.out.print("Enter code: ");
            String c = sc.nextLine();

            if (Validator.validateCode(c) && Validator.isDuplicatedCode(c, courses)) {
                code = c;
                break;
            }

            System.out.println("Invalid or duplicated code!");
        }

        System.out.print("Enter name: ");
        name = sc.nextLine();

        while (true) {

            System.out.print("Enter status (true/false): ");
            String s = sc.nextLine();

            if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")) {
                status = Boolean.parseBoolean(s);
                break;
            }

            System.out.println("Invalid status!");
        }

        while (true) {

            try {

                System.out.print("Enter duration: ");
                short d = Short.parseShort(sc.nextLine());

                if (Validator.validateDuration(d)) {
                    duration = d;
                    break;
                }

            } catch (Exception e) {
            }

            System.out.println("Invalid duration!");
        }

        while (true) {

            System.out.print("Enter flag (optional/prerequisite/N/A): ");
            String f = sc.nextLine();

            if (Validator.validateFlag(f)) {
                flag = f;
                break;
            }

            System.out.println("Invalid flag!");
        }
    }
}