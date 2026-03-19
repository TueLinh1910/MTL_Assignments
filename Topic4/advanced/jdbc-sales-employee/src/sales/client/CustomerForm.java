package sales.client;

import java.util.Scanner;
import sales.entities.Customer;

public class CustomerForm {
    private final Scanner sc;

    public CustomerForm(Scanner sc) {
        this.sc = sc;
    }

    // nhập ID
    public int getId() {
        int id;
        System.out.print("Enter id: ");
        id = Integer.parseInt(sc.nextLine());
        return id;
    }

    // nhập thông tin khách hàng
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
