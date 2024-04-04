package oy.tol.tra;

/**
 * An implementation of the StackInterface.
 * <p>
 * Note that you need to implement constructor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 * -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

    private Object[] itemArray;
    private int capacity;
    private int currentIndex = -1;
    private static final int DEFAULT_STACK_SIZE = 10;

    /**
     * Allocates a stack with a default capacity.
     * @throws StackAllocationException
     */
    public StackImplementation() throws StackAllocationException {
        this(DEFAULT_STACK_SIZE);
    }

    /**
     * Allocates a stack with the specified capacity.
     * @param capacity The capacity of the stack.
     * @throws StackAllocationException If cannot allocate room for the internal array.
     */
    public StackImplementation(int capacity) throws StackAllocationException {
        if (capacity < 2) {
            throw new StackAllocationException("The capacity is less than 2");
        }
        try {
            this.itemArray = new Object[capacity];
            this.capacity = capacity;
        } catch (Exception e) {
            throw new StackAllocationException("The programme failed to allocate space for the stack");
        }
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public void push(E element) throws StackAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("The element is null.");
        }
        if (currentIndex == capacity - 1) {
            resize();
        }
        itemArray[++currentIndex] = element;
    }

    private void resize() {
        Object[] newOne = new Object[capacity + 10];
        System.arraycopy(itemArray, 0, newOne, 0, capacity);
        this.capacity = capacity + 10;
        itemArray = newOne;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws StackIsEmptyException {
        if (currentIndex == -1) {
            throw new StackIsEmptyException("The stack is empty, you cannot pop any element");
        }
        E tmp = (E) itemArray[currentIndex];
        itemArray[currentIndex] = null;
        currentIndex--;
        return tmp;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws StackIsEmptyException {
        if (currentIndex == -1) {
            throw new StackIsEmptyException("The stack is empty, you cannot get the peek element");
        }
        return (E) itemArray[currentIndex];
    }

    @Override
    public int size() {
        return currentIndex + 1;
    }

    @Override
    public void clear() {
        currentIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (var index = 0; index <= currentIndex; index++) {
            builder.append(itemArray[index].toString());
            if (index < currentIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
