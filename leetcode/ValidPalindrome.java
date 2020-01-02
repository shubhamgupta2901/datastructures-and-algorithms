package leetcode;

/**
 * <a href="https://leetcode.com/problems/valid-palindrome/"/>
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 *
 * Observations : Following are valid palindromes:
 * empty string, "!." ,"._?", etc
 * "..a", "a.>"
 * "a_a"
 * "aa&a"
 * "Aba"
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if(s.length() == 0)
            return true;
        int i = 0;
        int j = s.length()-1;
        while(i<j){
            char iChar = s.charAt(i);
            char jChar = s.charAt(j);
            if(!Character.isLetterOrDigit(iChar)){
                i++;
                continue;
            }
            if(!Character.isLetterOrDigit(jChar)){
                j--;
                continue;
            }
            if(Character.toLowerCase(iChar)!=Character.toLowerCase(jChar))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
