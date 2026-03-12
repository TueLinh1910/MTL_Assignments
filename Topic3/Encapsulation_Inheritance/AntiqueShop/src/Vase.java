import java.util.Scanner;

public class Vase extends Item {

    private int height;
    private String material;

    public Vase() {}

    public Vase(String id, int value, String creator, int height, String material) {
        super(id, value, creator);
        this.height = height;
        this.material = material;
    }

    public void input() {

        super.input();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter height: ");
        height = Integer.parseInt(sc.nextLine());

        System.out.print("Enter material: ");
        material = sc.nextLine();
    }

    public String toString() {
        return super.toString() + " | " + height + " | " + material;
    }
}