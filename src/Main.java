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
        Algorithm dfid = new AStar(p,heuristic);
        dfid.solve();
    }
}
