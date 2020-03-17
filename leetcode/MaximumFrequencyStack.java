package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/maximum-frequency-stack/"/>
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 * FreqStack has two functions:
 * 1. push(int x), which pushes an integer x onto the stack.
 * 2. pop(), which removes and returns the most frequent element in the stack.
 *  If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 *
 * Example 1:
 * Input:
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 *
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 *
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 *
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 *
 * pop() -> returns 4.
 * The stack becomes [5,7].
 *
 *
 * Note:
 * Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 * It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 * The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 */
public class MaximumFrequencyStack {

    /**
     * Approach 1: Accepted. Run time very slow.
     * 1) Create a Max Heap and a HashMap.
     * 2) the compare method will order the number according to the frequency of an element, if frequency is same
     *  the latest added element will come first, to do this we use a variable insertionOrder, which is initialised with 0.
     *  every time push() is called, increment the insertionOrder.
     * 3) The HashMap will store the number as key and its indexInHeap, frequency and list of insertionOrders as a pair.
     * 4) When a number is added to heap, if it does not exists in heap,add it to heap or if it does exist
     * find its index in heap from map, increase its frequency, add a new insertion order to list insertion
     * and now perform heapifyUp(index) call in heap.
     * 5) Similary for pop(), if the top element has frequency >1, reduce its frequency, delete its last insertion,
     * and heapifyDown(), if its frequency is 1, copy last element of heap to top of heap, delete the last element and
     * perform heapifyDown().
     * 6) One thing to keep in mind is we also update the index of numbers in hashmap, when we perform heapify methods and
     * swap values.
     */
    static class FreqStack1 {
        class Info{
            int frequency;
            int indexInHeap;
            List<Integer> insertions;

            Info(int frequency,int indexInHeap, int insertionOrder){
                this.frequency = frequency;
                this.indexInHeap = indexInHeap;
                this.insertions = new ArrayList<>();
                this.insertions.add(insertionOrder);
            }
        }

        private List<Integer> heap;
        private HashMap<Integer, Info> hashMap;
        private int insertionOrder;

        public FreqStack1() {
            heap = new ArrayList<Integer>();
            hashMap = new HashMap<Integer, Info>();
            insertionOrder = 0;
        }

        public void push(int x) {
            insertionOrder++;
            Info info = hashMap.getOrDefault(x,null);
            if(info == null){
                int index = heap.size();
                heap.add(index, x);
                hashMap.put(x,new Info(1,index,insertionOrder));
                heapifyUp(index);
            }else{
                info.frequency = info.frequency+1;
                info.insertions.add(insertionOrder);
                heapifyUp(info.indexInHeap);
            }
        }

        public int pop() {
            int num = heap.get(0);
            Info info = hashMap.get(num);
            if(info.frequency>1){
                //element does not need to be removed, just decrement frequency and heapifyDown
                info.frequency = info.frequency-1;
                info.insertions.remove(info.insertions.size()-1);
                heapifyDown();
            }else{
                //need to delete the number from heap and from hashmap
                //for balancing the heap, the last number needs to be copied at top index(also update hashmap),
                //last index needs to be removed, and then heapifyDown the last element.
                heap.set(0, heap.get(heap.size()-1));
                hashMap.get(heap.get(0)).indexInHeap = 0;
                heap.remove(heap.size()-1);
                hashMap.remove(num);
                heapifyDown();
            }
            return num;
        }


        private void heapifyUp(int index){
            int parentIndex;
            while(index>0){
                parentIndex = parentIndex(index);
                if(compare(heap.get(parentIndex),heap.get(index)) < 0)
                    return;
                swapAndUpdateMap(index, parentIndex);
                index = parentIndex;
            }
        }

        private void heapifyDown(){
            int index = 0;
            int leftChildIndex = leftChildIndex(index);
            while(leftChildIndex<heap.size()){
                int largerIndex = compare(heap.get(leftChildIndex),heap.get(index)) < 0 ? leftChildIndex : index;
                int rightChildIndex = rightChildIndex(index);
                if(rightChildIndex< heap.size() && compare(heap.get(rightChildIndex),heap.get(largerIndex))<0)
                    largerIndex = rightChildIndex;
                if(largerIndex == index)
                    return;
                swapAndUpdateMap(index, largerIndex);
                index = largerIndex;
                leftChildIndex = leftChildIndex(index);
            }
        }

        // Retruns -ve number if val1 comes first in heap. -> val1 is bigger
        // Returns +ve number if val2 comes first in heap -> val2 is bigger
        private int compare( int val1, int val2){
            Info info1 = hashMap.get(val1);
            Info info2 = hashMap.get(val2);

            int frequencyDiff = info2.frequency - info1.frequency;
            if(frequencyDiff == 0)
                return info2.insertions.get(info2.insertions.size()-1) - info1.insertions.get(info1.insertions.size()-1);
            return frequencyDiff;
        }

        private void swapAndUpdateMap(int index1, int index2){
            Info info1 = hashMap.get(heap.get(index1));
            Info info2 = hashMap.get(heap.get(index2));

            Integer temp = heap.get(index1);
            heap.set(index1, heap.get(index2));
            heap.set(index2, temp);

            info1.indexInHeap = index2;
            info2.indexInHeap = index1;
        }

        private int parentIndex(int index){
            return (index-1)/2;
        }

        private int leftChildIndex(int index){
            return 2*index+1;
        }

        private int rightChildIndex(int index){
            return 2*index+2;
        }
    }

    public static void main(String[] args) {
        FreqStack1 stack = new MaximumFrequencyStack.FreqStack1();
        stack.push(4);
        stack.push(0);
        stack.push(9);
        stack.push(3);
        stack.push(4);
        stack.push(2);
        System.out.println(stack.pop());
        stack.push(6);
        System.out.println(stack.pop());
        stack.push(1);
        System.out.println(stack.pop());
        stack.push(1);
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
