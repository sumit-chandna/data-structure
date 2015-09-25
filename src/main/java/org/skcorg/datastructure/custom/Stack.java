package org.skcorg.datastructure.custom;

/*
 * Stack is a linear data structure which follows a particular order in which the operations are performed. The order may be LIFO(Last In First Out) or FILO(First In Last Out).
 * Mainly the following three basic operations are performed in the stack:
 **Push: Adds an item in the stack. If the stack is full, then it is said to be an Overflow condition.
 **Pop: Removes an item from the stack. The items are popped in the reversed order in which they are pushed. If the stack is empty, then it is said to be an Underflow condition.
 **Peek: Get the topmost item.
 */
public class Stack {
	private Character[] stack;
	private final int MAX_SIZE = 15;
	private int top = -1;

	public Stack() {
		super();
		stack = new Character[MAX_SIZE];
	}

	public boolean isFull() {
		if (top == (MAX_SIZE - 1)) {
			return true;
		}
		return false;
	}

	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		return false;
	}

	public void push(Character data) {
		if (!isFull()) {
			stack[++top] = data;
		}
	}

	public Character pop() {
		if (!isEmpty()) {
			return stack[top--];
		}
		return null;
	}

	public Character peek() {
		if (!isEmpty()) {
			return stack[top];
		}
		return null;
	}

	
}
