package class1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
    private final int sideLength;
    private final double obstacleChance;
    private final int robotCount;
    private short robotToken;
    private short obstacleToken;
    private short[][] maze;
    public MazeGenerator(int sideLength,double obstacleChance,int robotCount,short robotToken,short obstacleToken) {
        this.sideLength = sideLength;
        this.obstacleChance = obstacleChance;
        this.robotCount = robotCount;
        this.robotToken = robotToken;
        this.obstacleToken = obstacleToken;
    }
    public short[][] getMap(){
        return maze;
    }
    public void generateMaze() {
        maze = new short[sideLength][sideLength];
        Random rand = new Random();
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                maze[i][j] = 0;
                if (rand.nextDouble(0,1) < obstacleChance) {
                    maze[i][j] = obstacleToken;
                }
            }
        }
        for (int i = 1; i <= robotCount; i++) {
            int x = rand.nextInt(sideLength);
            int y = rand.nextInt(sideLength);
            if (maze[x][y] == robotToken){
                if (i > sideLength * sideLength) {
                    break;
                }
                i--;
                continue;
            }
            maze[x][y] = robotToken;
        }
    }
    public List<Node> convertToGraph(){
        HashMap<Integer,HashMap<Integer,Node>> nodes = new HashMap<>();
        List<Node> robots = new LinkedList<>();
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (maze[i][j] == obstacleToken) {
                    continue;
                }
                Node node = getNode(i,j,nodes);
                if (maze[i][j] == robotToken) {
                    robots.add(node);
                }
                nodes.putIfAbsent(i,new HashMap<>());
                nodes.get(i).put(j,node);
                try {
                    if (maze[i - 1][j - 1] != obstacleToken){
                        Node neighbor = getNode(i - 1, j - 1,nodes);
                        node.neighbors.add(neighbor);
                    }
                }
                catch (Exception ignored){}
                try {
                    if (maze[i - 1][j] != obstacleToken){
                        Node neighbor = getNode(i - 1, j,nodes);
                        node.neighbors.add(neighbor);
                    }
                }
                catch (Exception ignored){}
                try {
                    if (maze[i - 1][j + 1] != obstacleToken){
                        Node neighbor = getNode(i - 1, j + 1,nodes);
                        node.neighbors.add(neighbor);
                    }
                }
                catch (Exception ignored){}
                try {
                    if (maze[i][j - 1] != obstacleToken){
                        Node neighbor = getNode(i, j - 1,nodes);
                        node.neighbors.add(neighbor);
                    }
                }
                catch (Exception ignored){}
                try {
                    if (maze[i][j + 1] != obstacleToken){
                        Node neighbor = getNode(i, j + 1,nodes);
                        node.neighbors.add(neighbor);
                    }
                }
                catch (Exception ignored){}
                try {
                    if (maze[i + 1][j - 1] != obstacleToken){
                        Node neighbor = getNode(i + 1, j - 1,nodes);
                        node.neighbors.add(neighbor);
                    }
                }
                catch (Exception ignored){}
                try {
                    if (maze[i + 1][j] != obstacleToken){
                        Node neighbor = getNode(i + 1, j,nodes);
                        node.neighbors.add(neighbor);
                    }
                }
                catch (Exception ignored){}
                try {
                    if (maze[i + 1][j + 1] != obstacleToken){
                        Node neighbor = getNode(i + 1, j + 1,nodes);
                        node.neighbors.add(neighbor);
                    }
                }
                catch (Exception ignored){}
            }
        }
        return robots;
    }
    private Node getNode(int x, int y,HashMap<Integer,HashMap<Integer,Node>> nodes) {
        Node node;
        if (nodes.containsKey(x) && nodes.get(x).containsKey(y)) {
            node = nodes.get(x).get(y);
        }
        else {
            node = new Node(x,y,maze[x][y] == robotToken);
            if (!nodes.containsKey(x)){
                nodes.put(x,new HashMap<>());
            }
            nodes.get(x).put(y,node);
        }
        if (maze[x][y] == robotToken){
            node.isRobot = true;
        }
        return node;
    }
}
