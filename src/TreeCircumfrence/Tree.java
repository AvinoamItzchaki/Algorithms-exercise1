package TreeCircumfrence;

import java.util.*;

public class Tree {
    private final Map<TreeNode, List<TreeEdge>> nodesAndEdges;

    public Tree() {
        nodesAndEdges = new HashMap<>();
    }

    public void addEdge(TreeNode node1, TreeNode node2) {
        TreeEdge edge = new TreeEdge(node1, node2);

        nodesAndEdges.computeIfAbsent(node1, k -> new LinkedList<>()).add(edge);
        nodesAndEdges.computeIfAbsent(node2, k -> new LinkedList<>()).add(edge);
    }

    public List<TreeEdge> getEdges(TreeNode node) {
        return nodesAndEdges.getOrDefault(node, new LinkedList<>());
    }

    public List<TreeEdge> getEdges() {
        Set<TreeEdge> unique = new HashSet<>();
        for (List<TreeEdge> list : nodesAndEdges.values()) {
            unique.addAll(list);
        }
        return new ArrayList<>(unique);
    }

    public List<TreeNode> getNodes() {
        return new ArrayList<>(nodesAndEdges.keySet());
    }

    public TreeNode createNode() {
        TreeNode node = new TreeNode();
        nodesAndEdges.put(node, new LinkedList<>());
        return node;
    }

    public TreeNode getNodeByName(String name) {
        for (TreeNode node : nodesAndEdges.keySet()) {
            if (node.name.equals(name)) return node;
        }
        return null;
    }
}
