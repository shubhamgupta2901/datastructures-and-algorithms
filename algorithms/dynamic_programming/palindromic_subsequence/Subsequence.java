package algorithms.dynamic_programming.palindromic_subsequence;

import java.util.ArrayList;
import java.util.List;

/**
 * A subsequence is a sequence that can be derived from another sequence by deleting some
 * or no elements without changing the order of the remaining elements.
 *
 * Observation: While finding out subsets when I use the approach where I start from index 0
 * and keep making decisions whether to include the number at index i or to not use it,
 * I am finding out subsets without changing the order of elements in the array.
 * i.e. for an array {1,2,3} subsets found will always have the respective order intact like {1,3} or {2,3}
 * and not like {3,1} or {3,2}.
 * In that sense finding all subsequences of a sequence is like finding out all subsets of a set.
 *
 */

public class Subsequence {

    public List<List<Character>> findAllSubsequences(String s){
        List<List<Character>> allSubsequences = new ArrayList<>();
        char[] arr = s.toCharArray();
        List<Character> emptySubsequence = new ArrayList<>();
        helper(arr, 0, emptySubsequence, allSubsequences);
        return allSubsequences;
    }

    private void helper(char[] arr, int index, List<Character>subsequence, List<List<Character>> subsequences){
        if(index == arr.length){
            subsequences.add(subsequence);
            return;
        }

        helper(arr, index+1, subsequence, subsequences);
        List<Character> newSubsequence = new ArrayList<>(subsequence);
        newSubsequence.add(arr[index]);
        helper(arr, index+1,newSubsequence,subsequences);
    }


    List<String> findAllSubsequencesApproach2(String s){
        List<String> subsequences = new ArrayList<>();
        helper(s,0,new String(),subsequences);
        return subsequences;
    }

    private void helper(String str, int index, String subsequence, List<String> subsequences){
        if(index == str.length()){
            subsequences.add(subsequence);
            return;
        }

        helper(str,index+1,subsequence,subsequences);
        String newSubsequence = new String(subsequence);
        newSubsequence = newSubsequence.concat(str.substring(index,index+1));
        helper(str, index+1, newSubsequence, subsequences);
    }

    /** Helper methods*/

    private static void printStrings(List<List<Character>> subsequences){
        for(List<Character> subsequence: subsequences){
            for(Character chr: subsequence){
                System.out.print(chr);
            }
            System.out.println("");
        }
    }

    private static void printString(List<String> subsequences){
        for(String subsequence: subsequences){
            System.out.println(subsequence);
        }
    }


    /** Main function*/

    public static void main(String[] args) {
        String s = new String("abc");
//        List<List<Character>> subsequences = new Subsequence().findAllSubsequences(s);
//        printStrings(subsequences);
        List<String> subsequences = new Subsequence().findAllSubsequencesApproach2(s);
        printString(subsequences);
    }
}
