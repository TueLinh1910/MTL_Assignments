package training.utils;

import java.util.ArrayList;
import training.entities.Course;

public class Validator {

    public static boolean validateCode(String code) {

        return code.matches("RA\\d{3}");
    }

    public static boolean isDuplicatedCode(String code, ArrayList<Course> courses) {

        for (Course c : courses) {

            if (c.getCode().equalsIgnoreCase(code)) {
                return false;
            }

        }

        return true;
    }

    public static boolean validateFlag(String flag) {

        return flag.equalsIgnoreCase("optional")
                || flag.equalsIgnoreCase("prerequisite")
                || flag.equalsIgnoreCase("N/A");
    }

    public static boolean validateDuration(short d) {

        return d > 0;
    }
}