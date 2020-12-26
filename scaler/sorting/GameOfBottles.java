package scaler.sorting;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers A of size N which denotes N cylindrical empty bottles. The radius of the ith bottle is A[i].
 *
 * You can put the ith bottle into the jth bottle if the following conditions are met:
 * ith bottle is not put into another bottle.
 * jth bottle dosen't contain any other bottle.
 * The radius of bottle i is smaller than bottle j (A[i] < A[j]).
 * You can put bottles into each other any number of times.
 * You want to MINIMIZE the number of visible bottles. A bottle is called visible if it is not put into any other bottle.
 *
 * Find and return the minimum number of visible bottles.
 *
 * Problem Constraints
 * 1 <= N <= 100000
 * 1<= A[i] <= 100000000
 *
 * Input Format: First argument is an integer array A denoting the radius of the cyclindrical bottles.
 * Output Format: Return a single integer corresponding to the minimum number of visible bottles.
 *
 *Example
 * Input 1: A = [1, 2, 3]
 * Output 1: 1
 *
 * Input 2: [1, 1]
 * Output 2: 2
 */

public class GameOfBottles {

    public int approach1(int[] A) {
        int maxFrequency = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<A.length; i++){
            int frequency = map.getOrDefault(A[i],0);
            frequency = frequency+1;
            map.put(A[i], frequency);
            if(frequency>maxFrequency)
                maxFrequency = frequency;
        }
        return maxFrequency;
    }

    public int approach2(int[] A) {
        int maxFrequency = 1;
        Arrays.sort(A);
        int frequency = 1;
        for(int i=1; i<A.length; i++){
            if(A[i] == A[i-1]){
                frequency++;
                if(frequency>maxFrequency)
                    maxFrequency = frequency;
            }else frequency = 1;
        }
        return maxFrequency;
    }

    public static void main(String[] args) {
        GameOfBottles driver = new GameOfBottles();
        int[] input = new int[]{8, 15, 1, 10, 5, 19, 19, 3, 5, 6, 6, 2, 8, 2, 12, 16, 3, 8, 17, 12, 5, 3, 14, 13, 3, 2, 17, 19, 16, 8, 7, 12, 19, 10, 13, 8, 20, 16, 15, 4, 12, 3};
        int output = driver.approach2(input);
        System.out.println(output);
    }
}
