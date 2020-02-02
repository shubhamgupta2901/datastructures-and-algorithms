package algorithms.dp.knapsack;

/**
 * Given a set of positive numbers, partition the set into two subsets with a minimum difference between their subset sums.
 *
 * Example:
 * Input: {1, 2, 3, 9}
 * Output: 3
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of numbers is '3'. Following are the two subsets: {1, 2, 3} & {9}.
 *
 * Example:
 * Input: {1, 2, 7, 1, 5}
 * Output: 0
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of number is '0'. Following are the two subsets: {1, 2, 5} & {7, 1}.
 *
 * Example:
 * Input: {1, 3, 100, 4}
 * Output: 92
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of numbers is '92'. Here are the two subsets: {1, 3, 4} & {100}.
 */
public class MinimumSubsetSumDifference {

    /**
     * Approach 1: Brute force
     * The idea is to create all the possible subsets and finding out its sum.
     * Sum of array - sum of subset is the remainder sum left.
     * return the subset which has minimum absolute difference between its sum and remainder sum.
     * Time complexity: O(2^n)
     * Space Complexity: O(n) for recursive stack, because at any point the stack will have maximum n elements.
     */
    public int findDifferenceApproach1(int []set){
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum+=set[i];

        return  findDifferenceHelper(set,0,0,sum);
    }

    private int findDifferenceHelper(int [] set, int index, int subsetSum, int remainder){
        if(index == set.length)
            return Math.abs(subsetSum-remainder);
        return Math.min(findDifferenceHelper(set,index+1,subsetSum,remainder),
                findDifferenceHelper(set, index+1,subsetSum+set[index],remainder-set[index]));
    }


    /**
     * Approach 2: Same as approach 1 but a modification in thinking process.
     * I don't need to maintain the remainder of sum of array
     * Because remainder will always be total sum of array - total sum of subset.
     * And the difference between the sum of current subset and the sum of rest of elements will be
     * difference of sum between subsets = |sum of current subset - sum of remainder of array|
     * Or |sum of current subset - (total sum of array - sum of current subset)|
     * Time complexity: O(2^n)
     * Space Complexity: O(n)
     */
    public int findDifferenceApproach2(int[] set) {
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum+=set[i];
        return this.findDifferenceHelper(set,sum, 0,0);
    }

    private int findDifferenceHelper2(int [] set,int totalSum, int index, int subsetSum){
        if(index == set.length)
            return Math.abs(totalSum-2*subsetSum);
        return Math.min(findDifferenceHelper2(set,totalSum,index+1,subsetSum),
                findDifferenceHelper2(set, totalSum,index+1,subsetSum+set[index]));
    }


    public static void main(String[] args) {
        MinimumSubsetSumDifference ps = new MinimumSubsetSumDifference();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.findDifferenceApproach1(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.findDifferenceApproach1(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.findDifferenceApproach1(num));
    }

}
