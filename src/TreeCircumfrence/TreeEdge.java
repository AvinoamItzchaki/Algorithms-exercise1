package TreeCircumfrence;

public class TreeEdge {
    TreeNode node1;
    TreeNode node2;

    public TreeEdge(TreeNode node1, TreeNode node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public String toString() {
        return node1 + " -- " + node2;
    }
}
