package scaler;

public class CountOfDivisors {
    public int[] solve(int[] A) {
        int[] output = new int[A.length];
        for(int i = 0; i<A.length; i++){
            output[i] = count(A[i]);
        }
        return output;
    }

    private int count(int num){
        int count = 0;
        double sqrt = Math.sqrt(num);
        boolean isPerfectSquare = sqrt-Math.floor(sqrt) == 0;

        for(int i = 1; i<=sqrt; i++){
            if(num%i != 0)
                continue;
            count= count+2;
        }
        return isPerfectSquare? count-1 : count;
    }
}
