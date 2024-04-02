package oy.tol.tra;

import java.util.Arrays;

public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] itemArray;
    private int capacity;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private static final int DEFAULT_QUEUE_SIZE = 10;
    private static final int EXPANSION_SIZE = 10;

    public QueueImplementation() {
        this(DEFAULT_QUEUE_SIZE);
    }

    public QueueImplementation(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        }
        this.capacity = capacity;
        this.itemArray = new Object[capacity];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        size--;
        E tmp = (E) itemArray[head];
        itemArray[head] = null;
        head = (head + 1) % capacity;
        return tmp;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (tail == capacity && head != 0) {
            itemArray[0] = element;
            tail = 1;
            size++;
            return;
        }
        if (size == capacity) {
            Object[] newOne = new Object[capacity + 10];
            for (int i = 0; i < size; ++i) {
                newOne[i] = itemArray[(head + i) % capacity];
            }
            tail = size;
            head = 0;
            this.capacity = capacity + 10;
            itemArray = newOne;
            itemArray[tail] = element;
            tail++;
            size++;
            return;
        }
        itemArray[tail] = element;
        tail++;
        size++;
    }


    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return (E) itemArray[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(itemArray, null);
        head = tail = size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (tail >= head) {
            for (int i = head; i < tail; i++) {
                builder.append(itemArray[i]);
                if (i < tail - 1) {
                    builder.append(", ");
                }
            }
        } else {
            for (int i = head; i < head + size; i++) {
                builder.append(itemArray[i % capacity]);
                if (i < head + size - 1) {
                    builder.append(", ");
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private void expandCapacity() {
        int newCapacity = capacity + EXPANSION_SIZE;
        Object[] newArray = new Object[newCapacity];
        if (tail >= head) {
            System.arraycopy(itemArray, head, newArray, 0, size);
        } else {
            System.arraycopy(itemArray, head, newArray, 0, capacity - head);
            System.arraycopy(itemArray, 0, newArray, capacity - head, tail);
        }
        itemArray = newArray;
        capacity = newCapacity;
        head = 0;
        tail = size;
    }
}