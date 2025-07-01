package question3.strongconnectivitycheck;

public class MainGraphEx {
    public static void main(String[] args) {
        GraphCreator creator = new GraphCreator();
        creator.buildGraphFromInput();

        boolean stronglyConnected = StrongConnectivityChecker.isStronglyConnected(creator.getGraph());
        System.out.println("The graph is " + (stronglyConnected ? "strongly connected ✅" : "not strongly connected ❌"));
    }
}