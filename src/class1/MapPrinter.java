package class1;

import java.util.*;

public class MapPrinter {
    private int robotValue;
    private int obstacleValue;
    private int maxDigits;
    private String robotSymbol;
    private String obstacleSymbol;
    private String pathSymbol;
    private String robotsOptimalMeetingPointSymbol;

    public MapPrinter(int robotValue, int obstacleValue, int maxDigits, String robotSymbol, String obstacleSymbol, String pathSymbol, String robotsOptimalMeetingPointSymbol) {

        this.maxDigits = Math.max(maxDigits, 1);

        if (robotValue == obstacleValue) {
            System.out.println("Robot and obstacle tokens must be different. Defaults applied.");
            this.robotValue = -2;
            this.obstacleValue = -1;
        } else {
            this.robotValue = (robotValue == 0) ? -2 : robotValue;
            this.obstacleValue = obstacleValue;
        }


        this.robotSymbol = robotSymbol;
        this.obstacleSymbol = obstacleSymbol;
        this.pathSymbol = pathSymbol;
        this.robotsOptimalMeetingPointSymbol = robotsOptimalMeetingPointSymbol;
    }

    public void printRawMap(short[][] map) {
        Set<String> optimalCells = getMinPositions(map, obstacleValue);

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                String key = x + "," + y;

                if (map[x][y] == robotValue) {
                    System.out.print(robotSymbol + " ");
                } else if (optimalCells.contains(key)) {
                    System.out.print(robotsOptimalMeetingPointSymbol + " ");
                } else if (map[x][y] == obstacleValue) {
                    System.out.print(obstacleSymbol + " ");
                } else {
                    System.out.print(pathSymbol + " ");
                }
            }
            System.out.println();
        }
    }

    public void printSearchedMap(int[][] map, List<Node> robots) {
        HashMap<Integer, HashSet<Integer>> robotCoords = new HashMap<>();
        for (Node robot : robots) {
            robotCoords.computeIfAbsent(robot.x, k -> new HashSet<>()).add(robot.y);
        }

        Set<String> minCells = getMinPositions(map, obstacleValue);

        System.out.println("your result:\n");
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                String key = x + "," + y;
                boolean isRobot = robotCoords.getOrDefault(x, new HashSet<>()).contains(y);

                if (isRobot) {
                    System.out.print(robotSymbol + " ");
                } else if (minCells.contains(key)) {
                    System.out.print(robotsOptimalMeetingPointSymbol + " ");
                } else if (map[x][y] == obstacleValue) {
                    System.out.print(obstacleSymbol + " ");
                } else {
                    System.out.print(pathSymbol + " ");
                }
            }
            System.out.println();
        }

        System.out.println("\nyour distances result:\n");
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                String key = x + "," + y;
                boolean isRobot = robotCoords.getOrDefault(x, new HashSet<>()).contains(y);

                if (isRobot) {
                    System.out.print(robotSymbol + " ");
                } else if (minCells.contains(key)) {
                    System.out.print(map[x][y] + " ");
                } else if (map[x][y] == obstacleValue) {
                    System.out.print(obstacleSymbol + " ");
                } else {
                    System.out.print(map[x][y] + " ");
                }
            }
            System.out.println();
        }
    }

    private Set<String> getMinPositions(int[][] map, int unreachableToken) {
        int min = Integer.MAX_VALUE;
        Set<String> result = new HashSet<>();

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                int value = map[x][y];
                if (value != unreachableToken) {
                    if (value < min) {
                        result.clear();
                        result.add(x + "," + y);
                        min = value;
                    } else if (value == min) {
                        result.add(x + "," + y);
                    }
                }
            }
        }

        return result;
    }

    private Set<String> getMinPositions(short[][] map, int unreachableToken) {
        int min = Integer.MAX_VALUE;
        Set<String> result = new HashSet<>();

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                int value = map[x][y];
                if (value != unreachableToken) {
                    if (value < min) {
                        result.clear();
                        result.add(x + "," + y);
                        min = value;
                    } else if (value == min) {
                        result.add(x + "," + y);
                    }
                }
            }
        }

        return result;
    }
}
