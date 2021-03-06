package playground;


import datastructures.CircularQueue;
import datastructures.LinearQueue;
import datastructures.Queue;

public class QueueMain {
    public static void main(String[] args) {
        QueueMain demo = new QueueMain();
//        demo.testLinearQueue();
//        System.out.println("----------");
//        demo.testCircularQueue();
//        System.out.println("----------");
        demo.testQueueLL();
    }

    public void testLinearQueue(){
        LinearQueue queue = new LinearQueue();
        System.out.println("isEmpty "+queue.isEmpty());
        System.out.println("isFull "+ queue.isFull());
        System.out.println("dequeue: "+ queue.dequeue());
        System.out.println("enqueue 10: "+ queue.enqueue(10));
        System.out.println("enqueue 20: "+ queue.enqueue(20));
        System.out.println("dequeue: "+ queue.dequeue());
        System.out.println("dequeue: "+ queue.dequeue());
        System.out.println("dequeue: "+ queue.dequeue());
        System.out.println("isEmpty "+queue.isEmpty());

    }

    public void testCircularQueue(){
        CircularQueue queue = new CircularQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(70);
    }

    public void testQueueLL(){
        Queue queue = new Queue();
        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.remove();
        queue.remove();
        queue.remove();
        queue.add(40);
        int head = queue.peek();
        queue.add(50);
        head = queue.peek();
        queue.add(60);
        head = queue.peek();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.add(70);

    }
}
