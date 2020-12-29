package problem_solving;

public class FindAPeakElement {
    public int solve(int[] A) {
        if(A == null || A.length == 0)
            return -1;
        int start = 0, end = A.length-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            int leftEl = get(A, mid-1);
            int midEl = get(A, mid);
            int rightEl = get(A, mid+1);
            if(leftEl<=midEl && rightEl<=midEl)
                return midEl;
            else if(rightEl>= midEl){
                start = mid+1;
            }else end = mid-1;
        }
        return -1;
    }

    private int get(int[]A, int index){
        if(index < 0 || index >= A.length )
            return Integer.MIN_VALUE;
        return A[index];
    }

    public static void main(String[] args) {
        FindAPeakElement driver = new FindAPeakElement();
        int output = driver.solve(new int[]{18, 33, 100, 135, 203, 270, 292, 310, 356, 392, 400, 429, 436, 461, 427, 403, 399, 375, 251, 245, 173, 130, 43});
        System.out.println(output);
    }
}
