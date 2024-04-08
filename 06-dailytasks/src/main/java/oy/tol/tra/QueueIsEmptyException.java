package oy.tol.tra;

/**
 * Exception thrown when attempting to perform an operation on an empty queue.
 */
public class QueueIsEmptyException extends RuntimeException {

    /**
     * Constructs a new QueueIsEmptyException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public QueueIsEmptyException(String message) {
        super(message);
    }
}