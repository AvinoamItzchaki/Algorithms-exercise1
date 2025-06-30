package class1;

import java.util.List;
import java.util.Scanner;

public class MainRobotEx {
    static int SIDE_LENGTH = 10; //Size of maze will be SIDE_LENGTH * SIDE_LENGTH
    final static double OBSTACLE_PROBABILITY = 0.3; //Chance of a square being an obstacle from 0-1
    static int ROBOT_COUNT = 5; //Number of robots

    final static int ROBOT_TOKEN = -2; //Temp marking for robot spots
    final static int OBSTACLE_TOKEN = -1; //Temp marking for obstacle spots
    final static int MAX_DIGITS_PER_CELL = 5; //Recommended to do +1 for readable results. For ex: If largest collective distance is 100, then MDPN will be 4. Used to format map printing

    final static String SYMBOL_ROBOT = "🟦"; //Symbol marking a robot
    final static String SYMBOL_OBSTACLE = "⬛"; //Symbol marking an obstacle
    final static String SYMBOL_PATH = "⬜"; //Symbol marking a path in raw map

    final static String SYMBOL_ROBOTS_OPTIMAL_MEETING_POINT = "🟩"; //Symbol marking the optimal meeting point of the robots in the result map


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input size of side of board (NxN):");
        SIDE_LENGTH = scanner.nextInt();

        System.out.println("Input number of robots:");
        ROBOT_COUNT = scanner.nextInt();

        MazeGenerator generator = new MazeGenerator(
                SIDE_LENGTH,
                OBSTACLE_PROBABILITY,
                ROBOT_COUNT,
                (short) ROBOT_TOKEN,
                (short) OBSTACLE_TOKEN
        );

        generator.generateMaze();
        short[][] rawMap = generator.getMap();
        List<Node> robots = generator.convertToGraph();

        BFSRunner bfsRunner = new BFSRunner(ROBOT_TOKEN, OBSTACLE_TOKEN);
        int[][] bfsMap = bfsRunner.runBFS(robots, SIDE_LENGTH);

        MapPrinter printer = new MapPrinter(
                ROBOT_TOKEN,
                OBSTACLE_TOKEN,
                MAX_DIGITS_PER_CELL,
                SYMBOL_ROBOT,
                SYMBOL_OBSTACLE,
                SYMBOL_PATH,
                SYMBOL_ROBOTS_OPTIMAL_MEETING_POINT
        );

        System.out.println("Original map:");
        printer.printRawMap(rawMap);

        System.out.println("Collective distances after BFS:");
        printer.printSearchedMap(bfsMap, robots);
    }
}



//⬛ ⬜ ⬜ ⬛ ⬜ ⬜ ⬜
//⬜ ⬜ ⬛ 🟦 ⬜ ⬜ ⬜
//⬜ ⬜ ⬛ ⬛ ⬛ ⬛ ⬜
//⬜ ⬜ ⬜ ⬜ ⬛ ⬜ 🟩
//⬜ ⬜ 🟦 ⬜ ⬜ ⬜ ⬜
//⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜
//⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜
//🟩