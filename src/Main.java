import Algorithms.*;
import Common.Problem;
import Heuristics.*;


public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input.txt");

        //Heuristics
        Heuristic manhattan = new Manhattan(p.getGoalBoard());


        Algorithm astar = new AStar(p, manhattan);
        Algorithm dfid = new DFID(p);
        Algorithm idastar = new IDAStar(p,manhattan);
        Algorithm bfs = new BFS(p);
        idastar.solve();
    }
}