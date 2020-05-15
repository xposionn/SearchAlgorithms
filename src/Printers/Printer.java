package Printers;

import Common.BoardState;

public class Printer {

    public static void printBoard(BoardState boardState){
        int[][] board = boardState.getBoard();
        for(int[] row:board){
            for(int value:row){
                String valueToPrint = ""+value;
                if(value==0)
                    valueToPrint="_";
                System.out.print(valueToPrint+(value==row[row.length-1]?"":","));
            }
            System.out.println();
        }
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
