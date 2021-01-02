package problem_solving.two_pointers;

/**
 * Given a sorted array of integers (not necessarily distinct) A and an integer B, find and return how many pair of
 * integers ( A[i], A[j] ) such that i != j have sum equal to B.
 * Since the number of such pairs can be very large, return number of such pairs modulo (109 + 7).
 *
 * Problem Constraints
 *  1 <= |A| <= 100000
 *  1 <= A[i] <= 10^9
 *  1 <= B <= 10^9
 *
 * Input Format:
 *  The first argument given is the integer array A.
 *  The second argument given is integer B.
 * Output Format:
 *  Return the number of pairs for which sum is equal to B modulo (10^9+7).
 *
 * Example:
 * A = [1, 1, 1]
 * B = 2
 * Output: 3
 *
 * A = [1, 1]
 * B = 2
 * Output: 1
 */
public class KSumII {
    public int solve(int[] A, int B) {
        int MOD = 1000000007;
        if(A == null || A.length == 0)
            return 0;
        long totalCount = 0;
        int left = 0, right = A.length-1;
        while(left<right){
            int sum = A[left] + A[right];
            if(sum < B)
                left++;
            else if(sum>B)
                right--;
            else {
                if(A[left] == A[right]){
                    int num = right-left+1;
                    long count = (num*(num-1))/2;
                    totalCount+=count;
                    break;
                }else {
                    int countLeft = 1, countRight = 1;
                    while(A[left] == A[left+1]){
                        countLeft++;
                        left++;
                    }
                    while(A[right]==A[right-1]){
                        countRight++;
                        right--;
                    }
                    totalCount+= countLeft*countRight;
                    left++;
                    right--;
                }
            }
        }
        return (int)(totalCount%MOD);
    }

    public static void main(String[] args) {
        int[] input = new int[]{ 2, 3, 3, 5, 7, 7, 8, 9, 9, 10, 10};
        KSumII driver = new KSumII();
        int output = driver.solve(input, 11);
        System.out.println(output);
    }
}
