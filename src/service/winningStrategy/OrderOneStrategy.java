package service.winningStrategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneStrategy implements WinningStrategy {

    private int dimension;
    private List<HashMap<Character, Integer>> rowHashMaps;
    private List<HashMap<Character, Integer>> columnHashMaps;
    private HashMap<Character, Integer> topLeftHashMap;
    private HashMap<Character, Integer> topRightHashMap;
    private HashMap<Character, Integer> cornerHashMap;

    public OrderOneStrategy(int dimension) {
        this.dimension = dimension;
        rowHashMaps = new ArrayList<>();
        columnHashMaps = new ArrayList<>();
        topLeftHashMap = new HashMap<>();
        topRightHashMap = new HashMap<>();
        cornerHashMap = new HashMap<>();

        for (int i = 0; i < dimension; i++) {
            rowHashMaps.add(new HashMap<>());
            columnHashMaps.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        return null;
    }

    private boolean isTopLeftDiagonal(int row, int col) {
        return row == col;
    }

    private boolean isTopRightDiagonal(int row, int col){
        return row + col == dimension - 1;
    }

    private boolean checkRowWin(int row, char symbol) {
        rowHashMaps.get(row).putIfAbsent(symbol, 0);
        rowHashMaps.get(row).put(symbol, rowHashMaps.get(row).get(symbol) + 1);

        return rowHashMaps.get(row).get(symbol) == dimension;
    }

    private boolean checkColumnWin(int col, char symbol) {
        columnHashMaps.get(col).putIfAbsent(symbol, 0);
        columnHashMaps.get(col).put(symbol, columnHashMaps.get(col).get(symbol) + 1);

        return columnHashMaps.get(col).get(symbol) == dimension;
    }

    public boolean checkTopLeftDiagonalWin(char symbol) {
        topLeftHashMap.putIfAbsent(symbol, 0);
        topLeftHashMap.put(symbol, topLeftHashMap.get(symbol) + 1);

        return topLeftHashMap.get(symbol) == dimension;
    }

    public boolean checkTopRightDiagonalWin(char symbol) {
        topRightHashMap.putIfAbsent(symbol, 0);
        topRightHashMap.put(symbol, topRightHashMap.get(symbol) + 1);

        return topRightHashMap.get(symbol) == dimension;
    }

    public boolean checkCornerWin(char symbol) {
        cornerHashMap.putIfAbsent(symbol, 0);
        cornerHashMap.put(symbol, cornerHashMap.get(symbol) + 1);

        return cornerHashMap.get(symbol) == 4;
    }

}
