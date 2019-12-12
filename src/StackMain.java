import ds.Stack;

public class StackMain {

    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        for(int i =0;i<10;i++){
            stack.push(i);
        }
        System.out.println(stack.isFull());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        //System.out.println(stack.pop());
        System.out.println(stack.push(10));
    }
}
