// Linear search - checks each product one by one
public class LinearSearch {

    public static Product searchByName(Product[] products, String name) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductName().equalsIgnoreCase(name)) {
                return products[i];
            }
        }
        return null;
    }

    public static Product searchById(Product[] products, int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId() == id) {
                return products[i];
            }
        }
        return null;
    }
}
