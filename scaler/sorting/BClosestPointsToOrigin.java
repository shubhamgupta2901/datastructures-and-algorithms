package scaler.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 *
 * We have a list A, of points(x,y) on the plane. Find the B closest points to the origin (0, 0).
 * Here, the distance between two points on a plane is the Euclidean distance.
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.)
 * NOTE: Euclidean distance between two points P1(x1,y1) and P2(x2,y2) is sqrt( (x1-x2)2 + (y1-y2)2 ).
 *
 * Problem Constraints
 * 1 <= B <= length of the list A <= 100000
 * -100000 <= A[i][0] <= 100000
 * -100000 <= A[i][1] <= 100000
 *
 * Input: The argument given is list A and an integer B.
 * Output: Return the B closest points to the origin (0, 0) in any order.
 *
 * Example Input
 * Input:
 * A = [ [1, 3],[-2, 2]]
 * B = 1
 * Output: [[-2,2]]
 *
 */
public class BClosestPointsToOrigin {

    /**
     * Time Complexity: O(NlonN)
     * Space Complexity: O(N) for priority queue
     */
    class Approach1{
        class Point implements Comparable<Point>{
            int x;
            int y;

            Point(int[] arr){
                this(arr[0], arr[1]);
            }

            Point(int x, int y){
                this.x = x;
                this.y = y;
            }

            public int compareTo(Point p){
                return (int) (distance(this) - distance(p));
            }

            public int[] toArray(){
                int[] pointArr = new int[2];
                pointArr[0] = this.x;
                pointArr[1] = this.y;
                return pointArr;
            }

        }

        private double distance(Point p){
            return Math.sqrt((p.x*p.x)+(p.y*p.y));
        }

        public int[][] solve(int[][] A, int B) {
            PriorityQueue<Point> heap = new PriorityQueue<>();
            for(int i = 0; i<A.length; i++)
                heap.add(new Point(A[i]));
            int[][] output = new int[B][2];
            for(int i = 0; i<B; i++){
                output[i] = heap.poll().toArray();
            }
            return output;
        }
    }

    /**
     * Time Complexity: O(B*N)
     * Space Complexity: O(B) for output
     */
    class  Approach2 {
        public int[][] solve(int[][] A, int B) {
            int[][] output = new int[B][2];
            for(int i = 0; i<B; i++){
                int smallestIndex = i;
                for(int j = i+1;j<A.length; j++){
                    if(compareTo(A[j],A[smallestIndex])<0)
                        smallestIndex = j;
                }
                swap(A, i, smallestIndex);
                output[i] = A[i];
            }
            return output;
        }

        private double distance(int[] p){
            return Math.sqrt((p[0]*p[0])+(p[1]*p[1]));
        }

        private int compareTo(int[] pointA, int[] pointB){
            return (int)(distance(pointA)-distance(pointB));
        }

        private void swap(int[][] arr, int i, int j){
            int[] temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * Time Complexity: O(NlogN)
     * Space Complexity: O(B) for output
     */
    class Approach3 {
        public int[][] solve(int[][] A, int B) {
            int[][] output = new int[B][2];
            Arrays.sort(A,new ArrComparator());
            for(int i = 0; i<B; i++)
                output[i] = A[i];
            return output;
        }

        private double distance(int[] p){
            return Math.sqrt((p[0]*p[0])+(p[1]*p[1]));
        }


        private class ArrComparator implements Comparator<int[]>{
            @Override
            public int compare(int[] pointA, int[] pointB) {
                return (int)(distance(pointA)-distance(pointB));
            }
        }
    }

    /**
     * Time Complexity: O(NlogB)
     * Space Complexity: O(B) for output and priority queue
     */
    class Approach4 {
        public int[][] solve(int[][] A, int B) {
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>(B,new ReverseArrComparator());
            int[][] output = new int[B][2];
            for(int i = 0; i<A.length; i++){
                if(i<B){
                    maxHeap.add(A[i]);
                    continue;
                }
                if(distance(A[i])<distance(maxHeap.peek())){
                    maxHeap.poll();
                    maxHeap.add(A[i]);
                }
            }
            for(int i = 0; i<B; i++)
                output[i] = maxHeap.poll();
            return output;
        }

        private double distance(int[] p){
            return Math.sqrt((p[0]*p[0])+(p[1]*p[1]));
        }

        private class ReverseArrComparator implements Comparator<int[]>{
            @Override
            public int compare(int[] pointA, int[] pointB) {
                return (int)(distance(pointB)-distance(pointA));
            }
        }
    }


}
