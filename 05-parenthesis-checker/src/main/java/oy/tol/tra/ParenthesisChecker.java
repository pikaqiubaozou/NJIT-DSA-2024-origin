package oy.tol.tra;
public class ParenthesisChecker {

    private ParenthesisChecker() {
    }

    /**
     * Checks if the given string has matching opening and closing parentheses.
     *
     * @param stack      The stack object used in checking the parentheses from the string.
     * @param fromString A string containing parentheses to check if it is valid.
     * @return Returns the number of parentheses found from the input in total (both opening and closing).
     * @throws ParenthesesException     if the parentheses did not match as intended.
     * @throws StackAllocationException If the stack cannot be allocated or reallocated if necessary.
     */
    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
        int count = 0;
        for (int i = 0; i < fromString.length(); i++) {
            char ch = fromString.charAt(i);
            switch (ch) {
                case '(': case '[': case '{':
                    try {
                        stack.push(ch);
                    } catch (Exception e) {
                        throw new ParenthesesException("Stack operation failed", ParenthesesException.STACK_FAILURE);
                    }
                    count++;
                    break;
                case ')': case ']': case '}':
                    try {
                        Character topChar = stack.pop();
                        if ((ch == ')' && topChar != '(') || (ch == ']' && topChar != '[') || (ch == '}' && topChar != '{')) {
                            throw new ParenthesesException("Parentheses are in the wrong order", ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                        }
                    } catch (Exception e) {
                        throw new ParenthesesException("Too many closing parentheses", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
                    }
                    count++;
                    break;
                default:
                    // Ignore characters other than parentheses
                    break;
            }
        }

        if (!stack.isEmpty()) {
            throw new ParenthesesException("Too few closing parentheses", ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
        }

        return count;
    }
}