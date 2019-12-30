package datastructures;

import datastructures.interfaces.ITrie;

public class Trie implements ITrie {

    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void insert(String word) {

    }

    @Override
    public boolean find(String word) {
        return false;
    }

    @Override
    public void delete(String word) {

    }
}
