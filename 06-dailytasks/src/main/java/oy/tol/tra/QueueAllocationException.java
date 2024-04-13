package oy.tol.tra;

import java.time.LocalDateTime;

/**
 * Exception thrown when allocation in the queue fails.
 */
public class QueueAllocationException extends RuntimeException {
   private LocalDateTime timestamp;

   /**
    * Instantiate with a message.
    * @param message The explanation for the exception.
    */
   public QueueAllocationException(String message) {
      super(message);
      this.timestamp = LocalDateTime.now();
   }

   /**
    * Get the timestamp when the exception occurred.
    * @return The timestamp.
    */
   public LocalDateTime getTimestamp() {
      return timestamp;
   }
}