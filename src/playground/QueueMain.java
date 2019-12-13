package playground;

import ds.QueueArr;
import ds.QueueArrInefficient;

public class QueueMain {
    public static void main(String[] args) {
        QueueMain demo = new QueueMain();
        demo.testQueueArrInefficient();
        System.out.println("----------");
        demo.testQueueArr();
    }

    public void testQueueArrInefficient(){
        QueueArrInefficient queue = new QueueArrInefficient();
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

    public void testQueueArr(){
        QueueArr queue = new QueueArr();
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
}
