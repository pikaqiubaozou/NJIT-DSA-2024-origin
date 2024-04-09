package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] itemArray;
    private int capacity;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private static final int DEFAULT_QUEUE_SIZE = 10;
    private static final int EXPANSION_FACTOR = 10;

    public QueueImplementation() throws QueueAllocationException {
        this(DEFAULT_QUEUE_SIZE);
    }

    public QueueImplementation(int capacity) {
        if (capacity <= 2) {
            throw new IllegalArgumentException("Capacity must be greater than 2");
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
        E tmp = getElement(head);
        itemArray[head] = null;
        head = (head + 1) % capacity;
        return tmp;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (size == capacity) {
            expandCapacity();
        }
        itemArray[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }

    private void expandCapacity() {
        int newCapacity = capacity + EXPANSION_FACTOR;
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = getElement(i);
        }
        capacity = newCapacity;
        itemArray = newArray;
        head = 0;
        tail = size;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return getElement(head);
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
        head = tail = size = 0;
        itemArray = new Object[capacity];
    }

    private E getElement(int index) {
        @SuppressWarnings("unchecked")
        E element = (E) itemArray[index];
        return element;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (!isEmpty()) {
            int index = head;
            builder.append(itemArray[index]);
            index = (index + 1) % capacity;
            int count = 1;
            while (index != tail && count < size) {
                builder.append(", ").append(itemArray[index]);
                index = (index + 1) % capacity;
                count++;
            }
        }
        builder.append("]");
        return builder.toString();
    }
}