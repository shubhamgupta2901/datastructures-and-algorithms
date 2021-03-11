package problem_solving.graphs;

import java.util.*;

/**
 * Given an directed graph having A nodes labelled from 1 to A containing M edges given by matrix B of size M x 2such that there is a edge directed from node
 * B[i][0] to node B[i][1].
 * Find whether a path exists from node 1 to node A.
 * Return 1 if path exists else return 0.
 * <p>
 * NOTE:
 * There are no self-loops in the graph.
 * There are no multiple edges between two nodes.
 * The graph may or may not be connected.
 * Nodes are numbered from 1 to A.
 * Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
 * <p>
 * Problem Constraints
 * 2 <= A <= 105
 * 1 <= M <= min(200000,A*(A-1))
 * 1 <= B[i][0], B[i][1] <= A
 * <p>
 * Input Format
 * The first argument given is an integer A representing the number of nodes in the graph.
 * The second argument given a matrix B of size M x 2 which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
 * <p>
 * Output Format
 * Return 1 if path exists between node 1 to node A else return 0.
 * <p>
 * Examples:
 * <p>
 * Input 1:
 * A = 5
 * B = [  [1, 2]
 * [4, 1]
 * [2, 4]
 * [3, 4]
 * [5, 2]
 * [1, 3] ]
 * Output 1: 0 (The given doens't contain any path from node 1 to node 5 so we will return 0.)
 * <p>
 * Input 2:
 * A = 5
 * B = [  [1, 2]
 * [2, 3]
 * [3, 4]
 * [4, 5] ]
 * Output 2: 1 (Path from node1 to node 5 is ( 1 -> 2 -> 3 -> 4 -> 5 ) so we will return 1.)
 */
public class PathInDirectedGraph {
    /**
     * Approach 1: Representing the graph as Adjacency List , Traversing using BFS
     */
    class Approach1 {
        public int solve(int A, int[][] B) {
            HashMap<Integer, LinkedList<Integer>> graph = createGraph(B);
            return isEdge(graph, 1, A) ? 1 : 0;
        }

        private HashMap<Integer, LinkedList<Integer>> createGraph(int[][] B) {
            HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();

            for (int i = 0; i < B.length; i++) {
                if (graph.containsKey(B[i][0])) {
                    LinkedList list = graph.get(B[i][0]);
                    list.add(B[i][1]);
                } else {
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(B[i][1]);
                    graph.put(B[i][0], list);
                }
            }
            return graph;
        }

        private boolean isEdge(HashMap<Integer, LinkedList<Integer>> graph, int startingNode, int lastNode) {

            Queue<Integer> queue = new LinkedList<>();
            HashSet<Integer> visitedSet = new HashSet<>();
            queue.add(startingNode);
            visitedSet.add(startingNode);
            while (!queue.isEmpty()) {
                Integer node = queue.remove();
                if (node == lastNode)
                    return true;
                if (graph.containsKey(node)) {
                    LinkedList<Integer> list = graph.get(node);
                    for (Integer child : list) {
                        if (!visitedSet.contains(child)) {
                            queue.add(child);
                            visitedSet.add(child);
                        }
                    }
                }
            }

            return false;
        }
    }

    /**
     * Approach: Adjacency List with DFS
     */
    class Approach2{
        public int solve(int A, int[][] B) {
            HashMap<Integer, LinkedList<Integer>> graph = createGraph(B);
            HashSet<Integer> visitedSet = new HashSet<>();
            return dfs(graph, visitedSet, 1, A) ? 1 : 0;
        }

        private HashMap<Integer, LinkedList<Integer>> createGraph(int[][]B){
            HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
            for(int i = 0; i<B.length; i++){
                if(graph.containsKey(B[i][0])){
                    LinkedList<Integer> list = graph.get(B[i][0]);
                    list.add(B[i][1]);
                }else {
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(B[i][1]);
                    graph.put(B[i][0], list);
                }
            }
            return graph;
        }

        private boolean dfs(HashMap<Integer, LinkedList<Integer>> graph, HashSet<Integer> visitedSet, int startNode, int endNode){
            visitedSet.add(startNode);
            if(graph.containsKey(startNode)){
                LinkedList<Integer> list = graph.get(startNode);
                for(Integer child: list){
                    if(child == endNode)
                        return true;
                    if(!visitedSet.contains(child)){
                        if(dfs(graph, visitedSet, child, endNode))
                            return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * Representing graph as adjacency matrix, traversing as BFS
     */
    class Approach3{
        public int solve(int A, int[][] B) {
            int[][] graph = createGraph(A, B);
            return isEdge(graph, 1, A) ? 1 : 0;
        }

        private int[][] createGraph(int A, int[][]B){
            int[][] graph = new int[A+1][A+1];
            for(int i = 0; i<B.length; i++){
                graph[B[i][0]][B[i][1]] = 1;
            }
            return graph;
        }

        private boolean isEdge(int[][] graph, int startingNode, int endNode){
            HashSet<Integer> visitedSet = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.add(startingNode);
            visitedSet.add(startingNode);
            while(!queue.isEmpty()){
                Integer node = queue.remove();
                for(int i = 0; i< graph[node].length; i++){
                    if(graph[node][i]==1 && !visitedSet.contains(i)){
                        if(i == endNode)
                            return true;
                        visitedSet.add(i);
                        queue.add(i);
                    }
                }
            }
            return false;

        }
    }
}
