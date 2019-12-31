package datastructures;

import datastructures.interfaces.ITrie;

public class Trie implements ITrie {

    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    @Override
    public boolean isEmpty() {
        return root.children.isEmpty();
    }

    @Override
    public void insert(String word) {
        if(root == null)
            root = new TrieNode();
        TrieNode node = root;
        for(int i = 0; i< word.length(); i++){
            char c = word.charAt(i);
            if(!node.children.containsKey(c))
                node.children.put(c,new TrieNode());
            node = node.children.get(c);
        }
        node.endOfWord = true;
    }

    @Override
    public boolean find(String word) {
        if(root == null)
            return false;
        TrieNode node = root;
        for(int i = 0; i<word.length(); i++){
            char c = word.charAt(i);
            if(node == null || !node.children.containsKey(c))
                return false;
            node = node.children.get(c);
        }
        return node.endOfWord;
    }

    @Override
    public void delete(String word) {

    }
}
