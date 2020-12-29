package problem_solving.recursion_and_backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class RemoveInvalidParentheses   {
    private HashSet<String> set;
    private int minDeletions = Integer.MAX_VALUE;
    public ArrayList<String> solve(String A) {
        set = new HashSet<>();
        ArrayList<String> output = new ArrayList<>();

        if(A == null || A.length() == 0)
            return output;
        helper(A, 0, 0);

        for (String st: set){
            output.add(st);
        }
        return output;
    }

    private void helper(String str, int index, int deletions){
        if(isValid(str)){
            if(deletions == minDeletions){
                set.add(new String(str));
            }else if(deletions<minDeletions){
                set.clear();
                set.add(new String(str));
                minDeletions = deletions;
            }
            return;
        }

        if(index == str.length()){
            return;
        }
        if(str.charAt(index) =='(' || str.charAt(index)==')'){
            helper(str, index+1, deletions);
            String deletedString = (index>0) ? str.substring(0, index) + str.substring(index+1) : str.substring(index+1);
            helper(deletedString, index, deletions+1);
        }else helper(str, index+1, deletions);
    }

    private boolean isValid(String str){
        if(str.length() == 0)
            return true;
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i<str.length(); i++){
            char c = str.charAt(i);
            if(c == '(')
                stack.push(c);
            else if(c == ')'){
                if(stack.isEmpty())
                    return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses driver = new RemoveInvalidParentheses();
        ArrayList<String> output = driver.solve("()))()");
        for(String str: output){
            System.out.println(str);
        }
    }
}

/**
         *                                      ()))()
 *                            ())()            ())()        ())()
 */

