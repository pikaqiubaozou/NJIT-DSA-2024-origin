package oy.tol.tra;

import java.util.Comparator;
import java.util.function.Predicate;

public class Algorithms {
    public static <T extends Comparable<T>> void sortWithComparator(T[] arr, Comparator<? super T> comparator) {
        int n = arr.length;
        boolean swapped = false;
        while (n > 1 && !swapped) {
            swapped = true;
            for (int i = 0; i < n - 1; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = false;
                }
            }
            n--;
        }
    }

    public static <T extends Comparable<T>> void fastSort(T[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
        if (left < right) {
            int Index = partition(arr, left, right);
            quickSort(arr, left, Index - 1);
            quickSort(arr, Index + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int left, int right) {
        T pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        T temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        return i + 1;
    }

    public static <T extends Comparable<T>> int partitionByRule(T[] arr, int count, Predicate<T> rule) {
        for (int i = 0; i < count; i++) {
            if (rule.test(arr[i])) {
                for (int j = i; j < count; j++) {
                    if (!rule.test(arr[j])) {
                        arr[j] = arr[i];
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < count; i++) {
            if (rule.test(arr[i])) {
                return i;
            }
        }
        return 0;
    }

    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        T[] sorted = (T[]) new Comparable[arr.length];
        mergeSort(arr, sorted, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] arr, T[] sorted, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, sorted, start, mid);
            mergeSort(arr, sorted, mid + 1, end);
            merge(arr, sorted, start, mid, end);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] arr, T[] sorted, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = start;
        while (i <= mid && j <= end) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                sorted[k++] = arr[i++];
            } else {
                sorted[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            sorted[k++] = arr[i++];
        }
        while (j <= end) {
            sorted[k++] = arr[j++];
        }
        for (k = start; k <= end; k++) {
            arr[k] = sorted[k];
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}