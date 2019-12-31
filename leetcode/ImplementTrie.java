package leetcode;

import java.util.HashMap;

/**
 * <a href="https://leetcode.com/problems/implement-trie-prefix-tree/"/>
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class ImplementTrie {
    class Trie {

        class TrieNode{
            HashMap<Character, TrieNode> children;
            boolean endOfWord;

            TrieNode(){
                children = new HashMap<>();
            }
        }
        /** Initialize your data structure here. */
        TrieNode root;
        public Trie() {
            root = null;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(root == null)
                root = new TrieNode();
            TrieNode node = root;
            for(int i =0; i<word.length();i++){
                char c = word.charAt(i);
                if(!node.children.containsKey(c))
                    node.children.put(c,new TrieNode());
                node = node.children.get(c);
            }
            node.endOfWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode node = root;
            for(int i =0; i<word.length();i++){
                char c = word.charAt(i);
                if(node == null || !node.children.containsKey(c))
                    return false;
                node = node.children.get(c);
            }
            return node.endOfWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for(int i = 0; i< prefix.length(); i++){
                char c = prefix.charAt(i);
                if(node == null || !node.children.containsKey(c))
                    return false;
                node = node.children.get(c);
            }
            return true;
        }
    }

}
