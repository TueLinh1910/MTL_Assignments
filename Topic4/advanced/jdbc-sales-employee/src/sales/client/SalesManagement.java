package sales.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import sales.dao.CustomerDAO;
import sales.dao.EmployeeDAO;
import sales.entities.Customer;
import sales.entities.Employee;

public class SalesManagement {

    private static Scanner sc;

    private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;

    private CustomerForm customerForm;
    private EmployeeForm employeeForm;

    static final String GET_ALL_CUSTOMERS = "1";
    static final String ADD_NEW_CUSTOMER = "2";
    static final String UPDATE_CUSTOMER = "3";
    static final String REMOVE_CUSTOMER = "4";

    static final String GET_ALL_EMPLOYEES = "5";
    static final String ADD_NEW_EMPLOYEE = "6";
    static final String UPDATE_EMPLOYEE = "7";
    static final String REMOVE_EMPLOYEE = "8";

    static final String QUIT = "0";

    public SalesManagement() throws SQLException {
        initialize();
    }

    private void initialize() throws SQLException {
        sc = new Scanner(System.in);

        Connection conn = getConnection();

        customerDAO = new CustomerDAO(conn);
        employeeDAO = new EmployeeDAO(conn);

        customerForm = new CustomerForm(sc);
        employeeForm = new EmployeeForm(sc);
    }

    public static void main(String[] args) {
        String choice = "";

        do {
            try {
                SalesManagement management = new SalesManagement();

                createMenu();
                choice = sc.nextLine();

                switch (choice) {
                    case GET_ALL_CUSTOMERS:
                        management.displayAllCustomers();
                        break;

                    case ADD_NEW_CUSTOMER:
                        management.addCustomer();
                        break;

                    case UPDATE_CUSTOMER:
                        management.updateCustomer();
                        break;

                    case REMOVE_CUSTOMER:
                        management.removeCustomer();
                        break;

                    case GET_ALL_EMPLOYEES:
                        management.displayAllEmployees();
                        break;

                    case ADD_NEW_EMPLOYEE:
                        management.addEmployee();
                        break;

                    case UPDATE_EMPLOYEE:
                        management.updateEmployee();
                        break;

                    case REMOVE_EMPLOYEE:
                        management.removeEmployee();
                        break;

                    case QUIT:
                        System.out.println("Bye!");
                        break;

                    default:
                        System.out.println("Wrong choice");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (!choice.equals(QUIT));
    }

    private static void createMenu() {
        System.out.println("\n1. Get all customers");
        System.out.println("2. Add new an customer");
        System.out.println("3. Change customer information");
        System.out.println("4. Remove an customer");
        System.out.println("5. Get all employees");
        System.out.println("6. Add new an employee");
        System.out.println("7. Change employee information");
        System.out.println("8. Remove an employee");
        System.out.println("0. Quit");
        System.out.print("Your choice: ");
    }

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/sales";
            String user = "root";
            String password = "191004"; 

            return DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Can not open connection: " + e.getMessage());
        }
    }

    // ========================= CUSTOMER =========================

    private void displayAllCustomers() throws SQLException {
        ArrayList<Customer> customers = customerDAO.selectAll();

        if (customers == null || customers.isEmpty()) {
            System.out.println("Not found");
            return;
        }

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void addCustomer() throws SQLException {
        Customer customer = customerForm.getCustomer();

        if (customerDAO.insert(customer)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private void updateCustomer() throws SQLException {
        int id = customerForm.getId();
        Customer customer = customerForm.getCustomer();

        if (customerDAO.update(id, customer)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private void removeCustomer() throws SQLException {
        int id = customerForm.getId();

        if (customerDAO.delete(id)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    // ========================= EMPLOYEE =========================

    private void displayAllEmployees() throws SQLException {
        ArrayList<Employee> employees = employeeDAO.selectAll();

        if (employees == null || employees.isEmpty()) {
            System.out.println("Not found");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void addEmployee() throws SQLException {
        Employee employee = employeeForm.getEmployee();

        if (employeeDAO.insert(employee)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private void updateEmployee() throws SQLException {
        int id = employeeForm.getId();
        Employee employee = employeeForm.getEmployee();

        if (employeeDAO.update(id, employee)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private void removeEmployee() throws SQLException {
        int id = employeeForm.getId();

        if (employeeDAO.delete(id)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }
}