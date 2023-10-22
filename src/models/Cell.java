package models;

import models.enums.CellStatus;

public class Cell {

    private int row;
    private int column;
    private Player player;
    private CellStatus cellStatus;

    public Cell(int row, int col) {
        this.row = row;
        this.column = col;
        this.cellStatus = CellStatus.EMPTY;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }
}
