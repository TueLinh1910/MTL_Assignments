import java.util.Scanner;

public class Statue extends Item {

    private int weight;
    private String color;

    public Statue() {}

    public Statue(String id, int value, String creator, int weight, String color) {
        super(id, value, creator);
        this.weight = weight;
        this.color = color;
    }

    public void input() {

        super.input();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter weight: ");
        weight = Integer.parseInt(sc.nextLine());

        System.out.print("Enter color: ");
        color = sc.nextLine();
    }

    public String toString() {
        return super.toString() + " | " + weight + " | " + color;
    }
}