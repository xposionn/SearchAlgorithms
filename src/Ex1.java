import Algorithms.*;
import Common.Problem;
import Heuristics.*;


public class Ex1 {

    public static void main(String[] args) {
        Problem p = new Problem();
        p.buildProblemFromFile("input.txt");
        p.solve();
    }
}