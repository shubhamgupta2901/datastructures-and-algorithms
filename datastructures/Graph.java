package datastructures;

import java.util.Arrays;

/**
 * Graphs can either be represented as Adjacency Matrix or Adjacency List
 *
 * This is Adjacency Matrix representation of graph.
 * If there are n vertices in graph, we will create a 2d array mat of n*n size.
 * mat[u][v] is true if there is an edge from vertex u to v (u->v)
 */
public class Graph {

    private boolean[][] mat;
    private int vertexCount;

    public Graph(int vertexCount){
        this.vertexCount = vertexCount;
        mat = new boolean[vertexCount][vertexCount];
    }

    public int getVertexCount(){
        return this.vertexCount;
    }

    public void addEdge(int fromVertex, int toVertex){
        if(isValidVertex(fromVertex) && isValidVertex(toVertex))
            mat[fromVertex][toVertex] = true;
    }

    public void removeEdge(int fromVertex, int toVertex){
        if(isValidVertex(fromVertex) && isValidVertex(toVertex))
            mat[fromVertex][toVertex] = false;
    }

    public boolean isEdge(int fromVertex, int toVertex){
        if(isValidVertex(fromVertex) && isValidVertex(toVertex))
            return mat[fromVertex][toVertex];
        return false;
    }

    public void printGraph(){
        System.out.println(Arrays.deepToString(mat)
                .replace("], ", "]\n")
                .replace("[[", "[")
                .replace("]]", "]"));
    }

    private boolean isValidVertex(int vertex){
        if(vertex>0 || vertex<vertexCount)
            return true;
        return false;
    }


}
