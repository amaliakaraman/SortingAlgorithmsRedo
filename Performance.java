// Amalia Karaman
// Performance Domparison

import java.io.*;
import java.util.*;

public class Performance {
    public static void main(String[] args) throws IOException {
        SortingAlgorithm[] algorithms = { // all the sorting algorithms
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new ShellSort(),
                new QuickSort(),
                new MergeSort()
        };

        String[] names = {
                "Bubble Sort", "Insertion Sort", "Selection Sort",
                "Shell Sort", "Quick Sort", "Merge Sort"
        };

        int[] sizes = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};

        FileWriter fw = new FileWriter("performance_report.txt");

        for (int i = 0; i < algorithms.length; i++) {
            fw.write("Sorting algorithm – " + names[i] + "\n");
            System.out.println("Testing: " + names[i]);
            Tester tester = new Tester(algorithms[i]);
            for (int size : sizes) {
                double total = 0;
                for (int j = 0; j < 20; j++) {
                    total += tester.singleTest(size); // run test
                }
                double avg = total / 20.0; // calc avg
                String result = "Sorted " + size + " elements in " + avg + " ms (avg)\n";
                fw.write(result);
                System.out.print(result);
            }
            fw.write("\n");
        }
        fw.close(); // close the file
    }
}

