package Printers;

import Common.BoardState;
import Common.Problem;

public class Printer {

    public static String printBoard(BoardState boardState){
        int[][] board = boardState.getBoard();
        String output = "";
        for(int[] row:board){
            for(int value:row){
                String valueToPrint = ""+value;
                if(value==0) {
                    valueToPrint="_";
                }
                output+= valueToPrint+(value==row[row.length-1]?"":",");
            }
            output+="\n";

        }

        return output;
    }
    public static void printSolution(BoardState boardState) {
        BoardState current = boardState;
        String solution = "";
        while (current != null) {
            solution = current.getMovement()+"-" + solution;
            current = current.getParent();
        }
        System.out.println(solution.substring(1,solution.length()-1));
    }

    public static void printPrice(BoardState boardState){
        int cost = 0;
        while(boardState.getParent()!=null){
            cost+=boardState.getPaid();
            boardState = boardState.getParent();
        }
        System.out.println(cost);
    }

    public static void finishSolution(Problem p, BoardState finalState,boolean foundSolution,int nodesExpanded,double totalTime){
        if(foundSolution){
            if (!p.isToOpen()) {
                Printer.printSolution(finalState);
                System.out.println("Num: "+nodesExpanded);
                Printer.printPrice(finalState);
                System.out.println(totalTime+ " seconds");
            }
        }else{
            System.out.println("no path");
            System.out.println("Num: "+nodesExpanded);
        }

    }
}
