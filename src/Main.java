import Algorithms.Algorithm;
import Algorithms.BFS;
import Algorithms.DFID;
import Common.Problem;


public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input2.txt");
        Algorithm dfid = new BFS(p);
        dfid.solve();
    }
}
