package playground;



import datastructures.ArrayStack;
import datastructures.StackWithDLL;
import datastructures.StackWithLL;

public class StackMain {

    public static void main(String[] args) {
        StackMain demo = new StackMain();
        demo.testStack();
        System.out.println("-----------");
//        demo.testStackWithDLL();
//        System.out.println("-----------");
//        demo.testStackWithLL();
    }

    public void testStack(){
        ArrayStack stack = new ArrayStack();
        System.out.println(stack.empty());
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());
        for(int i =1;i<=11;i++){
            stack.push(i*100);
        }

//        for(int i = 1;i<=11; i++)
//            stack.pop();

        System.out.println(stack.empty());
//        System.out.println(stack.peek());
        System.out.println(stack.pop());
//        System.out.println(stack.push(10));
        int dist = stack.search(100);
        System.out.println(dist);
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
