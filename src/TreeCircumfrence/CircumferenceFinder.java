package TreeCircumfrence;

import java.util.*;

public class CircumferenceFinder {
    private CircumferenceFinder() {}

    public static int runCircumference(Tree tree) {
        return runCircumferenceWithPath(tree).length;
    }

    public static PathResult runCircumferenceWithPath(Tree tree) {
        List<TreeNode> nodes = tree.getNodes();
        if (nodes.isEmpty()) return new PathResult(0, new ArrayList<>());

        // שלב 1 – BFS ראשון
        TreeNode root = nodes.get(0);
        TreeNode maxDxNode = bfsToFarthestNode(tree, root);

        // שלב 2 – BFS שני ושמירה על הורה
        return bfsWithPath(tree, maxDxNode);
    }

    private static TreeNode bfsToFarthestNode(Tree tree, TreeNode start) {
        Queue<TreeNode> queue = new LinkedList<>();
        start.dx = 0;
        tree.getNodes().forEach(n -> n.visited = false);
        queue.add(start);
        TreeNode farthest = start;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            current.visited = true;

            if (current.dx > farthest.dx) {
                farthest = current;
            }

            for (TreeEdge edge : tree.getEdges(current)) {
                TreeNode neighbor = (edge.node1 == current) ? edge.node2 : edge.node1;
                if (!neighbor.visited) {
                    neighbor.dx = current.dx + 1;
                    queue.add(neighbor);
                }
            }
        }
        return farthest;
    }

    private static PathResult bfsWithPath(Tree tree, TreeNode start) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        tree.getNodes().forEach(n -> {
            n.visitedY = false;
            n.dy = 0;
        });

        queue.add(start);
        TreeNode farthest = start;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            current.visitedY = true;

            if (current.dy > farthest.dy) {
                farthest = current;
            }

            for (TreeEdge edge : tree.getEdges(current)) {
                TreeNode neighbor = (edge.node1 == current) ? edge.node2 : edge.node1;
                if (!neighbor.visitedY) {
                    neighbor.dy = current.dy + 1;
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // בניית המסלול מהצומת הכי רחוק חזרה להתחלה
        List<String> path = new LinkedList<>();
        TreeNode curr = farthest;
        while (curr != null) {
            path.add(0, curr.name); // מוסיף לתחילת הרשימה
            curr = parentMap.get(curr);
        }

        return new PathResult(path.size() - 1, path);
    }

    public static class PathResult {
        public int length;
        public List<String> path;

        public PathResult(int length, List<String> path) {
            this.length = length;
            this.path = path;
        }
    }
}