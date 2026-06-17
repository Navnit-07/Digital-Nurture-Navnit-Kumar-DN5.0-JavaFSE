// Binary search - works only on sorted array (sorted by productId)
public class BinarySearch {

    public static Product searchById(Product[] sortedProducts, int id) {
        int left = 0;
        int right = sortedProducts.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (sortedProducts[mid].getProductId() == id) {
                return sortedProducts[mid];
            } else if (sortedProducts[mid].getProductId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
