import Algorithms.*;
import Common.BoardState;
import Common.Problem;
import Heuristics.EuclideanDistance;
import Heuristics.IHeuristic;
import Heuristics.Manhattan;
import Heuristics.MissPlace;

import java.util.Comparator;


public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input.txt");

        //Heuristics
        IHeuristic manhattan = new Manhattan(p.getGoalBoard());
        IHeuristic euclidean = new EuclideanDistance(p.getGoalBoard());
        IHeuristic missPlace = new MissPlace(p.getGoalBoard());
        IHeuristic heuristic = manhattan;


        Algorithm astar = new AStar(p,heuristic);
        Algorithm dfid = new DFID(p);
        Algorithm idastar = new IDAStar(p,heuristic);
        Algorithm bfs = new BFS(p);
        Algorithm dfBnB = new DFBnB(p,heuristic);
//        bfs.solve();
//        System.out.println("BFS");
        astar.solve();
//        System.out.println("astar");
//        idastar.solve();
//        System.out.println("idastar");
//        dfid.solve();
//        System.out.println("dfis");
//        dfBnB.solve();
//        System.out.println("dfBnB");

    }
}
