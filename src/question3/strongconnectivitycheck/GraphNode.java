package question3.strongconnectivitycheck;

import java.util.Objects;

public class GraphNode {
    String name;
    boolean visited;

    public GraphNode(String name) {
        this.name = name;
        this.visited = false;
    }

    public void reset() {
        this.visited = false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphNode)) return false;
        GraphNode that = (GraphNode) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}