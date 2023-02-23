package org.saurav.simpletests.ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent the graph in multiple forms and print it
 */
public class GraphRepresentation {

    private Map<Node , ArrayList<Node>> adjacencyList = new HashMap<Node, ArrayList<Node>>();
    private List<Node> nodeList = new ArrayList<Node>();

    public static void main (String a[]) {
        GraphRepresentation gr = new GraphRepresentation();
        gr.setupGraph();
        gr.printGraph();
    }

    private void setupGraph() {
        addVertices(5);
        addEdge(nodeList.get(0), nodeList.get(1));
        addEdge(nodeList.get(0), nodeList.get(4));
        addEdge(nodeList.get(1), nodeList.get(2));
        addEdge(nodeList.get(1), nodeList.get(3));
        addEdge(nodeList.get(1), nodeList.get(4));
        addEdge(nodeList.get(2), nodeList.get(3));
        addEdge(nodeList.get(3), nodeList.get(4));

    }

    /**
     * Setups the edges and the graph using adjacency list
     * @param source
     * @param target
     */
    private void addEdge(Node source, Node target) {
        adjacencyList.get(source).add(target);
    }

    private void addVertices(int noOfVertices){
        for(int i= 0 ; i< noOfVertices; i++){
            Node node = new Node(i);
            nodeList.add(node);
            adjacencyList.put(node, new ArrayList<Node>());
        }

    }

    private void printGraph() {
        System.out.println("Printing the graph");
       adjacencyList.forEach((node, nodes) -> nodes.forEach(node1 -> {System.out.println("edge" + node.getData() + "to" + node1.getData());}));

    }

}

class Node {
    private int data;

    Node(int data){
        this.data = data;
    }

    public int getData(){
        return data;
    }
}
