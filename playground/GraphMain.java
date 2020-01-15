package playground;

import datastructures.Graph;

public class GraphMain {

    public static void main(String[] args) {
        GraphMain demo = new GraphMain();
        demo.createGraph1();
    }

    // Undirected Unweighted graph
    public void createGraph1(){
        int vertexCount = 5;
        Graph graph = new Graph(vertexCount);

        for(int i = 1; i<vertexCount; i++){
            graph.addEdge(0,i);
            graph.addEdge(i,0);
        }

        graph.addEdge(1,3);
        graph.addEdge(3,1);

        graph.addEdge(3,2);
        graph.addEdge(2,3);

        graph.addEdge(2,4);
        graph.addEdge(4,2);

        graph.printGraph();
    }
}
