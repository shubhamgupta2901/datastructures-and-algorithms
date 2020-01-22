package leetcode;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
 * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), for N > 1.
 * Given N, calculate F(N).
 *
 * Example 1:
 * Input: 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 *
 * Example 2:
 * Input: 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 *
 * Example 3:
 * Input: 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 * Note: 0 ≤ N ≤ 30.
 */
public class FibonacciNumber {

    /**
     * Approach 1: Recursive solution
     */
    public int fib1(int N) {
        if(N == 0 || N==1)
            return N;
        return fib1(N-1) + fib1(N-2);
    }

    /**
     * Approach 2: Iterative solution
     * Much faster than recursive solution because we are not calculating the
     * fibonacci of overlapping subproblems
     */
    public int fib2(int N) {
        if(N == 0 || N==1)
            return N;
        int fib1 = 0, fib2 = 1;
        int fib = 0;
        for(int i = 2; i<=N; i++){
            fib = fib1+fib2;
            fib1= fib2;
            fib2 = fib;
        }
        return fib;
    }

    /**
     * Approach 3: Dynamic Programming. Top Down with Memoization
     * A much better approach:  whenever we solve a sub-problem, we cache its result
     * so that we don’t end up solving it repeatedly if it’s called multiple times.
     * Instead, we can just return the saved result.
     * This technique of storing the results of already solved subproblems is called Memoization.
     */
    public int fib3(int N) {
        int[] cache = new int[N+1];
        return fibHelper(N,cache);
    }

    private int fibHelper(int N, int[] cache){

        if(cache[N]>0)
            return cache[N];

        if(N == 0 || N ==1){
            cache[N] = N;
            return N;
        }

        int fib = fibHelper(N-1, cache) + fibHelper(N-2, cache);
        cache[N] = fib;
        return fib;
    }

    /**
     * Approach 4: Bottom-up with Tabulation
     * Avoids recursion, solve the subproblems first and store the result in a table
     * (can be of any dimension depending on problem)
     * Based on the results in the table, the solution to the top/original problem is then computed.
     */
    public int fib4(int N) {
        if(N==0 || N ==1)
            return 1;
        int[] table = new int[N+1];
        table[0] = 0;
        table[1] = 1;

        for(int i = 2; i<=N; i++){
            int fib = table[i-1] + table[i-2];
            table[i] = fib;
        }
        return table[N];
    }

}
