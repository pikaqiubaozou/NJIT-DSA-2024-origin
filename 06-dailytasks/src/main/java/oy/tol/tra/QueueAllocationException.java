package oy.tol.tra;

/**
 * Exception thrown when allocation in the queue fails.
 */
public class QueueAllocationException extends RuntimeException {

   /**
    * Constructs a new QueueAllocationException with the specified detail message.
    *
    * @param message the detail message (which is saved for later retrieval by the getMessage() method).
    */
   public QueueAllocationException(String message) {
      super(message);
   }
}