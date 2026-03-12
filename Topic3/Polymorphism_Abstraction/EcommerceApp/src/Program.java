import java.util.Scanner;

public class Program {

    Product[] products = new Product[100];
    int numOfProduct = 0;
    final int MAX = 100;

    public Program() {}

    public void addProduct(Product product) {

        if (numOfProduct >= MAX) {
            System.out.println("List full!");
            return;
        }

        products[numOfProduct] = product;
        numOfProduct++;
    }

    public void displayProducts() {

        for (int i = 0; i < numOfProduct; i++) {
            products[i].display();
        }
    }

    public Product findProduct(int id) {

        for (int i = 0; i < numOfProduct; i++) {

            if (products[i].id == id)
                return products[i];
        }

        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Program program = new Program();

        int choice;

        do {

            System.out.println("\n1 Add Electronics");
            System.out.println("2 Add Clothing");
            System.out.println("3 Display Products");
            System.out.println("4 Find Product by ID");
            System.out.println("5 Exit");

            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Price: ");
                    float price = sc.nextFloat();
                    sc.nextLine();

                    System.out.print("Brand: ");
                    String brand = sc.nextLine();

                    Electronics e =
                            new Electronics(id, name, price, brand);

                    program.addProduct(e);

                    break;

                case 2:

                    System.out.print("ID: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    name = sc.nextLine();

                    System.out.print("Price: ");
                    price = sc.nextFloat();
                    sc.nextLine();

                    System.out.print("Size: ");
                    String size = sc.nextLine();

                    Clothing c =
                            new Clothing(id, name, price, size);

                    program.addProduct(c);

                    break;

                case 3:

                    program.displayProducts();

                    break;

                case 4:

                    System.out.print("Enter ID: ");
                    int searchId = sc.nextInt();

                    Product p = program.findProduct(searchId);

                    if (p != null)
                        p.display();
                    else
                        System.out.println("Not found");

                    break;

            }

        } while (choice != 5);

    }
}