import Algorithms.Algorithm;
import Algorithms.BFS;
import Common.Problem;


public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input2.txt");
        Algorithm bfs = new BFS(p);
        bfs.solve();
//        p.
    }
}
