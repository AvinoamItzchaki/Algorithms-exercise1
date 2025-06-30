package TreeCircumfrence;

public class MainTreeEx {
    public static void main(String[] args) {
        TreeCreator tc = new TreeCreator();
        tc.generateTree();
        CircumferenceFinder.PathResult result = CircumferenceFinder.runCircumferenceWithPath(tc.getTree());

        System.out.println("Circumference of tree is: " + result.length);
        System.out.println("Longest path is: " + String.join(" → ", result.path));
    }
}
// A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z