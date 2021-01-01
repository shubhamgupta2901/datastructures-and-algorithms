package problem_solving.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array A of N strings, return all groups of strings that are anagrams.
 * Represent a group by a list of integers representing the index(1-based) in the original list.
 * Look at the sample case for clarification.
 * NOTE: Anagram : a word, phrase, or name formed by rearranging the letters of another, such as 'spar', formed from 'rasp'.
 *
 * Problem Constraints
 * 1 <= N <= 104
 * 1 <= |A[i]| <= 104
 * Each string consists only of lowercase characters.
 * Sum of length of all the strings doesn't exceed 10^7
 *
 * Input: Single Argument A which is an array of strings.
 * Output: Return a two-dimensional array where each row describes a group.
 * Ordering of the result : You should not change the relative ordering of the strings within the group suppose within
 * a group containing A[i] and A[j], A[i] comes before A[j] if i < j.
 *
 * Input: A = [cat, dog, god, tca]
 * Output: [ [1, 4], [2, 3] ]
 * "cat" and "tca" are anagrams which correspond to index 1 and 4 and "dog" and "god" are another set of anagrams which correspond to index 2 and 3.
 *
 * Input: A = [rat, tar, art]
 * Output 2: [ [1, 2, 3] ]
 * All three strings are anagrams.
 */
public class Anagrams {
    static class Approach1{
        public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
            ArrayList<ArrayList<Integer>> output = new ArrayList<>();

            if(A == null || A.size() == 0)
                return output;
            boolean[] markArr = new boolean[A.size()];
            for(int i = 0;i<A.size();i++){
                if(markArr[i])
                    continue;
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(i+1);
                markArr[i] = true;
                for(int j = i+1; j<A.size(); j++){
                    if(markArr[j])
                        continue;
                    if(isAnagram(A.get(i), A.get(j))){
                        list.add(j+1);
                        markArr[j] = true;
                    }
                }
                output.add(list);
            }
            return output;
        }

        private boolean isAnagram(String str1, String str2){
            if(str1 == null || str2 == null)
                return false;
            if(str1.length() != str2.length())
                return false;
            HashMap<Character, Integer> map = new HashMap<>();
            for(int i = 0; i<str1.length(); i++){
                char c = str1.charAt(i);
                int frequency = map.getOrDefault(c, 0);
                map.put(c, frequency+1);
            }

            for(int i=0; i<str2.length(); i++){
                char c = str2.charAt(i);
                if(!map.containsKey(c))
                    return false;
                int frequency = map.get(c);
                if(frequency == 1)
                    map.remove(c);
                else map.put(c, frequency-1);
            }
            return map.isEmpty();
        }
    }

    static class Approach2{
        public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
            ArrayList<ArrayList<Integer>> output = new ArrayList<>();
            HashMap<String, ArrayList<Integer>> map = new HashMap<>();
            for(int i = 0; i<A.size(); i++){
                String str = A.get(i);
                char[] arr = str.toCharArray();
                Arrays.sort(arr);
                str = new String(arr);
                if(map.containsKey(str)){
                    ArrayList<Integer> list = map.get(str);
                    list.add(i+1);
                }
                else {
                   ArrayList<Integer> list = new ArrayList<>();
                   list.add(i+1);
                   map.put(str, list);
                }
            }
            for(ArrayList<Integer> list: map.values()){
                output.add(list);
            }
            return output;
        }
    }
}
