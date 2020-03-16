package grokking_ds_patterns.top_k_elements;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string, find if its letters can be rearranged in such a way that no two same characters come next to each other.
 *
 * Example 1:
 *
 * Input: "aappp"
 * Output: "papap"
 * Explanation: In "papap", none of the repeating characters come next to each other.
 * Example 2:
 *
 * Input: "Programming"
 * Output: "rgmrgmPiano" or "gmringmrPoa" or "gmrPagimnor", etc.
 * Explanation: None of the repeating characters come next to each other.
 * Example 3:
 *
 * Input: "aapa"
 * Output: ""
 * Explanation: In all arrangements of "aapa", atleast two 'a' will come together e.g., "apaa", "paaa".
 */
public class RearrangeString {
    /**
     * Wrong approach:
     * Fails for cases where in a string of length n, frequency of one of the characters (n+1)/2
     * Example: aappp.
     * @param str
     * @return
     */
    public static String rearrangeString(String str) {
        if(str.length()==0)
            return str;

        StringBuilder sb = new StringBuilder();
        List<Character> list = new LinkedList<>();

        for(int i = 1; i<str.length(); i++)
            list.add(str.charAt(i));
        sb.append(str.charAt(0));


        while(sb.length() < str.length()){
            int i;
            int size = list.size();
            for( i = 0;i<size; i++){
                if(sb.charAt(sb.length()-1) != list.get(i)){
                    sb.append(list.get(i));
                    list.remove(i);
                    break;
                }
            }
            if(i == size)
                return "";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aappp"));
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("Programming"));
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aapa"));
    }
}
