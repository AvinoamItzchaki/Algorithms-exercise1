package question3.strongconnectivitycheck;

import java.util.*;

public class StrongConnectivityChecker {

    private StrongConnectivityChecker() {}

    public static boolean isStronglyConnected(DirectedGraph graph) {
        List<GraphNode> nodes = graph.getNodes();
        if (nodes.isEmpty()) return true;

        // Check reachability from first node
        if (!isAllReachable(graph, nodes.get(0))) return false;

        // Check reachability in the transpose graph
        DirectedGraph reversed = graph.transpose();
        GraphNode reversedStart = reversed.getNodeByName(nodes.get(0).name);
        return isAllReachable(reversed, reversedStart);
    }

    private static boolean isAllReachable(DirectedGraph graph, GraphNode start) {
        Set<GraphNode> visited = new HashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            for (GraphNode neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited.size() == graph.getNodes().size();
    }
}