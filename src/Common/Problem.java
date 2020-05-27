package Common;

import Algorithms.*;
import Heuristics.Heuristic;
import Heuristics.Manhattan;
import Heuristics.PairsAnomaly;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Problem {
    private Algorithm algorithm;
    private boolean withOpen;
    private boolean withTime;
    private HashMap<Color, Integer> colorPrices;
    private HashMap<Integer, Color> colorMap;
    private BoardState startBoard;
    private BoardState goalBoard;
    private int spacePositionX;
    private int spacePositionY;

    public Problem() {
        colorPrices = new HashMap<>();
        colorMap = new HashMap<>();
        colorPrices.put(Color.RED, 30);
    }
    public void buildProblemFromFile(String fileName) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String algoName = bufferedReader.readLine();
            String line = null;
            setTime(bufferedReader.readLine());
            setWithOpen(bufferedReader.readLine());
            setSizeOfBoard(bufferedReader.readLine());
            setBlackColors(bufferedReader.readLine());
            setRedColors(bufferedReader.readLine());
            startBoard.setColors(colorPrices, colorMap);
            line = bufferedReader.readLine();
            int row = 0;
            while (line != null) {
                startBoard.setRow(row++, line);
                line = bufferedReader.readLine();
            }
            buildGoal();
            setAlgoFromName(algoName);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void buildGoal() {
        int rows = goalBoard.getBoard().length;
        int columns = goalBoard.getBoard()[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                goalBoard.setPosition(i, j, 1 + i * columns + j);
            }
        }
        goalBoard.getBoard()[rows - 1][columns - 1] = 0;
        goalBoard.setSpaceRow(rows - 1);
        goalBoard.setSpaceColumn(columns - 1);
    }

    private void setRedColors(String readLine) {
        String toSplit = readLine.substring(readLine.indexOf(':') + 1).trim();
        //If array is empty
        if (toSplit.isEmpty())
            return;
        String[] splitted = toSplit.split(",");
        for (String str : splitted) {
            int value = Integer.parseInt(str);
            colorMap.put(value, Color.RED);
        }
    }

    private void setBlackColors(String readLine) {
        String toSplit = readLine.substring(readLine.indexOf(':') + 1).trim();
        //If array is empty
        if (toSplit.isEmpty())
            return;
        String[] splitted = toSplit.split(",");
        for (String str : splitted) {
            int value = Integer.parseInt(str);
            colorMap.put(value, Color.BLACK);
        }
    }

    private void setSizeOfBoard(String readLine) {
        String[] pos = readLine.split("x");
        int rows = Integer.parseInt(pos[0]);
        int columns = Integer.parseInt(pos[1]);
        startBoard = new BoardState(rows, columns);
        goalBoard = new BoardState(rows, columns);
    }

    private void setWithOpen(String readLine) {
        this.withOpen = !readLine.equals("no open");
    }

    private void setTime(String readLine) {
        //check if line is true or false;
        this.withTime = !readLine.equals("no time");
    }

    private void setAlgoFromName(String line) {
        //check which algo to use
        switch (line) {
            case "BFS":
                this.algorithm = new BFS(this);
                break;
            case "A*":
                this.algorithm = new AStar(this, new PairsAnomaly(getGoalBoard()));
                break;
            case "IDA*":
                this.algorithm = new IDAStar(this, new PairsAnomaly(getGoalBoard()));
                break;
            case "DFID":
                this.algorithm = new DFID(this);
                break;
            case "DFBnB":
                this.algorithm = new DFBnB(this, new PairsAnomaly(getGoalBoard()));
                break;
        }
    }

    public BoardState getStartBoard() {
        return startBoard;
    }

    public BoardState getGoalBoard() {
        return goalBoard;
    }

    public boolean withOpen() {
        return withOpen;
    }

    public boolean withTime() {
        return withTime;
    }

    public void solve() {
        this.algorithm.solve();
    }
}
