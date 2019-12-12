package ds;

import ds.interfaces.IQueue;

public class Queue implements IQueue {
    private int[] arr;
    private int front , rear;
    private int capacity;

    public Queue() {
        this.capacity = 10;
        this.arr = new int[this.capacity];
        for(int i =0; i< this.capacity; i++)
            this.arr[i] = Integer.MIN_VALUE;
        this.front = this.rear = -1;
    }

    public Queue(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        for(int i =0; i< capacity; i++)
            this.arr[i] = Integer.MIN_VALUE;
        this.front = this.rear = -1;
    }

    @Override
    public boolean enqueue(int value) {
        if(isFull())
            return false;
        if(isEmpty())
            front++;
        arr[++rear] = value;
        return true;
    }

    @Override
    public int dequeue() {
        if(isEmpty())
            return Integer.MIN_VALUE;
        return arr[front++];
    }

    @Override
    public int peek() {
        if(isEmpty())
            return Integer.MIN_VALUE;
        return arr[front];
    }

    @Override
    public boolean isEmpty() {
        if((front ==-1 && rear == -1) || front > rear)
            return true;
        return false;
    }

    public boolean isFull(){
        return (rear == capacity-1);
    }
}
