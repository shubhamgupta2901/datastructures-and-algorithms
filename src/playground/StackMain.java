package playground;

import ds.Stack;
import ds.StackWithDLL;
import ds.StackWithLL;

public class StackMain {

    public static void main(String[] args) {
        StackMain demo = new StackMain();
        demo.testStack();
        System.out.println("-----------");
        demo.testStackWithDLL();
        System.out.println("-----------");
        demo.testStackWithLL();
    }

    public void testStack(){
        Stack stack = new Stack();
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        for(int i =0;i<10;i++){
            stack.push(i);
        }
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        //System.out.println(stack.pop());
        System.out.println(stack.push(10));
    }

    public void testStackWithDLL(){
        StackWithDLL stack = new StackWithDLL();
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        for(int i =0;i<10;i++){
            stack.push(i);
        }
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        //System.out.println(stack.pop());
        System.out.println(stack.push(10));
    }

    public void testStackWithLL(){
        StackWithLL stack = new StackWithLL();
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        for(int i =0;i<10;i++){
            stack.push(i);
        }
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        //System.out.println(stack.pop());
        System.out.println(stack.push(10));
    }
}
