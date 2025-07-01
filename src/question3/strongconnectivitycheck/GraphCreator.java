package question3.strongconnectivitycheck;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GraphCreator {
    private DirectedGraph graph = new DirectedGraph();
    private Map<String, GraphNode> nodeMap = new HashMap<>();

    public void buildGraphFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many nodes are in the graph?");
        int n = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the names of the nodes (comma-separated):");
        String[] nodeNames = scanner.nextLine().split(",");

        if (nodeNames.length != n) {
            System.out.println("Error: node count doesn't match input.");
            return;
        }

        for (String name : nodeNames) {
            name = name.trim();
            GraphNode node = new GraphNode(name);
            graph.addNode(node);
            nodeMap.put(name, node);
        }

        for (String name : nodeNames) {
            name = name.trim();
            GraphNode fromNode = nodeMap.get(name);
            System.out.println("Enter neighbors that " + name + " points to (comma-separated):");
            String[] neighbors = scanner.nextLine().split(",");

            for (String neighborName : neighbors) {
                neighborName = neighborName.trim();
                if (!neighborName.isEmpty() && nodeMap.containsKey(neighborName)) {
                    graph.addEdge(fromNode, nodeMap.get(neighborName));
                }
            }
        }
    }

    public DirectedGraph getGraph() {
        return graph;
    }
}