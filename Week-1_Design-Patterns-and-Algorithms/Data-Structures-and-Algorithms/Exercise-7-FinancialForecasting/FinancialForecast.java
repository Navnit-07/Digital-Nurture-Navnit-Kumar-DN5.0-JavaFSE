// Financial forecasting using recursion
public class FinancialForecast {

    /*
     * Recursion means a method calls itself to solve a smaller part of the problem.
     * Here we calculate future value based on past growth rate.
     *
     * Formula: futureValue = currentValue * (1 + growthRate)
     * We apply this for each year recursively.
     */

    // Recursive method to predict future value after given years
    public static double predictFutureValue(double currentValue, double growthRate, int years) {

        // Base case - no more years left
        if (years == 0) {
            return currentValue;
        }

        // Recursive case - apply growth for one year and call again
        double nextValue = currentValue * (1 + growthRate);
        return predictFutureValue(nextValue, growthRate, years - 1);
    }

    public static void main(String[] args) {

        double currentValue = 10000.0;
        double growthRate = 0.05;  // 5% growth per year
        int years = 5;

        double futureValue = predictFutureValue(currentValue, growthRate, years);

        System.out.println("Current Value: $" + currentValue);
        System.out.println("Growth Rate: " + (growthRate * 100) + "%");
        System.out.println("Years: " + years);
        System.out.println("Predicted Future Value: $" + futureValue);

        /*
         * ANALYSIS:
         *
         * Time Complexity: O(n) where n = number of years
         * Each recursive call reduces years by 1, so we make n calls.
         *
         * Optimization:
         * Recursion can cause stack overflow for very large years.
         * We can use iteration (loop) instead - same result, no extra stack calls.
         * Or use memoization if same sub-problems repeat.
         *
         * Iterative version is simpler:
         * for (int i = 0; i < years; i++) {
         *     currentValue = currentValue * (1 + growthRate);
         * }
         */
    }
}
