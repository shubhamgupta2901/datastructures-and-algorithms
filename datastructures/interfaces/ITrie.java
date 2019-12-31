package datastructures.interfaces;

import java.util.HashMap;

public interface ITrie {
    class TrieNode {
        public HashMap<Character, TrieNode> children;
        public boolean endOfWord;

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
