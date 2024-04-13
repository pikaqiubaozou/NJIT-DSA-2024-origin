package oy.tol.tra;

/**
 * Exception thrown when attempting to access or dequeue from an empty queue.
 */
public class QueueIsEmptyException extends RuntimeException {
    /**
     * Constructs a new QueueIsEmptyException with the specified detail message.
     *
     * @param message the detail message.
     */
    public QueueIsEmptyException(String message) {
        super(message);
    }
}