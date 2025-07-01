package question3.strongconnectivitycheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedGraph {
    private final Map<GraphNode, List<GraphNode>> adjacencyList = new HashMap<>();

    public void addNode(GraphNode node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(GraphNode from, GraphNode to) {
        adjacencyList.get(from).add(to); // Directed edge
    }

    public List<GraphNode> getNodes() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    public List<GraphNode> getNeighbors(GraphNode node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>());
    }

    public DirectedGraph transpose() {
        DirectedGraph reversed = new DirectedGraph();
        for (GraphNode node : adjacencyList.keySet()) {
            reversed.addNode(new GraphNode(node.name));
        }
        for (GraphNode from : adjacencyList.keySet()) {
            for (GraphNode to : adjacencyList.get(from)) {
                GraphNode newFrom = reversed.getNodeByName(to.name);
                GraphNode newTo = reversed.getNodeByName(from.name);
                reversed.addEdge(newFrom, newTo);
            }
        }
        return reversed;
    }

    public GraphNode getNodeByName(String name) {
        for (GraphNode node : adjacencyList.keySet()) {
            if (node.name.equals(name)) return node;
        }
        return null;
    }

    public void resetVisited() {
        for (GraphNode node : adjacencyList.keySet()) {
            node.reset();
        }
    }
}