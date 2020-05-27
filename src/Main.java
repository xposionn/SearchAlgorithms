import Algorithms.*;
import Common.Problem;
import Heuristics.*;


public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input5.txt");
        p.solve();
    }
}