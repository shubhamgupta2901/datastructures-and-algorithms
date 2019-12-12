package playground;

import ds.Queue;

public class QueueMain {
    public static void main(String[] args) {
        QueueMain demo = new QueueMain();
        demo.testQueue();
    }

    public void testQueue(){
        Queue queue = new Queue();
        System.out.println("isEmpty "+queue.isEmpty());
        System.out.println("isFull "+ queue.isFull());
        System.out.println("dequeue: "+ queue.dequeue());
        queue.enqueue(10);
        queue.enqueue(20);
        System.out.println("dequeue: "+ queue.dequeue());
        System.out.println("dequeue: "+ queue.dequeue());
        System.out.println("dequeue: "+ queue.dequeue());
        System.out.println("isEmpty "+queue.isEmpty());

    }
}
