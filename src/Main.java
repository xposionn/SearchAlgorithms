import Algorithms.*;
import Common.BoardState;
import Common.Problem;
import Heuristics.IHeuristic;
import Heuristics.Manhattan;

import java.util.Comparator;


public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input.txt");
        IHeuristic heuristic = new Manhattan(p.getGoalBoard());
        Algorithm astar = new AStar(p,heuristic);
        Algorithm dfid = new DFID(p);
        Algorithm bfs = new BFS(p);
        bfs.solve();
    }
}
