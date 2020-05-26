import Algorithms.*;
import Common.BoardState;
import Common.Problem;
import Heuristics.EuclideanDistance;
import Heuristics.IHeuristic;
import Heuristics.Manhattan;

import java.util.Comparator;


public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input.txt");
        IHeuristic manhattan = new Manhattan(p.getGoalBoard());
        IHeuristic euclidean = new EuclideanDistance(p.getGoalBoard());
        IHeuristic heuristic = manhattan;
        Algorithm astar = new AStar(p,heuristic);
        Algorithm dfid = new DFID(p);
        Algorithm idastar = new IDAStar(p,manhattan);
        Algorithm bfs = new BFS(p);
        Algorithm dfBnB = new DFBnB(p,manhattan);
        astar.solve();
    }
}
