import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductManagement manager = new ProductManagement();

        while (true) {

            System.out.println("\n1. Add Product");
            System.out.println("2. Find Product");
            System.out.println("3. Update Quantity");
            System.out.println("4. Exit");

            System.out.print("Choice: ");
            int choice = sc.nextInt();

            try {

                if (choice == 1) {

                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Quantity: ");
                    int quantity = sc.nextInt();

                    Product p = new Product(id, name, price, quantity);

                    manager.addProduct(p);

                }

                else if (choice == 2) {

                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    Product p = manager.findProductByID(id);

                    p.displayProductInfo();

                }

                else if (choice == 3) {

                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    System.out.print("New Quantity: ");
                    int q = sc.nextInt();

                    manager.updateQuantity(id, q);

                }

                else if (choice == 4) {
                    break;
                }

            }

            catch (ProductNotFoundException e) {

                System.out.println(e.getMessage());

            }

            catch (IllegalArgumentException e) {

                System.out.println(e.getMessage());

            }

            catch (Exception e) {

                System.out.println("Invalid input.");

                sc.nextLine();

            }

        }

        sc.close();
    }
}
