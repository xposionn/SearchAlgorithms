package Common;

import Printers.Printer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class BoardState {
    private int[][] board;
    private BoardState parent;
    private int depth;
    private int spacePositionRow;
    private int spacePositionColumn;
    private HashMap<Color, Integer> colorPrices;
    private HashMap<Integer, Color> colorMap;
    private String movement;
    private boolean isMoved = false;
    private int paid = 0;
    private int rows;
    private int columns;

    //for AStar
    private boolean isOut = false;

    public BoardState(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        paid = 0;
        movement = "";
        board = new int[rows][columns];
        parent = null;
        depth = 0;
    }


    public BoardState(BoardState b, Direction d) {
        this.rows = b.rows;
        this.columns = b.columns;
        this.board = b.board.clone();
        for (int y = 0; y < this.board.length; y++) {
            this.board[y] = b.board[y].clone();
        }
        this.spacePositionColumn = b.spacePositionColumn;
        this.spacePositionRow = b.spacePositionRow;
        this.depth = b.depth;
        this.parent = b;
        this.depth++;
        this.paid=b.paid;
        this.colorMap = b.colorMap;
        this.colorPrices = b.colorPrices;
        this.moveSpace(d);

    }



    public void setRow(int i, String line) {
        String[] split = line.split(",");
        for (int j = 0; j < split.length; ++j) {
            int value = 0;
            if (!split[j].equals("_")) {
                value = Integer.parseInt(split[j]);
            }else{
                spacePositionRow = i;
                spacePositionColumn = j;
            }
            board[i][j] = value;
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setPosition(int i, int j, int value) {
        board[i][j] = value;
    }

    public void setSpaceRow(int i) {
        spacePositionRow = i;
    }

    public void setSpaceColumn(int i) {
        spacePositionColumn = i;
    }

    public void moveSpace(Direction direction) {
        switch (direction) {
            case DOWN:
                if (spacePositionRow > 0){
                    int value = board[spacePositionRow-1][spacePositionColumn];
                    if(isAllowedToBeMoved(value)) {

                        movement = value + "D";
                        board[spacePositionRow][spacePositionColumn] = board[spacePositionRow - 1][spacePositionColumn];
                        spacePositionRow--;
                        board[spacePositionRow][spacePositionColumn] = 0;
                        this.isMoved = true;
                        int cost = (colorMap.get(value) == null) ? 1 : colorPrices.get(colorMap.get(value));
                        paid += cost;
                    }
                }
                break;
            case UP:
                if (spacePositionRow < board.length-1){
                    int value = board[spacePositionRow+1][spacePositionColumn];
                    if(isAllowedToBeMoved(value)) {
                        movement = value + "U";
                        board[spacePositionRow][spacePositionColumn] = board[spacePositionRow + 1][spacePositionColumn];
                        spacePositionRow++;
                        board[spacePositionRow][spacePositionColumn] = 0;
                        this.isMoved = true;
                        int cost = (colorMap.get(value) == null) ? 1 : colorPrices.get(colorMap.get(value));
                        paid += cost;
                    }


                }
                break;
            case RIGHT:
                if (spacePositionColumn > 0){
                    int value = board[spacePositionRow][spacePositionColumn-1];
                    if(isAllowedToBeMoved(value)) {
                        movement = value + "R";
                        board[spacePositionRow][spacePositionColumn] = board[spacePositionRow][spacePositionColumn - 1];
                        spacePositionColumn--;
                        board[spacePositionRow][spacePositionColumn] = 0;
                        this.isMoved = true;
                        int cost = (colorMap.get(value) == null) ? 1 : colorPrices.get(colorMap.get(value));
                        paid += cost;
                    }


                }
                break;
            case LEFT:
                if (spacePositionColumn < board[0].length-1){
                    int value = board[spacePositionRow][spacePositionColumn+1];
                    if(isAllowedToBeMoved(value)) {

                        movement = value + "L";
                        board[spacePositionRow][spacePositionColumn] = board[spacePositionRow][spacePositionColumn + 1];
                        spacePositionColumn++;
                        board[spacePositionRow][spacePositionColumn] = 0;
                        this.isMoved = true;
                        int cost = (colorMap.get(value) == null) ? 1 : colorPrices.get(colorMap.get(value));
                        paid += cost;
                    }
                }
                break;
        }
    }

    private boolean isAllowedToBeMoved(int value) {
        Color color = colorMap.get(value);
        return Color.BLACK != color;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        BoardState other = (BoardState) obj;
        for (int i = 0; i < board.length; i++) { //because arrays in Java are the work of the devil and I don't trust Arrays.deepEquals() to work.
            for (int j = 0; j < board[0].length; j++) {
                if (this.board[i][j] != other.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public void setColors(HashMap<Color, Integer> colorPrices, HashMap<Integer, Color> colorMap) {
        this.colorMap = colorMap;
        this.colorPrices = colorPrices;
    }

    public BoardState getParent() {
        return parent;
    }

    public String getMovement(){
        return movement;
    }

    public boolean isMoved() {
        return isMoved;
    }

    @Override
    public String toString() {
        return Printer.printBoard(this);
    }

    public int getPaid() {
        return paid;
    }


    //AStar Extention


    public boolean isOut() {
        return isOut;
    }

    public void isOut(boolean out) {
        isOut = out;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getH() {
        int h = 0;
        for(int i=0;i<rows;++i){
            for(int j=0;j<columns;++j){
                int value = board[i][j];
                int cRow = value/columns;
                int cColumn = value%columns;
                if(cColumn==0){
                    cColumn = columns-1;
                    cRow--;
                }else{
                    cColumn--;
                }
                h+=Math.abs(i-cRow)+Math.abs(j-cColumn);
            }
        }
        return h;
    }

    public ArrayList<BoardState> getAllowedChildrens(){
        ArrayList<BoardState> allowedMoves = new ArrayList<>();
        for(Direction direction:Direction.values()){
            BoardState possibleChild = new BoardState(this,direction);
            if(possibleChild.isMoved() && !possibleChild.equals(parent)){
                allowedMoves.add(possibleChild);
            }
        }
    return allowedMoves;
    }

}
