package scaler;

public class MaximumHeightOfStaircase {

    /**
     * Approach 1: Time Complexity: O(logA)
     * Sum of natural numbers upto n is given by (n(n+1))/2
     * So I just have to find largest n such that (n(n+1))/2 is less or equal to A.
     * This n could be estimated using binary search
     * So doing binary search for n is the best approach for the problem.
     */
    public int approach1(int A) {
        if(A == 0)
            return 0;
        int start = 1, end = A;
        int candidate = -1;
        while(start<=end){
            int mid = (start+end)/2;
            long requiredBlocks = requiredBlocks(mid);
            if(requiredBlocks == A)
                return mid;
            if(requiredBlocks<A){
                candidate = mid;
                start = mid+1;
            }else end = mid-1;
        }
        return candidate;
    }


    private long requiredBlocks(int num){
        return (num*(num+1))/2;
    }

    /**
     * Approach 1: Same as approach 1 , except requiredBlock method overflows for large number and doesn't fit in long either
     * So I add the sum manually. This does seem to increase the overhead.
     * Time Complexity: O(logA)*A??
     */
    public int approach2(int A) {
        if(A == 0)
            return 0;
        int start = 1, end = A;
        int candidate = -1;
        while(start<=end){
            int mid = (start+end)/2;
            int isCandidate = isCandidate(mid, A);
            if(isCandidate == 0)
                return mid;
            if(isCandidate<0){
                candidate = mid;
                start = mid+1;
            }else end = mid-1;
        }
        return candidate;
    }


    private int isCandidate(int num, int target){
        int sum = 0;
        for(int i =1; i<=num; i++){
            sum+=i;
            if(sum>target)
                return 1;
        }
        if(sum == target)
            return 0;
        return -1;
    }



    public static void main(String[] args) {
        MaximumHeightOfStaircase driver = new MaximumHeightOfStaircase();
        int val = driver.approach2(92761);
        System.out.println(val);
    }
}
