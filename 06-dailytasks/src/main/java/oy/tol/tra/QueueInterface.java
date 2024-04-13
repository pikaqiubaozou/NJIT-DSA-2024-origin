package oy.tol.tra;

/**
 * A generic interface for a queue class. Queues work following
 * the first-in-first-out principle.
 * Students: Implement this interface in a separate <code>QueueImplementation.java</code> file.
 *           No implementation in this file!!
 */
public interface QueueInterface<E> {

   /**
    * Retrieves the current capacity of the queue.
    * @return The maximum number of elements the queue can currently hold.
    */
   public int capacity();

   /**
    * Adds an element to the queue.
    * @param element The element to add, must not be null.
    * @throws QueueAllocationException If reallocation of the queue failed (in case queue needs reallocation).
    * @throws NullPointerException If the element is null.
    */
   public void enqueue(E element) throws QueueAllocationException, NullPointerException;

   /**
    * Removes and returns the element at the head of the queue.
    * @return The element at the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
   public E dequeue() throws QueueIsEmptyException;

   /**
    * Returns the element at the head of the queue without removing it.
    * @return The element at the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
   public E element() throws QueueIsEmptyException;

   /**
    * Returns the number of elements currently in the queue.
    * @return The number of elements in the queue.
    */
   public int size();

   /**
    * Checks if the queue is empty.
    * @return True if the queue is empty, false otherwise.
    */
   public boolean isEmpty();

   /**
    * Clears the queue, removing all elements.
    */
   public void clear();

}
