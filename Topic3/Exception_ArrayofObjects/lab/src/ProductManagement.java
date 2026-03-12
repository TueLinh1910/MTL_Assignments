public class ProductManagement {

    private Product[] products = new Product[10];
    private int count = 0;

    public void addProduct(Product p) {

        if (count >= 10) {
            System.out.println("Product list is full.");
            return;
        }

        for (int i = 0; i < count; i++) {
            if (products[i].getProductID() == p.getProductID()) {
                System.out.println("Duplicate product ID.");
                return;
            }
        }

        products[count] = p;
        count++;

        System.out.println("Product added successfully.");
    }

    public Product findProductByID(int id) throws ProductNotFoundException {

        for (int i = 0; i < count; i++) {

            if (products[i].getProductID() == id) {
                return products[i];
            }

        }

        throw new ProductNotFoundException("Product with ID " + id + " not found.");
    }

    public void updateQuantity(int id, int quantity)
            throws ProductNotFoundException {

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        Product p = findProductByID(id);

        p.setQuantityInStock(quantity);

        System.out.println("Quantity updated successfully.");
    }
}
