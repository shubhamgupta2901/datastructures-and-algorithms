package leetcode;

/**
 * <a href="https://leetcode.com/problems/reverse-string/"/>
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *
 *
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
public class ReverseString {

    /**
     * Time complexity: O(n)
     * Space Complexity: O(1)
     * @param s
     */
    public void reverseString(char[] s) {
        if(s.length == 0 || s.length == 1)
            return;
        int start = 0, end = s.length-1;
        while(start<end){
            swap(s, start, end);
            start++;
            end--;
        }
        return;
    }

    void swap(char[]s, int i1, int i2){
        char temp = s[i1];
        s[i1] = s[i2];
        s[i2] = temp;
    }
}
