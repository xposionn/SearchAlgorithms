package Common;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashMap;

public class BoardState {
    private int[][] board;
    private BoardState parent;
    private int depth;
    private int spacePositionRow;
    private int spacePositionColumn;
    private HashMap<Color, Integer> colorPrices;
    private HashMap<Integer, Color> colorMap;
    private String movement;

    public BoardState(int rows, int columns) {
        movement = "";
        board = new int[rows][columns];
        parent = null;
        depth = 0;
    }


    public BoardState(BoardState b, Direction d) {
        this.board = b.board.clone();
        for (int y = 0; y < this.board.length; y++) {
            this.board[y] = b.board[y].clone();
        }
        this.spacePositionColumn = b.spacePositionColumn;
        this.spacePositionRow = b.spacePositionRow;
        this.depth = b.depth;
        this.parent = b;
        this.moveSpace(d);
        this.depth++;
        this.colorMap = b.colorMap;
        this.colorPrices = b.colorPrices;
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
            case UP:
                if (spacePositionRow > 0){
                    int value = board[spacePositionRow-1][spacePositionColumn];
                    movement = value+"D";
                    board[spacePositionRow][spacePositionColumn] = board[spacePositionRow-1][spacePositionColumn];
                    spacePositionRow--;
                    board[spacePositionRow][spacePositionColumn] = 0;
                }
                break;
            case DOWN:
                if (spacePositionRow < board.length-1){
                    int value = board[spacePositionRow+1][spacePositionColumn];
                    movement = value+"U";
                    board[spacePositionRow][spacePositionColumn] = board[spacePositionRow+1][spacePositionColumn];
                    spacePositionRow++;
                    board[spacePositionRow][spacePositionColumn] = 0;
                }
                break;
            case LEFT:
                if (spacePositionColumn > 0){

                    int value = board[spacePositionRow][spacePositionColumn-1];
                    movement = value+"R";
                    board[spacePositionRow][spacePositionColumn] = board[spacePositionRow][spacePositionColumn-1];
                    spacePositionColumn--;
                    board[spacePositionRow][spacePositionColumn] = 0;
                }
                break;
            case RIGHT:
                if (spacePositionColumn < board[0].length-1){

                    int value = board[spacePositionRow][spacePositionColumn+1];
                    movement = value+"L";
                    board[spacePositionRow][spacePositionColumn] = board[spacePositionRow][spacePositionColumn+1];
                    spacePositionColumn++;
                    board[spacePositionRow][spacePositionColumn] = 0;
                }
                break;
        }
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
}
