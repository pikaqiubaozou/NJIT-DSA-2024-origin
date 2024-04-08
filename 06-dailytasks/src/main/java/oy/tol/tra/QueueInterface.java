package oy.tol.tra;

/**
 * A generic interface for a queue class. Queues follow the first-in-first-out principle.
 * Students: Implement this interface in a separate <code>QueueImplementation.java</code> file.
 * No implementation in this file!!
 *
 * @param <E> The type of elements held in this queue
 */
public interface QueueInterface<E> {

   /**
    * Returns the current capacity of the queue.
    *
    * @return The number of elements the queue can currently hold.
    */
   int capacity();

   /**
    * Adds an element to the queue.
    *
    * @param element The element to add. Must not be null.
    * @throws QueueAllocationException If reallocation for the queue fails.
    * @throws NullPointerException    If the element is null.
    */
   void enqueue(E element) throws QueueAllocationException, NullPointerException;

   /**
    * Removes an element from the queue.
    *
    * @return The element removed from the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
   E dequeue() throws QueueIsEmptyException;

   /**
    * Returns the element at the head of the queue without removing it.
    *
    * @return The element at the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
   E element() throws QueueIsEmptyException;

   /**
    * Returns the count of elements currently in the queue.
    *
    * @return The number of elements in the queue.
    */
   int size();

   /**
    * Checks if the queue is empty.
    *
    * @return True if the queue is empty, false otherwise.
    */
   boolean isEmpty();

   /**
    * Resets the queue to an empty state, removing all elements.
    */
   void clear();

}
