package problem_solving.recursion_and_backtracking;

import java.util.ArrayList;
import java.util.Stack;

public class GenerateAllParenthesesII {
    /**
     * Approach 1: Brute Force
     * Observations: for input A, every arrangement  of parenthesis will have 2*A length
     *
     * Intuition: at every index, we have 2 choices: either to append '(' or to append ')'
     *                          []                i:0
     *            [(]                         [)] i:1
     *       [((]      [()]                       i:2
     *   [(((] [(()]                              i:3
     *
     * So generate all possible strings with '(' and ')' of length 2A
     * Add valid arrangements to output list.
     */
    private class Approach1{
        private ArrayList<String> arrangements;
        private int size;
        public ArrayList<String> generateParenthesis(int A) {
            size = A;
            arrangements = new ArrayList<>();
            if(A == 0)
                return arrangements;
            char[] emptyArrangment = new char[size*2];
            helper(emptyArrangment, 0);
            return arrangements;
        }

        private void helper(char[] arrangement, int index){
            if(index == 2*size){
                if(isValid(arrangement)){
                    arrangements.add(new String(arrangement));
                }
                return;
            }
            arrangement[index] = '(';
            helper(arrangement, index+1);
            arrangement[index] = ')';
            helper(arrangement, index+1);
            return;
        }

        private boolean isValid(char[] arr){
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i<arr.length; i++){
                if(arr[i] == '('){
                    stack.push('(');
                }else{
                    if(stack.isEmpty())
                        return false;
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
    }

    /**
     * Approach 2: Same as approach 1 but with pruning
     * Observation: for input A:  every valid arrangement will count of '(': A & count of ')': A
     * At any point if '(' gets bigger than A, arrangment will be invalid
     * Also, at any point if count of ')' gets bigger than '(', those strings will also be invalid.
     */
    private class Approach2{
        private ArrayList<String> arrangements;
        private int size;
        public ArrayList<String> generateParenthesis(int A) {
            size = A;
            arrangements = new ArrayList<>();
            if(A == 0)
                return arrangements;
            char[] emptyArrangment = new char[2*size];
            helper(emptyArrangment,0,0,0);
            return arrangements;
        }

        private void helper(char[] arrangement, int index, int left, int right){
            if(right>left)
                return;
            if(left>size)
                return;
            if(index == 2*size){
                arrangements.add(new String(arrangement));
                return;
            }
            arrangement[index] = '(';
            helper(arrangement, index+1, left+1, right);
            arrangement[index] = ')';
            helper(arrangement, index+1, left, right+1);
            return;
        }
    }


}
