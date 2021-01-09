package problem_solving.strings;

import java.util.Stack;

/**
 * Reverse the String
 * Given a string A of size N.
 * Return the string A after reversing the string word by word.
 *
 * NOTE:
 * A sequence of non-space characters constitutes a word.
 * Your reversed string should not contain leading or trailing spaces, even if it is present in the input string.
 * If there are multiple spaces between words, reduce them to a single space in the reversed string.
 *
 * Problem Constraints: 1 <= N <= 3 * 105
 * Input Format: The only argument given is string A.
 * Output Format: Return the string A after reversing the string word by word.
 *
 * Input: A = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Input : A = "this is ib"
 * Output: "ib is this"
 */
public class SentenceReverse {

    static class Approach1{
        public String solve(String A) {
            if(A == null || A.length()<=1)
                return A;

            char[] arr = getTrimmedArray(A);
            int i = 0;
            int len = arr.length;
            while(i<len){
                if(arr[i] == ' '){
                    i++;
                    continue;
                }
                int j = i;
                while(j<len-1 && arr[j+1]!=' '){
                    j++;
                }
                reverse(arr,i, j);
                i= j+1;
            }
            reverse(arr, 0, len-1);
            return new String(arr);
        }

        private void reverse(char[] arr, int i, int j){
            while(i<j){
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        private void swap(char[] arr, int i, int j){
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private char[] getTrimmedArray(String str){
            StringBuilder builder = new StringBuilder();
            int len = str.length();
            char[] arr = str.toCharArray();
            int i = 0;
            while(i<len){
                if(arr[i] == ' '){
                    i++;
                    continue;
                }
                int j = i;
                while(j<len && arr[j]!=' '){
                    builder.append(arr[j]);
                    j++;
                }
                builder.append(' ');
                i= j+1;
            }
            if(builder.length()>=1)
                builder.deleteCharAt(builder.length()-1);
            return builder.toString().toCharArray();
        }
    }

    static class Approach2{
        public String solve(String A) {
            if(A==null || A.isEmpty())
                return A;
            Stack<Character> stack = new Stack<>();
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i< A.length(); i++){
                char c = A.charAt(i);
                if(c == ' '){
                    if(!stack.isEmpty()){
                        while(!stack.isEmpty()){
                            builder.append(stack.pop());
                        }
                        builder.append(' ');
                    }
                }else{
                    stack.push(c);
                }
            }
            while(!stack.isEmpty()){
                builder.append(stack.pop());
            }

            int length = builder.length();
            if(length>=1 && builder.charAt(length) ==' '){
                length--;
            }
            for(int i = 0; i< length; i++){
                stack.push(builder.charAt(i));
            }
            builder = new StringBuilder();
            while(!stack.isEmpty()){
                builder.append(stack.pop());
            }

            return builder.toString();
        }
    }
    public static void main(String[] args) {
        String input = "fwbpudnbrozzifml osdt ulc jsx kxorifrhubk ouhsuhf sswz qfho dqmy sn myq igjgip iwfcqq";
        SentenceReverse.Approach1 driver = new SentenceReverse.Approach1();
        String output = driver.solve(input);
        System.out.println(output);
    }
}
