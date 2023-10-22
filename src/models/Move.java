package models;

public class Move {

    private Player player;
    private Cell cell;

    private Move() {
    }

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public Move(int row, int col, Player player) {
        this.player = player;
        this.cell = new Cell(row, col);
    }

}
