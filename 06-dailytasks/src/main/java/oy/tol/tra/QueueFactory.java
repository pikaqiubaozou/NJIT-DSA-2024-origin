package oy.tol.tra;

/**
 * This class provides methods to instantiate queues implementing the QueueInterface.
 *
 * @author Antti Juustila
 */
public class QueueFactory {

    private QueueFactory() {
        // Private constructor to prevent instantiation of this utility class.
    }

    /**
     * Creates an instance of QueueInterface for Integer type with the specified capacity.
     *
     * @param capacity Number of elements the queue can hold.
     * @return The queue object.
     */
    public static QueueInterface<Integer> createIntegerQueue(int capacity) {
        return new QueueImplementation<>(capacity);
    }

    /**
     * Creates an instance of QueueInterface for String type with the specified capacity.
     *
     * @param capacity Number of elements the queue can hold.
     * @return The queue object.
     */
    public static QueueInterface<String> createStringQueue(int capacity) {
        return new QueueImplementation<>(capacity);
    }

    /**
     * Creates an instance of QueueInterface for Integer type using the default constructor.
     * The default constructor creates a queue with default capacity.
     *
     * @return The queue object.
     */
    public static QueueInterface<Integer> createIntegerQueue() {
        return new QueueImplementation<>();
    }

    /**
     * Creates an instance of QueueInterface for String type using the default constructor.
     * The default constructor creates a queue with default capacity.
     *
     * @return The queue object.
     */
    public static QueueInterface<String> createStringQueue() {
        return new QueueImplementation<>();
    }
}