package leetcode;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/min-stack/"/>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
class MinStack {

    Stack<Integer> stack, auxStack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        auxStack = new Stack<>();
    }

    public void push(int x) {
        if(stack.isEmpty()){
            stack.push(x);
            auxStack.push(x);
        }
        else {
            stack.push(x);
            auxStack.push(x < auxStack.peek() ? x : auxStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        auxStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return auxStack.peek();
    }
}


