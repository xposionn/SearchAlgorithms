import Algorithms.Algorithm;
import Algorithms.BFS;
import Algorithms.DFID;
import Common.Problem;
import Heuristics.IHeuristic;
import Heuristics.Manhattan;


public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input.txt");
        Algorithm dfid = new BFS(p);
        dfid.solve();
    }
}
