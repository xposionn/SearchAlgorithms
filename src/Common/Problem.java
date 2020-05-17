package Common;

import Algorithms.Algorithm;
import Printers.Printer;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Problem {
    private Algorithm algorithm;
    private boolean toOpen;
    private boolean toPrintTime;
    private HashMap<Color,Integer> colorPrices;
    private HashMap<Integer,Color> colorMap;
    private BoardState startBoard;
    private BoardState goalBoard;
    private int spacePositionX;
    private int spacePositionY;

    public Problem() {
        colorPrices = new HashMap<>();
        colorMap = new HashMap<>();
        colorPrices.put(Color.RED,30);
    }

    public void buildProblemFromFile(String fileName){
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            setAlgoFromName(bufferedReader.readLine());
            String line=null;
            setTime(bufferedReader.readLine());
            setToOpen(bufferedReader.readLine());
            setSizeOfBoard(bufferedReader.readLine());
            setBlackColors(bufferedReader.readLine());
            setRedColors(bufferedReader.readLine());
            startBoard.setColors(colorPrices,colorMap);
            line = bufferedReader.readLine();
            int row = 0;
            while(line !=null){
                startBoard.setRow(row++,line);
                line = bufferedReader.readLine();
            }
            buildGoal();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void buildGoal() {
        int rows = goalBoard.getBoard().length;
        int columns = goalBoard.getBoard()[0].length;
        for(int i=0;i<rows;++i){
            for(int j=0;j<columns;++j){
                goalBoard.setPosition(i,j,1+i*columns+j);
            }
        }
        goalBoard.getBoard()[rows-1][columns-1] = 0;
        goalBoard.setSpaceRow(rows-1);
        goalBoard.setSpaceColumn(columns-1);
    }

    private void setRedColors(String readLine) {
        String toSplit = readLine.substring(readLine.indexOf(':')+1).trim();
        //If array is empty
        if(toSplit.isEmpty())
            return;
        String[] splitted = toSplit.split(",");
        for(String str:splitted){
            int value = Integer.parseInt(str);
            colorMap.put(value,Color.RED);
        }
    }

    private void setBlackColors(String readLine) {
        String toSplit = readLine.substring(readLine.indexOf(':')+1).trim();
        //If array is empty
        if(toSplit.isEmpty())
            return;
        String[] splitted = toSplit.split(",");
        for(String str:splitted){
            int value = Integer.parseInt(str);
            colorMap.put(value,Color.BLACK);
        }
    }

    private void setSizeOfBoard(String readLine) {
        String[] pos = readLine.split("x");
        int rows = Integer.parseInt(pos[0]);
        int columns = Integer.parseInt(pos[1]);
        startBoard = new BoardState(rows,columns);
        goalBoard = new BoardState(rows,columns);
    }

    private void setToOpen(String readLine) {
        this.toPrintTime = !readLine.equals("no open");
    }

    private void setTime(String readLine) {
        //check if line is true or false;
        this.toPrintTime= !readLine.equals("no time");
    }

    private void setAlgoFromName(String line) {
        //check which algo to use
//        this.algorithm = new BFS();
    }

    public BoardState getStartBoard() {
        return startBoard;
    }

    public BoardState getGoalBoard() {
        return goalBoard;
    }

    public boolean isToOpen() {
        return toOpen;
    }

    public boolean isToPrintTime() {
        return toPrintTime;
    }
}
