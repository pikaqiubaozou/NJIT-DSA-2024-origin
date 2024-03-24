package oy.tol.tra;

public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   public StackImplementation() throws StackAllocationException {
      this(DEFAULT_STACK_SIZE);
   }

   public StackImplementation(int capacity) throws StackAllocationException {
      if (capacity < 2) {
         throw new StackAllocationException("Stack size must be at least 2");
      }
      try {
         this.itemArray = new Object[capacity];
         this.capacity = capacity;
      } catch (OutOfMemoryError e) {
         throw new StackAllocationException("Cannot allocate room for the internal array");
      }
   }

   @Override
   public int capacity() {
      return this.capacity;
   }

   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this
      if (element == null) {
         throw new NullPointerException("Cannot push null element onto the stack.");
      }
      if (currentIndex + 1 >= capacity){
         int newCapacity = capacity * 2;
         Object[] newArray;
         try{
            newArray = new Object[newCapacity];
            for (int i = 0; i < itemArray.length; i++){
               newArray[i] = itemArray[i];
            }
            itemArray = newArray;
            capacity = newCapacity;
         }catch (Exception e){
            throw new StackAllocationException("Cannot allocate room for the internal array");
         }
      }
      itemArray[++currentIndex] = element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("Stack is empty");
      }
      return (E) itemArray[currentIndex--];
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("Stack is empty");
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
