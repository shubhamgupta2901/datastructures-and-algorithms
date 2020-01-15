package algorithms.graphs;

import datastructures.Graph;

import java.util.*;

public class BreadthFirstTraversal {

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
}
