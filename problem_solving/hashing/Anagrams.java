package problem_solving.hashing;

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
}
