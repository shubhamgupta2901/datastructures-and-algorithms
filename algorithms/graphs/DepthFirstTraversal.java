package algorithms.graphs;

import datastructures.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Depth First Search (or Traversal) is an algorithm for traversing graphs.
 * It starts at the starting node and keeps exploring as far as possible along each edge before backtracking.
 *
 * It is similar to Depth First Traversal of a tree. The only catch here is, unlike trees,
 * graphs may contain cycles,so we may come to the same node again.
 * To avoid processing a node more than once, we use a boolean visited array.
 *
 */
public class DepthFirstTraversal {

    /**
     * Recursive approach to perform traversal
     * For graph of n vertices
     * Time Complexity: O(n^2)
     * Space Complexity: O(n) for recursive stack.
     * This time complexity can be improved if we use adjacency list to represent graphs
     * @param graph
     * @param startingVertex
     * @return
     */
    public List<Integer> performTraversal(Graph graph, int startingVertex){
        List<Integer> traversal = new ArrayList<>();
        if(graph.getVertexCount()<=0 || startingVertex >= graph.getVertexCount())
            return traversal;
        boolean[] visited = new boolean[graph.getVertexCount()];
        dfs(graph,traversal,visited,startingVertex);
        return traversal;

    }

    private void dfs(Graph graph, List<Integer> traversal, boolean[] visited, int vertex){
        traversal.add(vertex);
        visited[vertex] = true;
        for(int i = 0; i<graph.getVertexCount(); i++){
            if(graph.isEdge(vertex,i) && !visited[i])
                dfs(graph,traversal,visited,i);
        }
    }

    /**
     * Iterative approach to perform traversal
     * For graph of n vertices
     * Time Complexity: O(n^2)
     * Space Complexity: O(n) for stack because at any time there won't be more than n elements in stack.
     * This time complexity can be improved if we use adjacency list to represent graphs
     * @param graph
     * @param startingVertex
     * @return
     */
    @Deprecated
    public List<Integer> performIterativeTraversal(Graph graph, int startingVertex){
        List<Integer> traversal = new ArrayList<>();
        if(graph.getVertexCount()<= 0 || startingVertex >= graph.getVertexCount())
            return traversal;
        boolean[] visited = new boolean[graph.getVertexCount()];
        Stack<Integer> stack = new Stack<>();

        stack.push(startingVertex);
        visited[startingVertex] = true;
        traversal.add(startingVertex);

        while(!stack.isEmpty()){
            int vertex = stack.peek();
            int i;
            for(i = 0; i<graph.getVertexCount(); i++){
                if(graph.isEdge(vertex,i) && !visited[i]){
                    stack.push(i);
                    visited[i] = true;
                    traversal.add(i);
                    break;
                }
            }

            if(i == graph.getVertexCount())
                stack.pop();

        }
        return traversal;

    }

    /**
     * Iterative approach to perform traversal
     * For graph of n vertices
     * Time Complexity: O(n^2)
     * Space Complexity: O(n) for stack. [Need to recheck]
     * This time complexity can be improved if we use adjacency list to represent graphs
     * @param graph
     * @param startingVertex
     * @return
     */
    public List<Integer> iterativeTraversal(Graph graph, int startingVertex){
        List<Integer> traversal = new ArrayList<>();
        if(graph.getVertexCount()<= 0 || startingVertex >= graph.getVertexCount())
            return traversal;
        boolean[] visited = new boolean[graph.getVertexCount()];
        Stack<Integer> stack = new Stack<>();
        stack.push(startingVertex);
        while(!stack.isEmpty()){
            int vertex = stack.pop();
            if(!visited[vertex]){
                traversal.add(vertex);
                visited[vertex] = true;
            }

            for(int i = graph.getVertexCount()-1; i>=0; i--){
                if(graph.isEdge(vertex,i) && !visited[i])
                    stack.push(i);
            }
        }
        return traversal;

    }
}

