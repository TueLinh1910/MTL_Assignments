package sales.client;

import java.util.Scanner;
import sales.entities.Customer;

public class CustomerForm {

    private final Scanner sc;

    public CustomerForm(Scanner sc) {
        this.sc = sc;
    }

    // Nhập ID (dùng cho update, delete)
    public int getId() {
        System.out.print("Enter id: ");
        return Integer.parseInt(sc.nextLine());
    }

    // Nhập thông tin khách hàng
    public Customer getCustomer() {
        Customer customer = new Customer();

        System.out.print("Enter customer name: ");
        customer.setName(sc.nextLine());

        System.out.print("Enter contact name: ");
        customer.setContact(sc.nextLine());

        System.out.print("Enter address: ");
        customer.setAddress(sc.nextLine());

        System.out.print("Enter city: ");
        customer.setCity(sc.nextLine());

        System.out.print("Enter post code: ");
        customer.setPostCode(sc.nextLine());

        System.out.print("Enter country: ");
        customer.setCountry(sc.nextLine());

        return customer;
    }
}