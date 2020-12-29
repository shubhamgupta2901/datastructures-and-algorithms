package problem_solving;

/**
 * Scooby has 3 three integers A, B and C.
 * Scooby calls a positive integer special if it is divisible by B and it is divisible by C.
 * You need to tell number of special integers less than or equal to A.
 * Problem Constraints
 * 1 <= A, B, C <= 109
 * Input Format
 * First argument is a positive integer A
 * Second argument is a positive integer B
 * Third argument is a positive integer C
 * Output Format: One integer corresponding to the number of special integers less than or equal to A.
 *
 * Input :
 *
 *  A = 12
 *  B = 3
 *  C = 2
 * Output: 2 (The two integers divisible by 2 and 3 and less than or equal to 12 are 6,12.)
 *
 * Input 2:
 *
 *  A = 6
 *  B = 1
 *  C = 4
 * Output : 1 (Only 4 is a positive integer less than equal to 6 which is divisible by 1 and 4)
 *
 */
public class DivisorGame {
    /**
     * Approach 1: Brute force
     * iterate i from max(B,C) to A
     * update counter every time a value divides both B and C
     * time complexity O(A-max(B,C)) ->Linear
     */
    public int approach1(int A, int B, int C) {
        int counter = 0;
        for(int i = Math.max(B,C); i<=A; i++){
            if(i%B == 0 && i%C == 0)
                counter++;
        }
        return counter;
    }

    /**
     * Approach 2:
     * Intuition: The solution is  equal to the no of multiples of LCM(B,C) in A
     * Ex.A=100 , B=16, C=24 , lcm (B, C) is 48,
     * multiples of no of multiples of 48 in 100 are; 100/48 = 2 i.e 48 nd 96.
     * So 2 is answer
     * Also LCM*HCF = a*b
     * so, rather than finding LCM, I am finding HCF or GCD
     * Time complexity: O(sqrt(min(B, C)) -> Sqrt
     */
    public int approach2(int A, int B, int C) {
        int gcd = B<=C ? findGCD(B, C): findGCD(C,B);
        int lcm =  B/gcd * C;
        return A/lcm;
    }
    private int findGCD(int X, int Y){
        int gcd = 1;
        for(int i = 1; i<= Math.sqrt(X);i++){
            if(i % X !=0)
                continue;
            int f1 = X/i;
            int f2 = X/f1;
            if(Y%f1==0)
                gcd = Math.max(gcd, f1);
            if(Y%f2==0)
                gcd = Math.max(gcd, f2);
        }
        return gcd;
    }

    /**
     * Approach 2:
     * Same as approach 2, but efficiently finding hcf using euclid's algorithm
     * Time complexity: O(log(min(a,b))
     */
    public int approach3(int A, int B, int C) {
        int gcd = gcd(B,C);
        int lcm =  B/gcd * C;
        return A/lcm;
    }

    private int gcd (int a, int b){
        if(b ==0)
            return a;
        return gcd(b, a%b);
    }
}
