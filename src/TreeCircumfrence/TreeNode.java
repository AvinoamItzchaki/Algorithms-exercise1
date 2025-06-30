package TreeCircumfrence;

public class TreeNode {
    int dx;
    int dy;
    boolean visited;
    boolean visitedY;
    String name;

    public TreeNode() {
        this.dx = 0;
        this.dy = 0;
        this.visited = false;
        this.visitedY = false;
        this.name = "";
    }

    @Override
    public String toString() {
        return name;
    }

    // חשוב: להשוואה במפות/סטים
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TreeNode)) return false;
        TreeNode other = (TreeNode) obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
