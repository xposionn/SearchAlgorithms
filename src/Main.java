import Algorithms.Algorithm;
import Algorithms.BFS;
import Algorithms.DFID;
import Algorithms.IDAStar;
import Common.BoardState;
import Common.Problem;
import Heuristics.IHeuristic;
import Heuristics.Manhattan;

import java.util.Comparator;


public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input.txt");
        Comparator<BoardState> manhatthan = new Manhattan(p.getGoalBoard());
        IHeuristic heuristic = new Manhattan(p.getGoalBoard());
        Algorithm dfid = new IDAStar(p,manhatthan,heuristic);
        dfid.solve();
    }
}
