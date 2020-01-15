package algorithms.graphs;

import datastructures.Graph;

import java.util.*;

/**
 * Breadth First Traversal (or Search) is an algorithm for traversing graph data structures.
 * It starts at some arbitrary node of a graph and explores the neighbour nodes first,
 * before moving to the next level of neighbours.
 *
 * BFS for a graph is similar to Breadth First Traversal of a binary tree.
 * The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again.
 * To avoid processing a node more than once, we use a boolean visited array or a hashset.
 *
 *
 */
public class BreadthFirstTraversal {

    /**
     * Note it is assumed that all vertices are reachable from the starting vertex (Connected graphs).
     * A graph is disconnected if at least two vertices of the graph are not connected by a path.
     * For disconnected graphs we will have to perform BFS for all nodes till all nodes are visited.
     * Time complexity of BFS depends on the data structure we are using.
     * For a graph with n vertices represented by Adjacency Matrix
     * Time Complexity: O(n^2)
     * Space Complexity: O(n) because the queue and visited set will at max store all the vertices.
     * @param graph
     * @param startingVertex
     * @return
     */
    public List<Integer> performTraversal(Graph graph, int startingVertex){
        List<Integer> traversal = new ArrayList<>();

        if(graph.getVertexCount()<=0)
            return traversal;

        HashSet<Integer> visitedVertices = new HashSet();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(startingVertex);
        traversal.add(startingVertex);
        visitedVertices.add(startingVertex);

        while(!queue.isEmpty()){
            int vertex = queue.remove();
            for(int i = 0; i<graph.getVertexCount(); i++){
                if(graph.isEdge(vertex,i) && !visitedVertices.contains(i)){
                    queue.add(i);
                    traversal.add(i);
                    visitedVertices.add(i);
                }
            }
        }
        return traversal;
    }

    /**
     * BFS traversal for a disconnected graph.
     * A graph is disconnected if at least two vertices of the graph are not connected by a path.
     * Time Complexity: O(n^3) because after every BFS traversal,
     * we are checking we
     * @param graph
     * @return
     */
    public List<Integer> performTraversalForDisconnectedGraph(Graph graph){
        List<Integer> traversal = new ArrayList<>();
        if(graph.getVertexCount() <=0)
            return traversal;
        boolean[] visitedVertices = new boolean[graph.getVertexCount()];
        for(int i = 0 ; i<visitedVertices.length; i++){
            int startVertex = i;
            if(visitedVertices[i])
                continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(startVertex);
            traversal.add(startVertex);
            visitedVertices[startVertex]=true;

            while(!queue.isEmpty()){
                int vertex = queue.remove();
                for(int j = 0; j<graph.getVertexCount(); j++){
                    if(graph.isEdge(vertex,j) && visitedVertices[j] ==false){
                        queue.add(j);
                        traversal.add(j);
                        visitedVertices[j] =true;
                    }
                }
            }
        }
        return traversal;
    }
}
