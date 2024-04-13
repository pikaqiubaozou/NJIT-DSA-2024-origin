package oy.tol.tra;
public class QueueImplementation<E> implements QueueInterface<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] itemArray;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private int capacity;

    @SuppressWarnings("unchecked")
    public QueueImplementation(int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        this.itemArray = (E[]) new Object[this.capacity];
    }

    public QueueImplementation() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (size == capacity) {
            resize(capacity * 2);
        }
        itemArray[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }

    private void resize(int newCapacity) throws QueueAllocationException {
        try {
            E[] newArray = (E[]) new Object[newCapacity];
            if (head < tail) {
                System.arraycopy(itemArray, head, newArray, 0, size);
            } else {
                System.arraycopy(itemArray, head, newArray, 0, capacity - head);
                System.arraycopy(itemArray, 0, newArray, capacity - head, tail);
            }
            itemArray = newArray;
            head = 0;
            tail = size;
            capacity = newCapacity;
        } catch (OutOfMemoryError e) {
            throw new QueueAllocationException("Failed to allocate more room for the queue.");
        }
    }

    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty, can't dequeue.");
        }
        E element = itemArray[head];
        itemArray[head] = null;
        head = (head + 1) % capacity;
        size--;
        return element;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("There's no data in the queue");
        }
        return itemArray[head];
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
        for (int i = 0; i < size; i++) {
            itemArray[(head + i) % capacity] = null;
        }
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(itemArray[(head + i) % capacity]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}