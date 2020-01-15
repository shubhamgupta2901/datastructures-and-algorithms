package playground;

import algorithms.graphs.BreadthFirstTraversal;

import algorithms.graphs.DepthFirstTraversal;
import datastructures.Graph;


public class GraphMain {

    public static void main(String[] args) {
        GraphMain demo = new GraphMain();
        BreadthFirstTraversal bfs = new BreadthFirstTraversal();
        DepthFirstTraversal dfs = new DepthFirstTraversal();

        demo.testGraph1(bfs, dfs);
        System.out.println("-------------");
        demo.testGraph2(bfs, dfs);
        System.out.println("-------------");
        demo.testGraph3(bfs, dfs);
    }

    // Undirected Unweighted graph
    public void testGraph1(BreadthFirstTraversal bfs, DepthFirstTraversal dfs){
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

        System.out.println("BFS: ");
        Utils.printList(bfs.performTraversal(graph,0));
        System.out.println("DFS (recursive): ");
        Utils.printList(dfs.performTraversal(graph,0));
        System.out.println("DFS (iterative): ");
        Utils.printList(dfs.performIterativeTraversal(graph,0));
        Utils.printList(dfs.iterativeTraversal(graph,0));
    }

    public void testGraph2(BreadthFirstTraversal bfs, DepthFirstTraversal dfs){
        int vertexCount = 4;
        Graph graph = new Graph(vertexCount);

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(3,3);

        System.out.println("BFS: ");
        Utils.printList(bfs.performTraversal(graph,2));
        System.out.println("DFS (recursive): ");
        Utils.printList(dfs.performTraversal(graph,2));
        System.out.println("DFS (iterative): ");
        Utils.printList(dfs.performIterativeTraversal(graph,2));
        Utils.printList(dfs.iterativeTraversal(graph,2));
    }

    public void testGraph3(BreadthFirstTraversal bfs, DepthFirstTraversal dfs){
        int vertexCount = 9;
        Graph graph = new Graph(vertexCount);

        graph.addEdge(0,1);
        graph.addEdge(0,8);

        graph.addEdge(1,0);

        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(2,5);
        graph.addEdge(2,8);

        graph.addEdge(3,2);

        graph.addEdge(4,2);
        graph.addEdge(4,7);

        graph.addEdge(5,2);
        graph.addEdge(5,6);


        graph.addEdge(6,5);
        graph.addEdge(6,7);
        graph.addEdge(6,8);

        graph.addEdge(7,4);
        graph.addEdge(7,6);

        graph.addEdge(8,0);
        graph.addEdge(8,2);
        graph.addEdge(8,6);


        System.out.println("BFS: ");
        Utils.printList(bfs.performTraversal(graph,0));
        System.out.println("DFS (recursive): ");
        Utils.printList(dfs.performTraversal(graph,0));
        System.out.println("DFS (iterative): ");
        Utils.printList(dfs.performIterativeTraversal(graph,0));
        Utils.printList(dfs.iterativeTraversal(graph,0));

    }
}
