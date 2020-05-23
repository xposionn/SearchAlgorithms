package Printers;

import Common.BoardState;
import Common.Problem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Printer {

    private static final String OUTPUT_FILE_NAME = "output.txt";


    public static String printBoard(BoardState boardState) {
        int[][] board = boardState.getBoard();
        String output = "";
        for (int[] row : board) {
            for (int value : row) {
                String valueToPrint = "" + value;
                if (value == 0) {
                    valueToPrint = "_";
                }
                output += valueToPrint + (value == row[row.length - 1] ? "" : ",");
            }
            output += "\n";
        }
        return output;
    }


    public static void exportToOutput(Problem p, BoardState finalState, boolean foundSolution, int nodesExpanded, double totalTime) {
        try {
            FileWriter write = new FileWriter(OUTPUT_FILE_NAME);
            PrintWriter print_line = new PrintWriter(write, true);
            if (foundSolution) {
                print_line.println(Printer.solutionToString(finalState));
                print_line.println("Num: " + nodesExpanded);
                print_line.println("Cost: " + finalState.getPaid());
                if (p.withTime()) {
                    print_line.println(totalTime + " seconds");
                }
                print_line.close();
            } else {
                print_line.println("no path");
                print_line.println("Num: " + nodesExpanded);
                if (p.withTime()) {
                    print_line.println(totalTime + " seconds");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportToOutput(Problem p, BoardState finalState, int nodesExpanded, double totalTime) {
        exportToOutput(p,finalState,true,nodesExpanded,totalTime);
    }


    //Private Methods
    private static String solutionToString(BoardState boardState) {
        BoardState current = boardState;
        String solution = "";
        while (current != null) {
            solution = current.getMovement() + "-" + solution;
            current = current.getParent();
        }
        return solution.substring(1, solution.length() - 1);
    }
}
