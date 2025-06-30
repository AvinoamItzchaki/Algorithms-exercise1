package class1;

import java.util.*;

public class BFSRunner {
    private int robotToken;
    private int unreachableToken;
    public BFSRunner(int robotToken, int unreachableToken) {
        if (robotToken == unreachableToken) {
            System.out.println("Robot token and unreachableToken must be different. Initializing robot = -2, unreachable = -1");
            this.robotToken = -2;
            this.unreachableToken = -1;
            return;
        }
        if (robotToken == 0){
            System.out.println("Robot token can't be 0. Initialized to -2");
            this.robotToken = -2;
        }
        else {
            this.robotToken = robotToken;
        }
        this.unreachableToken = unreachableToken;
    }

    public void setRobotToken(int robotToken) {
        if (robotToken == 0){
            System.out.println("Robot token can't be 0. Initialized to -2");
            this.robotToken = -2;
        }
        else if (robotToken == unreachableToken){
            System.out.println("Robot token and unreachableToken must be different");
        }
        else {
            this.robotToken = robotToken;
        }
    }

    public void setUnreachableToken(int unreachableToken) {
        if (robotToken == unreachableToken){
            System.out.println("Robot token and unreachableToken must be different");
        }
        else {
            this.unreachableToken = unreachableToken;
        }
    }

    public int[][] runBFS(List<Node> robots, int lengthSize) {
        int[][] map = new int[lengthSize][lengthSize];
        Queue<Node> queue = new LinkedList<>();
        for (Node robot : robots) {
            robot.d = 0;
            queue.add(robot);
            HashMap<Integer, HashSet<Integer>> visited = new HashMap<>();
            while (!queue.isEmpty()) {
                Node current = queue.remove();
                if (!visited.containsKey(current.x)){
                    visited.put(current.x, new HashSet<>());
                }
                visited.get(current.x).add(current.y);
                map[current.x][current.y] += current.d;
                for (Node neighbor : current.neighbors) {
                    if ((!visited.containsKey(neighbor.x) || !visited.get(neighbor.x).contains(neighbor.y)) && !queue.contains(neighbor)) {
                        neighbor.d = current.d + 1;
                        queue.add(neighbor);
                    }
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = unreachableToken;
                }
            }
        }
        return map;
    }
}
