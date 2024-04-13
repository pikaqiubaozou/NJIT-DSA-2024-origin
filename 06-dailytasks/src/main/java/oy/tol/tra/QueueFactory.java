package oy.tol.tra;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class instantiates a queue implementing the {@code QueueInterface}.
 *
 * @author Antti Juustila
 */
public class QueueFactory {
    private static final Logger LOGGER = Logger.getLogger(QueueFactory.class.getName());

    private QueueFactory() {
    }

    /**
     * Creates an instance of QueueInterface for Integer type.
     * @param capacity Number of elements the queue can hold.
     * @return The queue object.
     */
    public static QueueInterface<Integer> createIntegerQueue(int capacity) {
        LOGGER.log(Level.INFO, "Creating Integer queue with capacity: {0}", capacity);
        return new QueueImplementation<>(capacity);
    }

    // Add similar methods for other types of queues...

}