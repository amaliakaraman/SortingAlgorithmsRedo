// amalia karaman
// problem 7 - performance testing

import java.util.*;

class Tester {
    private SortingAlgorithm algorithm; // the sorting algorithm to test
    private Random rand; // random num generator

    public Tester(SortingAlgorithm algorithm) { // constructor takes a sorting algorithm
        this.algorithm = algorithm;
        this.rand = new Random();
    }

    public double singleTest(int size) { // run one test of given size, return time in ms
        int[] arr = new int[size]; // make array
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(); // fill with random ints
        }
        long start = System.nanoTime(); // start timer
        algorithm.sorty(arr); // run sort
        long end = System.nanoTime(); // end timer
        return (end - start) / 1_000_000.0; // convert ns to ms
    }

    public void test(int iterations, int size) { // run multiple tests and print avg
        double total = 0;
        for (int i = 0; i < iterations; i++) {
            total += singleTest(size); // add up times
        }
        double avg = total / iterations; // avg time
        System.out.println("Sorted " + size + " elements in " + avg + " ms (avg)");
    }
}
