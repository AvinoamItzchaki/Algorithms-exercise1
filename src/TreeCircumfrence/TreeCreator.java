package TreeCircumfrence;

import java.util.*;

public class TreeCreator {
    private Tree tree;
    private Map<String, TreeNode> nodeMap;
    private Set<String> createdEdges; // למניעת כפילויות

    public TreeCreator() {
        tree = new Tree();
        nodeMap = new HashMap<>();
        createdEdges = new HashSet<>();
    }

    public void generateTree() {
        Scanner scanner = new Scanner(System.in);

        // שלב 1: קבלת שמות הצמתים
        System.out.println("How many nodes are in the tree?");
        int n = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter the names of the nodes (comma-separated):");
        String[] nodeNames = scanner.nextLine().split(",");

        if (nodeNames.length != n) {
            System.out.println("Error: number of names doesn't match number of nodes.");
            return;
        }

        // יצירת הצמתים והוספתם למפה
        for (String name : nodeNames) {
            TreeNode node = tree.createNode();
            node.name = name.trim();
            nodeMap.put(node.name, node);
        }

        // שלב 2: קבלת קשרים (קשתות)
        for (String name : nodeNames) {
            String trimmedName = name.trim();
            System.out.println("Enter neighbors of " + trimmedName + " (comma-separated):");
            String[] neighbors = scanner.nextLine().split(",");

            TreeNode current = nodeMap.get(trimmedName);
            for (String neighborName : neighbors) {
                neighborName = neighborName.trim();
                if (!neighborName.isEmpty() && nodeMap.containsKey(neighborName)) {
                    TreeNode neighbor = nodeMap.get(neighborName);

                    // מפתח ייחודי לקשת - נניח A,B → תמיד נרשום "A|B" (ממויין)
                    String edgeKey = generateEdgeKey(current.name, neighbor.name);

                    if (!createdEdges.contains(edgeKey)) {
                        tree.addEdge(current, neighbor);
                        createdEdges.add(edgeKey);
                    }
                }
            }
        }
    }

    public Tree getTree() {
        return tree;
    }

    // מחזיר מחרוזת אחידה לכל זוג צמתים - לא משנה הסדר
    private String generateEdgeKey(String name1, String name2) {
        if (name1.compareTo(name2) < 0) {
            return name1 + "|" + name2;
        } else {
            return name2 + "|" + name1;
        }
    }
}
