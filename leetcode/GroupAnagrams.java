package leetcode;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/group-anagrams/"/>
 * Given an array of strings, group anagrams together.
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * Note:
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 *
 */

public class GroupAnagrams {

    /**
     * TODO: All cases passed but time limit exceeds
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashSet<Integer> groupedStrings = new HashSet<>();
        HashMap<Integer,List<Integer>> indexMap = new HashMap<>();
        for(int i = 0; i< strs.length; i++){
            if(!groupedStrings.contains(i)){
                for(int j=i+1; j<strs.length; j++){
                    if(!groupedStrings.contains(j)){
                        if(isValidAnagram(strs[i],strs[j])){
                            groupedStrings.add(i);
                            groupedStrings.add(j);

                            List<Integer> list;
                            if(!indexMap.containsKey(i))
                                list = new ArrayList<>();
                            else
                                list = indexMap.get(i);
                            list.add(j);
                            indexMap.put(i,list);
                        }
                    }
                }
                if(!groupedStrings.contains(i)){
                    List<Integer> list = new ArrayList<>();
                    indexMap.put(i,list);
                }

            }
        }
        List<List<String>> list = new ArrayList<>();
        for(Map.Entry<Integer, List<Integer>> entry : indexMap.entrySet()){
            List<String> group = new ArrayList<>();
            group.add(strs[entry.getKey()]);
            for(Integer i : entry.getValue()){
                group.add(strs[i]);
            }
            list.add(group);
        }
        return list;
    }

    private boolean isValidAnagram(String str1, String str2){
        if(str1.length() == 0 && str2.length() == 0)
            return true;
        if(str1.length() != str2.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i< str1.length(); i++){
            char c = str1.charAt(i);
            if(!map.containsKey(c))
                map.put(c,1);
            else map.put(c,map.get(c) + 1);
        }

        for(int i = 0; i< str2.length(); i++){
            char c = str2.charAt(i);
            if(map.isEmpty() || !map.containsKey(c))
                return false;
            int frequency = map.get(c);
            frequency--;
            if(frequency == 0)
                map.remove(c);
            else map.put(c, frequency);
        }
        return  map.isEmpty();
    }
}
