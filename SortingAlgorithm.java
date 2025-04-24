// Amalia Karaman
// Sorting Algorithm

// Interface all sorts follow
interface SortingAlgorithm {
    int[] sorty(int[] input); // sort method every class implements
}

// bubble sort class
class BubbleSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone(); // make a copy to avoid changing original
        int n = arr.length; // store length
        for (int i = 0; i < n - 1; i++) { // outer loop
            for (int j = 0; j < n - i - 1; j++) { // inner loop
                if (arr[j] > arr[j + 1]) { // if out of order, swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr; // return sorted array
    }
}

// insertion sort class
class InsertionSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone(); // copy input
        for (int i = 1; i < arr.length; i++) { // start from second item
            int key = arr[i]; // save item to insert
            int j = i - 1; // check previous items
            while (j >= 0 && arr[j] > key) { // shift if bigger
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // insert at right spot
        }
        return arr;
    }
}

// selection sort class
class SelectionSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone(); // copy input
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; // track index of min value
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) { // update if smaller found
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp; // swap min with current
        }
        return arr;
    }
}

// shell sort class
class ShellSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone(); // copy input
        for (int gap = arr.length / 2; gap > 0; gap /= 2) { // reduce gap
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) { // shift elements
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp; // insert in correct spot
            }
        }
        return arr;
    }
}

// quick sort class
class QuickSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone(); // copy input
        quickSort(arr, 0, arr.length - 1); // call helper
        return arr;
    }
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high); // find pivot index
            quickSort(arr, low, pi - 1); // sort left
            quickSort(arr, pi + 1, high); // sort right
        }
    }
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // last item as pivot
        int i = low - 1; // smaller element index
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp; // move pivot to correct spot
        return i + 1;
    }
}

// merge sort class
class MergeSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone(); // copy input
        mergeSort(arr, 0, arr.length - 1); // call recursive sort
        return arr; // return sorted array
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2; // calc midpoint
            mergeSort(arr, left, mid); // sort left half
            mergeSort(arr, mid + 1, right); // sort right half
            merge(arr, left, mid, right); // merge halves
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1; // left size
        int n2 = right - mid; // right size

        int[] L = new int[n1]; // left array
        int[] R = new int[n2]; // right array

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // merge L and R into arr
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        // copy remaining L
        while (i < n1) arr[k++] = L[i++];
        // copy remaining R
        while (j < n2) arr[k++] = R[j++];
    }
}