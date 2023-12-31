package models;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Cell>> board;
    private int size;

    private Board() {
    }

    public Board(int size) {
        this.size = size;
        board = new ArrayList<>();

        for(int i = 0; i < size; i++){
            board.add(new ArrayList<>());
            for(int j = 0; j < size; j++){
                board.get(i).add(new Cell(i, j));
            }
        }

    }

    public void displayBoard(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                board.get(i).get(j).display();
            }
            System.out.println();
        }
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }
}
