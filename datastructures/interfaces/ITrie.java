package datastructures.interfaces;

import java.util.HashMap;

public interface ITrie {
    class TrieNode {
         HashMap<Character, TrieNode> children;
         boolean endOfWord;

        public TrieNode(){
            this.children = new HashMap<>();
            this.endOfWord = false;
        }
    }

    boolean isEmpty();

    void insert(String word);

    boolean find(String word);

    void delete(String word);

}
