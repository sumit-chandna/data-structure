package org.skcorg.datastructure.custom;

public class StackOperations {
	public String infixToPostfix(String data) {
		final StringBuilder builder = new StringBuilder();
		final Stack stack = new Stack();
		for (Character _tempChar : data.toCharArray()) {
			if (Character.isAlphabetic(_tempChar)) {
				builder.append(_tempChar);
			} // If the scanned character is an ‘(‘, push it to the stack.
			else if (_tempChar == '(')
				stack.push(_tempChar);

			// If the scanned character is an ‘)’, pop and output from the stack
			// until an ‘(‘ is encountered.
			else if (_tempChar == ')') {
				while (!stack.isEmpty() && stack.peek() != '(')
					builder.append(stack.pop());
				if (!stack.isEmpty() && stack.peek() != '(')
					return null; // invalid expression
				else
					stack.pop();
			} else // an operator is encountered
			{
				while (!stack.isEmpty()
						&& Prec(_tempChar) <= Prec(stack.peek()))
					builder.append(stack.pop());
				stack.push(_tempChar);
			}
		}
		return builder.toString();
	}

	// A utility function to return precedence of a given operator
	// Higher returned value means higher precedence
	private int Prec(Character ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;

		case '*':
		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}

}
