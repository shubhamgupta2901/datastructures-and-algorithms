package playground;

import datastructures.BinaryHeap;

public class BinaryHeapMain {
    public static void main(String[] args) {
        BinaryHeapMain demo = new BinaryHeapMain();
        demo.testBinaryHeap();
    }

    private void testBinaryHeap(){
        BinaryHeap heap = new BinaryHeap();
        int[] values = new int[]{13,20,15,7,10,9,2,4,8,9,1};
        for (int value: values){
            System.out.println("Inserting "+ value+" : ");
            heap.insert(value);
            BinaryHeapPrinter.print(heap.getHeap());
        }

        System.out.println("--------------------");
        System.out.println("Extract mins:");
        while (heap.size()>0){
            System.out.println("Get min: " + heap.extractMin());
            BinaryHeapPrinter.print(heap.getHeap());
        }


    }
}
