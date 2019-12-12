import ds.Stack;
import ds.StackWithLinkedList;

public class StackMain {

    public static void main(String[] args) {
        StackMain demo = new StackMain();
        demo.testStack();
        demo.testStackWithLinkedList();
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

    public void testStackWithLinkedList(){
        StackWithLinkedList stack = new StackWithLinkedList();
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
