package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;


/**
 * <a href="https://leetcode.com/problems/flatten-nested-list-iterator/"/>
 * Given a nested list of integers, implement an iterator to flatten it.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 *
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 *
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,4,6].
 *
 */

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
public class FlattenNestedListIterator {

      // This is the interface that allows for creating nested lists.
      // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.public boolean isInteger();
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /**
     * Approach 1: Brute force approach with recursion.
     * Runtime percentile is 99.92
     * Memory Usage percentile is 44.5
     * Could do better with same approach but not allocating space for new list inside recursive method.
     */
    public class NestedIterator1 implements Iterator<Integer> {
        private List<Integer> flatList;
        private Integer index;
        public NestedIterator1(List<NestedInteger> nestedList) {
            flatList= new ArrayList<>();
            flatList = flatten(nestedList);
            index = 0;
        }

        @Override
        public Integer next() {
            Integer value = flatList.get(index);
            index++;
            return value;
        }

        @Override
        public boolean hasNext() {
            return flatList.size() > index;
        }

        private List<Integer> flatten(List<NestedInteger> nestedList){
            List<Integer> flatList = new ArrayList<>();
            for(NestedInteger nI: nestedList){
                if(nI.isInteger())
                    flatList.add(nI.getInteger());
                else flatList.addAll(flatten(nI.getList()));
            }
            return flatList;
        }
    }


    /**
     * Approach 2: Same as approach 1
     * Except the recursion is space optimized by not creating lists inside recursive function.
     * Rather, having a global list, where recursive function keeps adding integers.
     * Could probably still do better with an iterative function to flatten list. This way I can avoid using recursive stack.
     * Runtime percentile is 99.92
     * Memory Usage percentile is 55
     */
    public class NestedIterator2 implements Iterator<Integer> {
        private List<Integer> flatList;
        private Integer index;
        public NestedIterator2(List<NestedInteger> nestedList) {
            flatList= new ArrayList<>();
            flatten(nestedList,flatList);
            index = 0;
        }

        @Override
        public Integer next() {
            Integer value = flatList.get(index);
            index++;
            return value;
        }

        @Override
        public boolean hasNext() {
            return flatList.size() > index;
        }

        private void flatten(List<NestedInteger> nestedList,List<Integer> flatList){
            for(NestedInteger nI: nestedList){
                if(nI.isInteger())
                    flatList.add(nI.getInteger());
                else flatten(nI.getList(),flatList);
            }
        }
    }


    /**
     * Approach 3: This is space efficient method. But I realise I can not do away with the recursion.
     * I can flatten all the NestedIntegers at once while the constructor is invoked or
     * I can delay the flattening till user calls for next().
     * This approach delays the flattening till I actually need an integer to return for next() call.
     * Runtime percentile is 48.5
     * Memory Usage percentile is 100
     * Notice in asymptotic terms the Time and space complexities of all the three approaches is same.
     * i.e. if there are total n integers (not nested integers)in the input, All these approaches
     * would take O(n) time and O(n) space.
     * This also goes to show how asymptotic analysis does not show the complete picture.
     */
    public class NestedIterator implements Iterator<Integer> {
        private Stack<NestedInteger> stack;
        public NestedIterator(List<NestedInteger> nestedList) {
            this.stack = new Stack<>();
            pushToStack(nestedList);
        }

        private void pushToStack(List<NestedInteger> nestedList){
            for(int i = nestedList.size()-1; i>=0; i--){
                NestedInteger nestedInteger = nestedList.get(i);
                stack.push(nestedInteger);
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()){
                if(stack.peek().isInteger())
                    return true;
                pushToStack(stack.pop().getList());
            }
            return false;
        }

    }
}


