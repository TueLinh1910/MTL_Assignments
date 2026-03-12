public class Product {

    private int productID;
    private String name;
    private double price;
    private int quantityInStock;

    public Product(int productID, String name, double price, int quantityInStock) {

        if (price < 0 || quantityInStock < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }

        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {

        if (quantityInStock < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative.");
        }

        this.quantityInStock = quantityInStock;
    }

    public void displayProductInfo() {
        System.out.println("ID: " + productID +
                " | Name: " + name +
                " | Price: " + price +
                " | Quantity: " + quantityInStock);
    }
}