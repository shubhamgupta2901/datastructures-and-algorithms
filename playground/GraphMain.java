package playground;

import algorithms.graphs.BreadthFirstTraversal;
import datastructures.Graph;

public class GraphMain {

    public static void main(String[] args) {
        GraphMain demo = new GraphMain();
        demo.testGraph1();
        demo.testGraph2();
    }

    // Undirected Unweighted graph
    public void testGraph1(){
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

        //graph.printGraph();
        BreadthFirstTraversal bfs = new BreadthFirstTraversal();
        Utils.printList(bfs.performTraversal(graph,0));
    }

    public void testGraph2(){
        int vertexCount = 4;
        Graph graph = new Graph(vertexCount);

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(3,3);

        //graph.printGraph();
        BreadthFirstTraversal bfs = new BreadthFirstTraversal();
        Utils.printList(bfs.performTraversal(graph,2));
    }
}
