import java.util.Scanner;

public class Painting extends Item {

    private int height;
    private int width;
    private boolean isWaterColor;
    private boolean isFramed;

    public Painting() {}

    public Painting(String id, int value, String creator,
                    int height, int width,
                    boolean isWaterColor, boolean isFramed) {

        super(id, value, creator);
        this.height = height;
        this.width = width;
        this.isWaterColor = isWaterColor;
        this.isFramed = isFramed;
    }

    public void input() {

        super.input();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter height: ");
        height = Integer.parseInt(sc.nextLine());

        System.out.print("Enter width: ");
        width = Integer.parseInt(sc.nextLine());

        System.out.print("Is watercolor (true/false): ");
        isWaterColor = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Is framed (true/false): ");
        isFramed = Boolean.parseBoolean(sc.nextLine());
    }

    public String toString() {
        return super.toString() + " | " + height + " | " + width + " | " + isWaterColor + " | " + isFramed;
    }
}