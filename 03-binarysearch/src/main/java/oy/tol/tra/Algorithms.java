package oy.tol.tra;

public class Algorithms {

    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            return -1;
        }

        int mid = (fromIndex + toIndex) / 2;

        if (aValue.compareTo(fromArray[mid]) == 0) {
            return mid;
        } else if (aValue.compareTo(fromArray[mid]) < 0) {
            return binarySearch(aValue, fromArray, fromIndex, mid - 1);
        } else {
            return binarySearch(aValue, fromArray, mid + 1, toIndex);
        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static void sort(Integer[] array) {
        quickSort(array);
    }

    public static void sort(String[] array) {
        quickSort(array);
    }
}