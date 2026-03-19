package sales.client;

import java.util.Scanner;
import sales.entities.Employee;

public class EmployeeForm {

    private final Scanner sc;

    public EmployeeForm(Scanner sc) {
        this.sc = sc;
    }

    // nhập ID
    public int getId() {
        System.out.print("Enter employee id: ");
        return Integer.parseInt(sc.nextLine());
    }

    // nhập thông tin nhân viên
    public Employee getEmployee() {
    Employee emp = new Employee();

    System.out.print("Enter last name: ");
    emp.setLastName(sc.nextLine());

    System.out.print("Enter first name: ");
    emp.setFirstName(sc.nextLine());

    System.out.print("Enter birth date (yyyy-MM-dd): ");
    emp.setBirthdate(sc.nextLine());

    System.out.print("Enter supervisor id ( Enter if not ): ");
    String supervisorInput = sc.nextLine();

    if (supervisorInput.trim().isEmpty() || supervisorInput.equals("0")) {
        emp.setSupervisor(null);
    } else {
        emp.setSupervisor(Integer.parseInt(supervisorInput));
    }

    return emp;
    }
}