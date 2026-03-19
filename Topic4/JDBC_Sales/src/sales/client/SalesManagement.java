package sales.client;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import sales.dao.CustomerDAO;
import sales.entities.Customer;

public class SalesManagement {

    private static Scanner sc;
    private CustomerDAO customerDAO;
    private CustomerForm customerForm;

    static final String GET_ALL_CUSTOMERS = "1";
    static final String ADD_NEW_CUSTOMER = "2";
    static final String UPDATE_CUSTOMER = "3";
    static final String REMOVE_CUSTOMER = "4";
    static final String QUIT = "0";

    // Khởi tạo
    private void initialize() throws SQLException {
        sc = new Scanner(System.in);
        Connection conn = getConnection();

        customerForm = new CustomerForm(sc);
        customerDAO = new CustomerDAO(conn);
    }

    public SalesManagement() throws SQLException {
        initialize();
    }

    public static void main(String[] args) {
        String choice = "";
        SalesManagement management;

        do {
            try {
                management = new SalesManagement();
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
        System.out.println("2. Add new customer");
        System.out.println("3. Update customer");
        System.out.println("4. Remove customer");
        System.out.println("0. Quit");
        System.out.print("Your choice: ");
    }

    // Kết nối DB
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/sales";
            String user = "root";        
            String password = "191004";  

            return DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found");
        }
    }

    // Hiển thị
    private void displayAllCustomers() throws SQLException {
        ArrayList<Customer> list = customerDAO.selectAll();

        if (list == null || list.isEmpty()) {
            System.out.println("No data");
            return;
        }

        for (Customer c : list) {
            System.out.println(c);
        }
    }

    // Thêm
    private void addCustomer() throws SQLException {
        Customer c = customerForm.getCustomer();

        if (customerDAO.insert(c)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    // Sửa
    private void updateCustomer() throws SQLException {
        int id = customerForm.getId();
        Customer c = customerForm.getCustomer();

        if (customerDAO.update(id, c)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    // Xóa
    private void removeCustomer() throws SQLException {
        int id = customerForm.getId();

        if (customerDAO.delete(id)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }
}