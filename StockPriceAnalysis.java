/**
 * This program performs several operations on stock prices using both an array and an ArrayList.
 * It implements the following functionalities:
 * 
 * 1. Calculates the average stock price from an array.
 * 2. Finds the maximum stock price from an array.
 * 3. Counts how many times a specific stock price appears in the array.
 * 4. Computes the cumulative sum of stock prices using an ArrayList.
 *
 * Assumptions:
 * - The input array and ArrayList are not null.
 * - They contain at least one element.
 *
 * The logic uses standard looping constructs (for loops) for processing both arrays and ArrayLists.
 */

import java.util.ArrayList;

public class StockPriceAnalysis {

    // Method to calculate the average stock price from an array
    public static double calculateAveragePrice(int[] prices) {
        int sum = 0;
        for (int price : prices) {
            sum += price;
        }
        return (double) sum / prices.length;
    }

    // Method to find the maximum stock price from an array
    public static int findMaximumPrice(int[] prices) {
        int max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > max) {
                max = prices[i];
            }
        }
        return max;
    }

    // Method to count the occurrences of a specific price in the array
    public static int countOccurrences(int[] prices, int target) {
        int count = 0;
        for (int price : prices) {
            if (price == target) {
                count++;
            }
        }
        return count;
    }

    // Method to compute the cumulative sum of stock prices from an ArrayList
    public static ArrayList<Integer> computeCumulativeSum(ArrayList<Integer> pricesList) {
        ArrayList<Integer> cumulativeSum = new ArrayList<>();
        int sum = 0;
        for (int price : pricesList) {
            sum += price;
            cumulativeSum.add(sum);
        }
        return cumulativeSum;
    }

    // Main method to test the implemented functionalities
    public static void main(String[] args) {
        // Sample data
        int[] stockPricesArray = {100, 120, 110, 130, 100, 120};
        ArrayList<Integer> stockPricesList = new ArrayList<>();
        for (int price : stockPricesArray) {
            stockPricesList.add(price);
        }

        // Test calculateAveragePrice
        double average = calculateAveragePrice(stockPricesArray);
        System.out.println("Average Stock Price: " + average);

        // Test findMaximumPrice
        int maxPrice = findMaximumPrice(stockPricesArray);
        System.out.println("Maximum Stock Price: " + maxPrice);

        // Test countOccurrences for price 120
        int occurrences = countOccurrences(stockPricesArray, 120);
        System.out.println("Occurrences of 120: " + occurrences);

        // Test computeCumulativeSum
        ArrayList<Integer> cumulativeSums = computeCumulativeSum(stockPricesList);
        System.out.println("Cumulative Sums: " + cumulativeSums);
    }
}
