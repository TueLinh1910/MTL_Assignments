import java.util.Scanner;

public class Item {

    protected String id;
    protected int value;
    protected String creator;

    public Item() {}

    public Item(String id, int value, String creator) {
        this.id = id;
        this.value = value;
        this.creator = creator;
    }

    public void input() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter id: ");
        id = sc.nextLine();

        System.out.print("Enter value: ");
        value = Integer.parseInt(sc.nextLine());

        System.out.print("Enter creator: ");
        creator = sc.nextLine();
    }

    public String toString() {
        return id + " | " + value + " | " + creator;
    }
}