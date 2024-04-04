package oy.tol.tra;

/**
 * This class instantiates different types of stacks implementing the {@code StackInterface} interface.
 * <p>
 * TODO: Students, implement the createCharacterStack method for instantiating {@code StackImplementation<Character>}
 * objects in this task.
 *
 * @author Antti Juustila
 * @version 1.0
 */
public class StackFactory {

    private StackFactory() {
        // Private constructor to prevent instantiation of this utility class.
    }

    /**
     * Creates an instance of StackImplementation for Integer type with default capacity.
     *
     * @return The stack object.
     */
    public static StackInterface<Integer> createIntegerStack() {
        return createStack();
    }

    /**
     * Creates an instance of StackImplementation for Integer type with specified capacity.
     *
     * @param capacity Number of elements the stack can hold.
     * @return The stack object.
     */
    public static StackInterface<Integer> createIntegerStack(int capacity) {
        return createStack(capacity);
    }

    /**
     * Instantiates a stack of Characters using the stack constructor with default capacity.
     *
     * @return The stack implementation holding Characters.
     */
    public static StackInterface<Character> createCharacterStack() {
        return createStack();
    }

    /**
     * Instantiates a stack of Characters using the stack constructor with specified capacity.
     *
     * @param capacity Number of elements the stack can hold.
     * @return The stack implementation holding Characters.
     */
    public static StackInterface<Character> createCharacterStack(int capacity) {
        return createStack(capacity);
    }

    // Private method to instantiate stack
    private static <T> StackInterface<T> createStack() {
        return new StackImplementation<>();
    }

    // Private method to instantiate stack with specified capacity
    private static <T> StackInterface<T> createStack(int capacity) {
        return new StackImplementation<>(capacity);
    }
}