package class1;

import java.util.LinkedList;
import java.util.List;

public class Node {
    int x;
    int y;
    int d;
    boolean isRobot;
    List<Node> neighbors;
    public Node(int x, int y,boolean isRobot){
        this.x = x;
        this.y = y;
        this.d = 0;
        neighbors = new LinkedList<>();
    }
}
