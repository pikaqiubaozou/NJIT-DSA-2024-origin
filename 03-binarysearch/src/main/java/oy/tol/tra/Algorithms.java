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
    public static <T extends Comparable<T>> void fastSort(T[] fromArray, int fromIndex, int toIndex) {
        if (fromIndex < toIndex) {
            int pivotIndex = partition(fromArray, fromIndex, toIndex);
            fastSort(fromArray, fromIndex, pivotIndex - 1);
            fastSort(fromArray, pivotIndex + 1, toIndex);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] fromArray, int fromIndex, int toIndex) {
        int pivotIndex = (fromIndex + toIndex) / 2;
        T pivotValue = fromArray[pivotIndex];
        swap(fromArray, pivotIndex, toIndex);
        int storeIndex = fromIndex;

        for (int i = fromIndex; i < toIndex; i++) {
            if (fromArray[i].compareTo(pivotValue) < 0) {
                swap(fromArray, storeIndex, i);
                storeIndex++;
            }
        }

        swap(fromArray, storeIndex, toIndex);
        return storeIndex;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void sort(Integer[] array) {
        fastSort(array, 0, array.length - 1);
    }

    public static void sort(String[] array) {
        fastSort(array, 0, array.length - 1);
    }
}