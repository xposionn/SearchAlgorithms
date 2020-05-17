package Printers;

import Common.BoardState;

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
}
