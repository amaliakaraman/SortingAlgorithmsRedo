// Amalia Karaman
// K-sorted performance testing

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class KSortedPerformance {
    private static final int[] sizes = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
    private static final int iterations = 20;

    public static void main(String[] args) {
        SortingAlgorithm[] algorithms = { // all sorting algorithms
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new ShellSort(),
                new MergeSort(),
                new QuickSort()
        };

        try (FileWriter fw = new FileWriter("k_sorted_performance_report.txt")) {
            System.out.println("testing on 10-sorted data...");

            for (SortingAlgorithm algorithm : algorithms) {
                String name = algorithm.getClass().getSimpleName();
                System.out.println("\n" + name);
                fw.write("sorting algorithm – " + name + "\n");

                for (int size : sizes) {
                    double avgTime = runTest(algorithm, size); // get avg
                    String result = "sorted " + size + " elements in " + String.format("%.3f", avgTime) + " ms (avg)\n";
                    fw.write(result);
                    System.out.print(result);
                }
                fw.write("\n");
            }

            System.out.println("\ndone. results in k_sorted_performance_report.txt");

        } catch (IOException e) {
            System.out.println("file error: " + e.getMessage());
        }
    }

    private static double runTest(SortingAlgorithm algorithm, int size) {
        double total = 0;
        Random rand = new Random();

        for (int i = 0; i < iterations; i++) {
            int[] base = generateKSorted(size, rand); // 10-sorted array
            int[] arr = base.clone(); // copy for fairness
            long start = System.nanoTime();
            algorithm.sorty(arr); // run sort
            long end = System.nanoTime();
            total += (end - start) / 1_000_000.0; // ms
        }
        return total / iterations;
    }

    private static int[] generateKSorted(int size, Random rand) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i + 1;

        int k = 10;
        for (int i = 0; i < size; i++) {
            int min = Math.max(0, i - k);
            int max = Math.min(size - 1, i + k);
            int j = rand.nextInt(max - min + 1) + min;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
}
