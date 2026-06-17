// Demo class for linear and binary search on e-commerce products
public class SearchDemo {

    public static void main(String[] args) {

        // Unsorted array for linear search
        Product[] products = {
            new Product(105, "Keyboard", "Electronics"),
            new Product(102, "Mouse", "Electronics"),
            new Product(108, "Notebook", "Stationery"),
            new Product(101, "Pen", "Stationery"),
            new Product(103, "Monitor", "Electronics")
        };

        // Sorted array for binary search (sorted by productId)
        Product[] sortedProducts = {
            new Product(101, "Pen", "Stationery"),
            new Product(102, "Mouse", "Electronics"),
            new Product(103, "Monitor", "Electronics"),
            new Product(105, "Keyboard", "Electronics"),
            new Product(108, "Notebook", "Stationery")
        };

        System.out.println("=== Linear Search Demo ===");
        Product found = LinearSearch.searchByName(products, "Mouse");
        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Product not found");
        }

        System.out.println("\n=== Binary Search Demo ===");
        Product foundById = BinarySearch.searchById(sortedProducts, 103);
        if (foundById != null) {
            System.out.println("Found: " + foundById);
        } else {
            System.out.println("Product not found");
        }

        /*
         * ANALYSIS:
         *
         * Big O Notation:
         * Big O tells us how fast an algorithm grows when input size increases.
         * It helps compare algorithms and pick the best one for large data.
         *
         * Linear Search:
         * - Best case: O(1) - item is at first position
         * - Average case: O(n) - item is somewhere in middle
         * - Worst case: O(n) - item is at last or not found
         *
         * Binary Search:
         * - Best case: O(1) - item is at middle
         * - Average case: O(log n)
         * - Worst case: O(log n)
         *
         * For e-commerce platform:
         * Binary search is better when products are sorted and list is large,
         * because it is much faster. Linear search is fine for small lists
         * or when data is not sorted.
         */
    }
}
