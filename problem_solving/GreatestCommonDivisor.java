package problem_solving;

public class GreatestCommonDivisor   {


    /**
     * Time Complexity: O(n) where n is min(A,B)
     */
    public int approach1(int A, int B) {
        if(A == 0)
            return B;
        if(B == 0)
            return A;
        for(int i = Math.min(A,B); i>=1; i--){
            if(A%i == 0 && B%i==0)
                return i;
        }
        return 1;
    }


    /**
     * Time Complexity: O(sqrt(n)) where n is min(A,B)
     */
    public int approach2(int A, int B) {
        if(A==0)
            return B;
        if(B == 0)
            return A;
        int X = Math.min(A,B);
        int Y = Math.max(A,B);
        int gcd = 1;
        for(int f1 = 1; f1<=Math.sqrt(X); f1++){
            if(X%f1!=0)
                continue;
            if(Y%f1 == 0)
                gcd = Math.max(gcd, f1);
            int f2 = X/f1;
            if(Y%f2 == 0)
                gcd = Math.max(gcd, f2);
        }
        return gcd;
    }

    /**
     * Approach : Euclid's property
     * Time: O(logn)??
     */
    public int gcd(int A, int B) {
        if(B == 0) return A;
        return gcd(B, A%B);
    }

    public static void main(String[] args) {
        GreatestCommonDivisor driver = new GreatestCommonDivisor();
        int x = driver.approach2(350,136);
        System.out.println(x);
    }
}
