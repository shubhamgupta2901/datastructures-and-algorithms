package problem_solving.hashing;

import java.util.*;

public class SortArrayInGivenOrder {
    static class Approach1{
        HashMap<Integer, Integer> orderMap;
        public int[] solve(int[] A, int[] B) {
            fillOrderMap(B);
            for(int i = 0; i< A.length; i++){
                int smallestIndex = i;
                for(int j = i+1; j<A.length; j++){
                    if(compare(A[j],A[smallestIndex])<0){
                        smallestIndex = j;
                    }
                }
                if(i!=smallestIndex)
                    swap(A, i, smallestIndex);
            }
            return A;
        }

        private void swap(int[] arr, int i , int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private int compare(int x, int y){
            int posX = orderMap.getOrDefault(x, Integer.MAX_VALUE);
            int posY = orderMap.getOrDefault(y, Integer.MAX_VALUE);
            return posX==posY ? x-y : posX-posY;
        }

        private void fillOrderMap(int[] B){
            orderMap = new HashMap<Integer, Integer>();
            for(int i = 0; i<B.length; i++){
                orderMap.put(B[i], i);
            }
        }
    }

    public static class Approach2{
        HashMap<Integer, Integer> orderMap;
        public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
            fillOrderMap(B);
            Collections.sort(A, new CustomComparator());
            return A;
        }

        public class CustomComparator implements Comparator<Integer>{
            @Override
            public int compare(Integer x, Integer y) {
                int posX = orderMap.getOrDefault(x, Integer.MAX_VALUE);
                int posY = orderMap.getOrDefault(y, Integer.MAX_VALUE);
                if (posX==posY){
                    return Integer.compare(x,y);
                }
                return  posX -posY;
            }
        }

        private void fillOrderMap(ArrayList<Integer> B){
            orderMap = new HashMap<>();
            for(int i = 0; i<B.size(); i++){
                orderMap.put(B.get(i), i);
            }
        }
    }

    public static void main(String[] args) {
        int[]A = new int[]{20, 14, 8, 18, 6};
        int[]B = new int[]{ 1, 16, 7, 6, 17, 3, 13, 8, 19, 20};
        SortArrayInGivenOrder driver = new SortArrayInGivenOrder();
        Approach1 approach1 = new Approach1();
        Approach2 approach2 = new Approach2();
        int[] output = approach1.solve(A, B);
        for(int a: output)
            System.out.print(a+" ");
    }
}
