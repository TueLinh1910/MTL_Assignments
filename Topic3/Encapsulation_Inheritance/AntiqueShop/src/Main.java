import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ItemList list = new ItemList();

        int choice;

        do {

            System.out.println("\n1 Add Vase");
            System.out.println("2 Add Statue");
            System.out.println("3 Add Painting");
            System.out.println("4 Display All");
            System.out.println("5 Find by Creator");
            System.out.println("6 Display by Type");
            System.out.println("7 Exit");

            System.out.print("Your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    Vase v = new Vase();
                    v.input();
                    list.addItem(v);
                    break;

                case 2:
                    Statue s = new Statue();
                    s.input();
                    list.addItem(s);
                    break;

                case 3:
                    Painting p = new Painting();
                    p.input();
                    list.addItem(p);
                    break;

                case 4:
                    list.displayAll();
                    break;

                case 5:
                    System.out.print("Enter creator: ");
                    String c = sc.nextLine();

                    Item result = list.findItem(c);

                    if (result != null)
                        System.out.println(result);
                    else
                        System.out.println("Not found");

                    break;

                case 6:
                    System.out.print("Enter type (vase/statue/painting): ");
                    String type = sc.nextLine();
                    list.displayByType(type);
                    break;
            }

        } while (choice != 7);
    }
}